package pl.rawie.bisection;

public interface BisectableFunction<T> extends ContinuousFunction<T> {
    int compareToZero(double value);

    boolean closeToZero(double value);
}
