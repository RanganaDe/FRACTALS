package com.company;

/**
 * This class is use to make complex object.Complex functions needed only are created here.
 * Other methods such as sqrt..etc can also be written here.
 */

public class Complex {
        private final double re;   // the real part
        private final double im;   // the imaginary part


        public Complex(double real, double imag) {
            re = real;
            im = imag;
        }

    /**abs(absolute value)
     * hypot : method used to get the |re^2+im^2|
     *
     */

        public double abs() {
            return Math.hypot(re, im);
        }

    /**Complex plus
     * function: add two complex numbers.
     * returns a new Complex object whose value is (this + b)
     */

        public Complex plus(Complex b) {
            Complex a = this;
            double real = a.re + b.re;
            double imag = a.im + b.im;
            return new Complex(real, imag);
        }

    /**Complex Multiplication
     * performes the mulipication of two comlex numbers
     * returns a new Complex object whose value is (this * b)
     */


        public Complex times(Complex b) {
            Complex a = this;
            double real = a.re * b.re - a.im * b.im;
            double imag = a.re * b.im + a.im * b.re;
            return new Complex(real, imag);
        }




    }


