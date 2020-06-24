/*
Objectives: This class generates the coefficients for the RK4 method, given a certain a1 and a2.
Inputs: a1 (double), a2 (double)
Outputs: A (double[]), B (double[][]), C (double[])
 */

public class RK4Coefficients {
    private double a1;
    private double a2;

    public RK4Coefficients(double a1, double a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    private double[] A() {
        return new double[] { 0, this.a1, this.a2, 1.0};  // a0 will always equal 0 and a3 will always equal 1.0
    }

    private double[][] B() {
        double[][] B = {
                {this.a1}, // B10
                {0.0, //B20
                        ((this.a1 - this.a2) * this.a2) / (2 * this.a1 * (-1 + 2 * this.a1))}, //B21
                {0.0, //B30
                        ((-1 + this.a1)*(-2 + this.a1 + 5 * this.a2 - 4 * Math.pow(this.a2, 2)))/(2*this.a1*(this.a1-this.a2)*(3-4*this.a2+this.a1*(-4+6*this.a2))), //B31
                        ((-1+this.a1) * (-1 + 2 * this.a1) * (-1 + this.a2))/((this.a1 - this.a2) * this.a2 * (3 - 4 * this.a2 + this.a1*(-4 + 6 * this.a2)))} //B32
                };
        B[1][0] = this.a2 - B[1][1];
        B[2][0] = 1.0 - B[2][1] - B[2][2];
        return B;
    }

    private double[] C() {
        return new double[]{
               (1 - 2 * this.a2 + this.a1 * (-2 + 6 * this.a2))/(12 * this.a1 * this.a2),
                (-1 + 2 * this.a2) / ( 12 * (-1 + this.a1) * this.a1 * (this.a1 - this.a2)),
                (1 - 2 * this.a1) / (12 * (this.a1 - this.a2) * (-1 + this.a2) * this.a2),
                (3 - 4 * this.a2 + this.a1 * (-4 + 6 * this.a2)) / (12 * (-1 + this.a1) * (-1 + this.a2))
        };
    }
    public double[] getA() {
        return A();
    }
    public double[][] getB() {
        return B();
    }
    public double[] getC() {
        return C();
    }
}
