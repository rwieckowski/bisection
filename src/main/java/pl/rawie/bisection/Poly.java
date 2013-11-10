package pl.rawie.bisection;

public class Poly implements Function<Double,Double> {
    public static final Poly IDENTITY = new Poly(0.0, 1.0);

    private final double[] coeffs;

    public Poly(double... coeffs) {
        this.coeffs = coeffs;
    }

    @Override
    public Double apply(Double value) {
        double result = 0.0;
        double var = 1.0;
        for (double coeff : coeffs) {
            result += (coeff * var);
            var *= value;
        }
        return result;
    }
}
