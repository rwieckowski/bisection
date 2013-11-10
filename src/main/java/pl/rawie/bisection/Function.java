package pl.rawie.bisection;

public interface Function<F, T> {
    T apply(F value);
}
