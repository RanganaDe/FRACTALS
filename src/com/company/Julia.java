package com.company;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Here the Julia iteration function is established and all the set methods are written.
 */
public class Julia {
    private double real;
    private double imaginary;
    private int number_of_Iterations;

    private final double Y_MIN_DEFAULT = -1.0;
    private final double WIDTH_DEFAULT = 2.0;
    private final double HEIGHT_DEFAULT = 2.0;
    private final int PANEL_HEIGHT = 800;
    private final int THREAD_POOL_SIZE = 500;
    static Color[] colors = new Color[255];


    static Color Jul(Complex c, Complex z, int max) {
        for (int t = 0; t < max; t++) {
            if (z.times(z).abs() > 4.0)
                return colors[t % 125 + 100];// this return statement can be modified to get the colors you wish.
            z = z.times(z).plus(c);
        }

        return new Color(0, 0, 0);
    }


    public Julia(JuliaParameters para) {
        this.real = para.real;
        this.imaginary = para.imaginary;
        this.number_of_Iterations = para.iterationCount;

//RGB values can be manually set from the following loop.
        for (int t = 0; t < 255; t++) {
            int r = t;
            int g = 255 - t;
            int b = 255 - t % 255;
            colors[t] = new Color(r, g, b);
        }


    }


    public void showfigure_julia() throws InterruptedException {
        Complex c = new Complex(real, imaginary);
        Picture picture = new Picture(PANEL_HEIGHT, PANEL_HEIGHT);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // load threads to execute tasks


        for (int col = 0; col < PANEL_HEIGHT; col++) {
            for (int row = 0; row < PANEL_HEIGHT; row++) {

                double x_MIN_DEFAULT = -1.0;
                executorService.submit(new JuliaCalTask(Y_MIN_DEFAULT, WIDTH_DEFAULT, HEIGHT_DEFAULT, PANEL_HEIGHT, picture, row, col, number_of_Iterations, x_MIN_DEFAULT, c));


            }
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        picture.show();
    }


}

