package com.company;


import java.awt.*;
import java.util.concurrent.*;

/**
 * Here the mandelbrot iteration function is established and all the set methods are written.
 */

public class Mandelbrot {
    // static Color[] colors = new Color[255];
    private static Color[] colors = new Color[5];

    private double min_re;
    private double max_re;
    private double min_im;
    private double max_im;
    private final int PANEL_HEIGHT = 800;
    private int number_of_Iterations;

    /**
     * Mand : follows the mandelbrot algorithm and checks if the points are within  or outside the mandelbrot set.
     * z0  : Initial complex number.
     * max  : maximum number of times to iterate
     *
     * @return : if true,outside the set and if false within the set.
     */
    public static Color mand(Complex z0, int max) {
        Complex z = z0;
        for (int t = 0; t < max; t++) {
            if (z.times(z).abs() > 4.0) return colors[t % 5]; // or if you use more colors --> colors[t%255]
            z = z.times(z).plus(z0);

        }
        return new Color(0, 0, 0);


    }


    /**
     * min_re  : minimum real value;
     * max_re  : maximum real value
     * min_im  : minimum imaginary value
     * max_im  : maximum imaginary value
     * Here you can use a set of 5 colors or a set of 255 colors.I 've used 5 colors to see the iteration regions more clearly.
     * Other method can be used by uncommenting the code block below.
     */
    public Mandelbrot(MandelParameters para) {
        this.min_re = para.realMin;
        this.max_re = para.realMax;
        this.min_im = para.imaginaryMin;
        this.max_im = para.imaginaryMax;
        this.number_of_Iterations = para.iterationCount;

       /* for (int t = 0; t < 255; t++) {
            int r = t;
            int g =t%255;
            int b = t%255;
            colors[t] = new Color(r, g, b);
        }
        */
        colors[0] = new Color(12, 12, 54);
        colors[1] = new Color(239, 255, 59);
        colors[2] = new Color(255, 23, 19);
        colors[3] = new Color(5, 54, 4);
        colors[4] = new Color(56, 255, 228);


    }


    public void showfigure_Mandel() throws InterruptedException {
        Picture picture = new Picture(PANEL_HEIGHT, PANEL_HEIGHT);

        int THREAD_POOL_SIZE = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE); // load threads to do tasks.

        for (int x = 0; x < PANEL_HEIGHT; x++) {

            for (int y = 0; y < PANEL_HEIGHT; y++) {

                //schedule an asynchronous task to calculate the mandelbrot position.
                executorService.submit(new MandelbrotCalTask(min_re, max_re, min_im, max_im, PANEL_HEIGHT, number_of_Iterations, x, y, picture));

            }
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        //Render the picture after all above scheduled tasks are complete.
        picture.show();


    }


}
