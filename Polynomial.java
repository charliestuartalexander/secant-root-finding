/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
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

public class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        
        // Consider cases of 0/null polynomial, length 1 and length>1
        if(coeff.length == 0||coeff == null){
            this.coeff = new Complex[]{new Complex()};
        }
        else{
            if (coeff.length == 1){
                this.coeff=coeff;
            }
            else{
                int i = coeff.length-1;
                // Count number of zeros at end of polynomial to find order
                while (coeff[i].abs() == 0){
                    i = i-1;
                    if (i == 0){
                        break;
                    }

                }
                // make new array with the length of the order (+1 for index of 0)
                Complex[] newArray = new Complex[i+1];
                for(int k=0; k<i+1; k++){
                newArray[k]=coeff[k];
                }
                this.coeff=newArray;

            }
        }

    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        
        this.coeff = new Complex[]{new Complex()};
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Return the coefficients array.
     *
     * @return  The coefficients array.
     */
    public Complex[] getCoeff() {
        
        return coeff;
    }

    /**
     * Create a string representation of the polynomial.
     * Use z to represent the variable.  Include terms
     * with zero co-efficients up to the degree of the
     * polynomial.
     *
     * For example: (-5.000+5.000i) + (2.000-2.000i)z + (-1.000+0.000i)z^2
     */
    public String toString() {
        // Consider case for zero/empty
        String newstring = "";
        String terms;
        if(coeff.length == 0 || coeff == null ){
            newstring = String.format("(" + new  Complex().toString() + ")") ;
        }
        else{
            for(int i=0;i<coeff.length;i++){
                if (i>0){
                    terms = " + ";
                }
                else{
                    terms = "";
                }
                terms = terms +"(" + coeff[i] + ")";
                if (i>0){
                    terms = terms + "z";
                    if (i>1){
                        terms = terms + "^" + i;
                    }
                }
                newstring = newstring + terms;

            }
        }
        return newstring;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        
        return (coeff.length-1);
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        Complex total = new Complex();
        // Case for 0/empty polynomial
        if(coeff.length == 0||coeff == null){
            return total;
        }
        else{
            for(int i = coeff.length-1; i>=0; i--){
                total = coeff[i].add(z.multiply(total)) ;
            }
            return total ;
        }
         
    }

    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // Tests
        Polynomial p1 = new Polynomial(new Complex[]{new Complex(3,8),new Complex(4.7,-9),new Complex(4.5657,121)});
        System.out.println(p1);
        System.out.println(p1.degree());
        System.out.println(p1.evaluate(new Complex(4.2,-6)));
  

    }
}