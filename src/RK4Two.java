import java.io.*;
/*
PROGRAMMER: Brett Langford

COURSE: CSCI 3321

DATE: May 7, 2020

ASSIGNMENT: Number 5

ENVIRONMENT: Windows 10, IntelliJ IDEA

FILES INCLUDED: RK4Two.java, RK4Coefficients.java, RK4E.java

PURPOSE: To obtain a numerical solution to a two-point boundary-value problem.

INPUT: t0 (double), t (double), x0 (double), x1 (double), n (double)

PRECONDITIONS: Assuming that we only wish to test the singular function x + 0.09 * x^2 + cos(5t), and we want 40 steps from 0 to 1

OUTPUT: the x(0), x(1), error, and the area of x(t) from 0 to 1

ALGORITHM:
1. get coefficients from RK4Coefficients
2. call computeRK4 using coefficients
    A.  for loop:
        i. calculate h
        ii. j = 1; is j <= 40? j++
            a. RK4 algorithm lives here
            b. return x4 after 40 iterations
3. call secant method
    A. while loop:
        i. x2 = secant method
        ii. print x0, x1, and error
        iii. is error > tolerance?
            a.  yes: goto A
            b. no: return x0
3. print x0
4. print RK4(x0)

Formulas: Fourth-Order Runge-Kutta Method, Secant Method
*/
public class RK4Two {
    public static void main (String[] args) throws IOException {
        RK4E rk4 = new RK4E(0, 1, 40);
        double x0 = secant(0.7, 1.0, Math.pow(10, -4));
        System.out.printf("x(0) = %f\n", x0);
        System.out.printf("x(t) from 0 to 1: %f", rk4.solve(x0));
    }

    private static double g(double x)  {
        RK4E rk4 = new RK4E(0, 1, 40);
        double ans = x + rk4.solve(x) - 3.0;
        return x + rk4.solve(x) - 3.0;
    }

    /*
    Secant Method
     */
    private static double secant(double x0, double x1, double tol) {
        double x2; double err;
        err = 1;
        x2 = 0;
        while (err > tol) {
            x2 = x1 - g(x1) * ((x1 - x0) / (g(x1) - g(x0)));
            err = Math.abs(x2-x1);
            System.out.printf("x(0) = %f     | x(1) = %f     | err = %f\n", x0, x1, err);
            x0 = x1;
            x1 = x2;

        }
        return x2;
    }
}
