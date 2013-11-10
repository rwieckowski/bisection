package pl.rawie.bisection;

public class Bisection {
    private Function<Double, Double> function;
    private int iterations;
    private double tolerance;

    public Bisection(Function<Double, Double> function, int iterations, double tolerance) {
        this.function = function;
        this.iterations = iterations;
        this.tolerance = tolerance;
    }

    public double bisect(double left, double right) {
        if (left > right)
            throw new IllegalArgumentException("left greater than right");
        if (Math.signum(function.apply(left)) == Math.signum(function.apply(right)))
            throw new IllegalArgumentException("f(left) and f(right) have the same sign");

        if (Math.abs(function.apply(left)) < tolerance)
            return left;
        if (Math.abs(function.apply(right)) < tolerance)
            return right;

        for (int i = 1; i <= iterations; i++) {
            double midpoint = (left + right) / 2;
            if (Math.abs(function.apply(midpoint)) < tolerance || Math.abs(left - right) < tolerance)
                return midpoint;
            if (Math.signum(function.apply(left)) == Math.signum(function.apply(midpoint)))
                left = midpoint;
            else
                right = midpoint;
        }

        throw new MaxIterationsExceeded(iterations);
    }
}
