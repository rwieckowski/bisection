package pl.rawie.bisection;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BisectionTest {
    public static final double DELTA = 0.00001;
    public static final double TOLERANCE = 0.00001;

    private Bisection<Double> makeBisection(int iterations, double tolerance) {
        return new Bisection<Double>(new Identity(TOLERANCE), iterations, tolerance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leftIsGreaterThanRight() {
        Bisection<Double> bisection = makeBisection(0, TOLERANCE);
        bisection.bisect(1.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void functionValuesHaveTheSameSign() {
        Bisection<Double> bisection = makeBisection(0, TOLERANCE);
        bisection.bisect(1.0, 2.0);
    }

    @Test
    public void rootInLeft() {
        Bisection<Double> bisection = makeBisection(0, TOLERANCE);
        double value = bisection.bisect(-1.0, 0.0);
        assertThat(value, is(closeTo(0.0, 0.00001)));
    }

    @Test
    public void rootInRight() {
        Bisection<Double> bisection = makeBisection(0, TOLERANCE);
        double value = bisection.bisect(0.0, 1.0);
        assertThat(value, is(closeTo(0.0, 0.00001)));
    }

    @Test(expected = MaxIterationsExceeded.class)
    public void maxIterationsExceeded() {
        Bisection<Double> bisection = makeBisection(0, TOLERANCE);
        bisection.bisect(-1.0, 3.0);
    }

    @Test
    public void rootInMidpoint() {
        Bisection<Double> bisection = makeBisection(1, TOLERANCE);
        double value = bisection.bisect(-1.0, 1.0);
        assertThat(value, is(closeTo(0.0, 0.00001)));
    }

    @Test
    public void leftAndRightWithinTolerance() {
        Bisection<Double> bisection = makeBisection(1, 0.5);
        double value = bisection.bisect(-0.1, 0.3);
        assertThat(value, is(closeTo(0.1, 0.00001)));
    }

    @Test
    public void sin() {
        BisectableRealFunction function = new BisectableRealFunction(TOLERANCE) {
            @Override
            public Double apply(Double value) {
                return Math.sin(value);
            }
        };
        Bisection<Double> bisection = new Bisection<Double>(function, 100, TOLERANCE);
        double value = bisection.bisect(1.0, 4.0);
        assertThat(value, is(closeTo(Math.PI, 0.00001)));
    }
}

class Identity extends BisectableRealFunction {
    public Identity(double tolerance) {
        super(tolerance);
    }

    @Override
    public Double apply(Double value) {
        return value;
    }
}
