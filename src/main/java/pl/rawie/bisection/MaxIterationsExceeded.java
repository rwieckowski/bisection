package pl.rawie.bisection;

public class MaxIterationsExceeded extends RuntimeException{
    public MaxIterationsExceeded(int iterations) {
        super(String.valueOf(iterations));
    }
}
