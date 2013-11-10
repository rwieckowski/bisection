package pl.rawie.bisection;

public abstract class BisectableRealFunction implements BisectableFunction<Double> {
    double tolerance;

    protected BisectableRealFunction(double tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public int compareToZero(double value) {
        return new Double(0.0).compareTo(apply(value));
    }

    @Override
    public boolean closeToZero(double value) {
        return Math.abs(apply(value)) < tolerance;
    }
}
