package pl.rawie.bisection;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BisectionTest {
    public static final double TOLERANCE = 0.00001;

    @Test(expected = IllegalArgumentException.class)
    public void leftIsGreaterThanRight() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .build();
        bisection.bisect(1.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void functionValuesHaveTheSameSign() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(Poly.IDENTITY)
                .build();
        bisection.bisect(1.0, 2.0);
    }

    @Test
    public void rootInLeft() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(Poly.IDENTITY)
                .build();
        double value = bisection.bisect(-1.0, 0.0);
        assertThat(value, is(closeTo(0.0, TOLERANCE)));
    }

    @Test
    public void rootInRight() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(Poly.IDENTITY)
                .build();
        double value = bisection.bisect(0.0, 1.0);
        assertThat(value, is(closeTo(0.0, TOLERANCE)));
    }

    @Test(expected = MaxIterationsExceeded.class)
    public void maxIterationsExceeded() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(Poly.IDENTITY)
                .withIterations(0)
                .build();
        bisection.bisect(-1.0, 3.0);
    }

    @Test
    public void rootInMidpoint() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(Poly.IDENTITY)
                .withIterations(1)
                .build();
        double value = bisection.bisect(-1.0, 1.0);
        assertThat(value, is(closeTo(0.0, 0.00001)));
    }

    @Test
    public void leftAndRightWithinTolerance() {
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(new Poly(0.0, 10.0))
                .withIterations(1)
                .withTolerance(0.1)
                .build();
        double value = bisection.bisect(-0.02, 0.04);
        assertThat(value, is(closeTo(0.01, 0.00001)));
    }

    @Test
    public void sin() {
        Function<Double,Double> function = new Function<Double,Double>() {
            @Override
            public Double apply(Double value) {
                return Math.sin(value);
            }
        };
        Bisection bisection = BisectionBuilder
                .bisection()
                .withFunction(function)
                .withIterations(100)
                .withTolerance(0.00001)
                .build();
        double value = bisection.bisect(1.0, 4.0);
        assertThat(value, is(closeTo(Math.PI, 0.00001)));
    }
}