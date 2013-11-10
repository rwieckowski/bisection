package pl.rawie.bisection;

public class Bisection<T> {
    private BisectableFunction<T> function;
    private int iterations;
    private double tolerance;

    public Bisection(BisectableFunction<T> function, int iterations, double tolerance) {
        this.function = function;
        this.iterations = iterations;
        this.tolerance = tolerance;
    }

    public double bisect(double left, double right) {
        if (left > right)
            throw new IllegalArgumentException("left greater than right");
        if (Math.signum(function.compareToZero(left)) == Math.signum(function.compareToZero(right)))
            throw new IllegalArgumentException("f(left) and f(right) have the same sign");

        if (function.closeToZero(left))
            return left;
        if (function.closeToZero(right))
            return right;

        for (int i = 1; i <= iterations; i++) {
            double midpoint = (left + right) / 2;
            if (function.closeToZero(midpoint) || Math.abs(left - right) < tolerance)
                return midpoint;
            if (Math.signum(function.compareToZero(left)) == Math.signum(function.compareToZero(midpoint)))
                left = midpoint;
            else
                right = midpoint;
        }

        throw new MaxIterationsExceeded(iterations);
    }
}
