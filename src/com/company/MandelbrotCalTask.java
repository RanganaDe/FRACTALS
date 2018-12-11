package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Perform actions using Threads for mandelbrot set.
 */

public class MandelbrotCalTask implements Callable<Picture> {

    private double min_re;
    private double max_re;
    private double min_im;
    private double max_im;
    private int n = 800;
    private int number_of_Iterations;
    private int x, y;
    private Picture picture;
//uncomment to see process time
    // private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");

    public MandelbrotCalTask(double min_re, double max_re, double min_im, double max_im, int n, int number_of_Iterations, int x, int y, Picture picture) {
        this.min_re = min_re;
        this.max_re = max_re;
        this.min_im = min_im;
        this.max_im = max_im;
        this.n = n;
        this.number_of_Iterations = number_of_Iterations;
        this.x = x;
        this.y = y;
        this.picture = picture;
    }

    @Override
    public Picture call() throws Exception {
        double c_re = min_re + x * (max_re - min_re) / n;
        double c_im = max_im - y * (max_im - min_im) / n;
        Complex z0 = new Complex(c_re, c_im);

        picture.set(x, y, Mandelbrot.mand(z0, number_of_Iterations));
        // uncomment to see process time
        // if (x % 400 == 0) {
        // System.out.println("Ran Task : " + sdf.format(new Date(System.currentTimeMillis())));
        //}

        return picture;
    }
}
