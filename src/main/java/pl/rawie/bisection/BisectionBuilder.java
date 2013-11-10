package pl.rawie.bisection;

public class BisectionBuilder {
    private Function<Double, Double> function = Poly.IDENTITY;
    private int iterations = 100;
    private double tolerance = 0.00001;

    private BisectionBuilder() {
    }

    public static BisectionBuilder bisection() {
        return new BisectionBuilder();
    }

    public BisectionBuilder withFunction(Function<Double, Double> function) {
        this.function = function;
        return this;
    }

    public BisectionBuilder withIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public BisectionBuilder withTolerance(double tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public Bisection build() {
        return new Bisection(function, iterations, tolerance);
    }
}
