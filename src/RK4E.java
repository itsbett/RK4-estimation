
public class RK4E {
    private double [] A;
    private double [][] B;
    private double [] C;
    private int n;
    private double h;

    public RK4E (double t0, double t, int n) {
        RK4Coefficients coefficients = new RK4Coefficients(1.0/4.0, 3.0/4.0);
        this.A = coefficients.getA();
        this.B = coefficients.getB();
        this.C = coefficients.getC();
        this.n = n;
        this.h = (t - t0) / n;
    }
   private static double f(double t, double x) {
        return x + 0.09 * Math.pow(x, 2) + Math.cos(10 * t);  // x + 0.09 * x^2 * cos(10t)
    }

    public double solve(double x) {
        double f0; double x1; double f1; double x2; double f2; double x3; double f3; double err;
        double t0; double x0; double x4 = 0; int i = 1;

            t0 = 0;
            x0 = x;

            for (int j = 1; j <= n; j++) {

                f0 = f(t0, x0);

                x1 = x0 + h * B[0][0] * f0;                                         // x1 = x0 + h * B10 * f0
                f1 = f(t0 + A[1] * h, x1);                                       // f1 = f(t0 + a1 * h, x1)

                x2 = x0 + h * (B[1][0] * f0 + B[1][1] * f1);                        // x2 = x0 + h * B20 * f0 + B21 * f1
                f2 = f(t0 + A[2] * h, x2);                                       // f2 = f(t0 + a2 * h, x2)

                x3 = x0 + h * (B[2][0] * f0 + B[2][1] * f1 + B[2][2] * f2);         // x3 = x + h * (B30 * f0 + B31 * f1  + B32 * f2)
                f3 = f(t0 + A[3] * h, x3);                                       // f3 = f(t0 + a3 * h, x3)

                x4 = x0 + h * (C[0] * f0 + C[1] * f1 + C[2] * f2 + C[3] * f3);      // x0 + h * (c0 * f0 + c1 * f1 + c2 * f2 + c3 * f3)

                t0 += h;                                                            // increment time to get closer to t = 2
                x0 = x4;                                                            // x0 = x4 to continue loop for #steps times
            }
        return x4;
    }
    public void printCoefficients () {
        System.out.printf("A Coefficients: %f %f %f %f\n" +
                        "B Coefficients: B10: %f B20: %f B21: %f B30: %f B31: %f B32: %f\n" +
                        "C Coefficients: %f %f %f %f", A[0], A[1], A[2], A[3], B[0][0], B[1][0], B[1][1], B[2][0], B[2][1],
                B[2][2], C[0], C[1], C[2], C[3]);
    }
}
