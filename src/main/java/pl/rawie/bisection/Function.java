package pl.rawie.bisection;

public interface Function<D, C> {
    C apply(D value);
}
