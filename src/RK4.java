import java.io.*;
/*
PROGRAMMER: Brett Langford

COURSE: CSCI 3321

DATE: April 12, 2020

ASSIGNMENT: Number 4

ENVIRONMENT: Windows 10, IntelliJ IDEA

FILES INCLUDED: RK4.java, RK4Coefficients.java, output.txt

PURPOSE: To estimate the value of a given ordinary differential equation by using the fourth-order Runge-Kutta method.

INPUT: a1 (double), a2 (double)

PRECONDITIONS: Assuming that we only wish to test the singular function x^2*cos(5t), and we want 7 steps from 20 to 1280

OUTPUT: the step #, the size of h, and a table of the Ts and Hs for each step.

ALGORITHM:
1. get coefficients from RK4Coefficients
2. call computeRK4 using coefficients
    A.  for loop:
        i. calculate h
        ii. output h values
        iii. j = 1; is j <= i? j++
            a. RK4 algorithm lives here
            b. output X and T values
        iv. i *= 2
        v. is the # of steps < 1280? goto A
3. close outputfile

Formulas: Fourth-Order Runge-Kutta Method
*/
public class RK4 {
    public static void main (String[] args) throws IOException {
       RK4Coefficients coefficients = new RK4Coefficients(1.0/4.0, 3.0/4.0);  // a1 = 1/5, a2 = 3/5
        double t0 = 0;
        printCoefficients(coefficients);
        computeRK4(t0, 1, coefficients.getA(), coefficients.getB(), coefficients.getC());
    }

    /*
    The differential equation we are estimating the solutoin for.
     */
    private static double f(double t, double x) {
        return Math.pow(x, 2) * Math.cos(5 * t);  // x^2 * cos(5t)
    }

    /*
    The RK4 method itself.
     */
    private static void computeRK4(double t, double x, double[] A, double[][] B, double[] C) throws IOException {
        double h; double f0; double x1; double f1; double x2; double f2; double x3; double f3; double err;
        double t0; double x0; double x4 = 0; int i = 20;
        StringBuilder output = new StringBuilder();
        PrintWriter outputStream = new PrintWriter(new FileWriter("C:\\Users\\Brett\\OneDrive\\School\\Spring 2020\\CSCI 3321 - Numerical Methods\\Homework\\Assignment 4\\output.txt"));
        try {
            for (; i <= 1280; i *= 2) {
                outputStream.printf("Step #%d\n", i);
                h = 0.1 / (i / 20);
                t0 = t;
                x0 = x;
                outputStream.printf(" _______________\n");
                outputStream.printf("| h: %.8f |\n", h);
                outputStream.println("---------------------------------------");
                for (int j = 1; j <= i; j++) {
                    outputStream.printf("| T: %f |", t0);
                    f0 = f(t0, x0);

                    x1 = x0 + h * B[0][0] * f0;                                         // x1 = x0 + h * B10 * f0
                    f1 = f(t0 + A[1] * h, x1);                                       // f1 = f(t0 + a1 * h, x1)

                    x2 = x0 + h * (B[1][0] * f0 + B[1][1] * f1);                        // x2 = x0 + h * B20 * f0 + B21 * f1
                    f2 = f(t0 + A[2] * h, x2);                                       // f2 = f(t0 + a2 * h, x2)

                    x3 = x0 + h * (B[2][0] * f0 + B[2][1] * f1 + B[2][2] * f2);         // x3 = x + h * (B30 * f0 + B31 * f1  + B32 * f2)
                    f3 = f(t0 + A[3] * h, x3);                                       // f3 = f(t0 + a3 * h, x3)

                    x4 = x0 + h * (C[0] * f0 + C[1] * f1 + C[2] * f2 + C[3] * f3);      // x0 + h * (c0 * f0 + c1 * f1 + c2 * f2 + c3 * f3)
                    outputStream.printf(" X: %.16f |\n", x4);
                    t0 += h;                                                            // increment time to get closer to t = 2
                    x0 = x4;                                                            // x0 = x4 to continue loop for #steps times
                }
                outputStream.println("---------------------------------------");
            }
        } finally {
            outputStream.close();
        }
    }

/*
This method is simply for troubleshooting the coefficients.
 */
    private static void printCoefficients (RK4Coefficients coefficients) {
        double[] A = coefficients.getA();
        double[][] B = coefficients.getB();
        double[] C = coefficients.getC();

        System.out.printf("A Coefficients: %f %f %f %f\n" +
                "B Coefficients: B10: %f B20: %f B21: %f B30: %f B31: %f B32: %f\n" +
                "C Coefficients: %f %f %f %f", A[0], A[1], A[2], A[3], B[0][0], B[1][0], B[1][1], B[2][0], B[2][1],
                B[2][2], C[0], C[1], C[2], C[3]);
    }
}
