/*
 * PROJECT II: Secant.java
 *
 * This file contains a template for the class Secant. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Secant root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 * 
 * Tasks:
 *
 * 1) Complete this class with the indicated methods and instance variables.
 *
 * 2) Fill in the following fields:
 *
 * NAME: Charlie Alexander
 * UNIVERSITY ID: 2109968
 * DEPARTMENT: Maths
 */

public class Secant {
    /**
     * The maximum number of iterations that should be used when applying
     * Secant. Ensure this is *small* (e.g. at most 50) otherwise your
     * program may appear to freeze.
     */
    public static final int MAXITER = 20;

    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and _not_ explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Secant.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Secant method to.
     */
    private Polynomial f;


    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An enumeration that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   OK: Nothing went wrong.
     *   ZERO: Difference went to zero during the algorithm.
     *   DNF: Reached MAXITER iterations (did not finish)
     */
    enum Error { OK, ZERO, DNF };
    private Error err = Error.OK;
    
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor.
     *
     * @param p  The polynomial used for Secant.
     */
    public Secant(Polynomial p) {
        this.f=p;
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public Error getError() {
        // You need to fill in this method with the correct code.
        return err;
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
        // You need to fill in this method with the correct code.
        return numIterations;
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        // You need to fill in this method with the correct code.
        return root;
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
        // You need to fill in this method with the correct code.
        return f;
    }

    // ========================================================
    // Secant method (check the comment)
    // ========================================================
    
    /**
     * Given two complex numbers z0 and z1, apply Secant to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to OK.
     *   - At some point the absolute difference between f(zn) and f(zn-1) becomes zero. 
     *     In this case, set err to ZERO and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to DNF and return.
     *
     * @param z0,z1  The initial starting points for the algorithm.
     */
    public void iterate(Complex z0, Complex z1) {
        Complex[] z = new Complex[MAXITER+2];
        numIterations=0;
        z[0]=z0;
        z[1]=z1;
        // Check if conditions are satisfied for initial z0 and z1 (include Zero error case)
        if ((((z[1].add((z[0]).negate())).abs())<TOL) && (f.evaluate(z[1]).abs())<TOL){
            err=Error.OK;
            root=z[1];
            numIterations=1;
        }
        else if(((f.evaluate(z[1]).add(f.evaluate(z[0]).negate())).abs())<TOL){
            err=Error.ZERO;
        }
        else{
            for (int i=1;i<MAXITER+1;i++){
                // Iterate secant formula
                numIterations=numIterations+1;
                z[i+1]=z[i].add(((f.evaluate(z[i])).negate()).multiply(((z[i].add(z[i-1].negate())).divide(f.evaluate(z[i]).add((f.evaluate(z[i-1]).negate()))))));
                if ((((z[i+1].add((z[i]).negate())).abs())<TOL) && (f.evaluate(z[i+1]).abs())<TOL){
                    // Case where there are no errors
                    err=Error.OK; 
                    root=z[i+1];
                    break;
                }
                    // Consider Zero error
                else if((((f.evaluate(z[i+1])).add(f.evaluate(z[i]).negate())).abs())<TOL){
                    err=Error.ZERO;
                    break;
                }
                // Consider DNF error
            if (numIterations==MAXITER){
                err=Error.DNF;
            }

        }
            
        }
    }
      
    // ========================================================
    // Tester function.
    // ========================================================
    
    public static void main(String[] args) {
        // Basic tester: find a root of f(z) = z^3-1.
        Complex[] coeff = new Complex[] { new Complex(-1.0,0.0), new Complex(), new Complex(), new Complex(1.0,0.0) };
        Polynomial p    = new Polynomial(coeff);
        Secant     s    = new Secant(p);
                
        s.iterate(new Complex(), new Complex(1.0,1.0));
        System.out.println(s.getNumIterations());   // 12
        System.out.println(s.getError());           // OK
        System.out.println(s.getRoot());

        // Test 2
        Complex[] coeff2 = new Complex[] { new Complex(5,-6)};
        Polynomial p2    = new Polynomial(coeff2);
        Secant     s2    = new Secant(p2);
                
        s2.iterate(new Complex(), new Complex(1.0,1.0));
        System.out.println(s2.getNumIterations());  
        System.out.println(s2.getError());          
        System.out.println(s2.getRoot());

        // Test 3
        Complex[] coeff3 = new Complex[] { new Complex(-1,0)};
        Polynomial p3    = new Polynomial(coeff2);
        Secant     s3    = new Secant(p2);
                
        s3.iterate(new Complex(), new Complex(500.0,-2.0));
        System.out.println(s3.getNumIterations());  
        System.out.println(s3.getError());          
        System.out.println(s3.getRoot());

    }
}
