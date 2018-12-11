package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Perform actions using Threads for Julia set.
 */
public class JuliaCalTask implements Callable<Picture> {
    private double x_min;
    private double y_min;
    private double width;
    private double height;
    private int n = 800;
    private Picture picture;
    private int row, col;
    private int number_of_Iterations;
    private Complex c;
    // uncomment to check performance time
    // private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");


    public JuliaCalTask(double y_min, double width, double height, int n, Picture picture, int row, int col, int number_of_Iterations, double x_min, Complex c) {
        this.y_min = y_min;
        this.width = width;
        this.height = height;
        this.n = n;
        this.picture = picture;
        this.c = c;
        this.row = row;
        this.col = col;
        this.number_of_Iterations = number_of_Iterations;
        this.x_min = x_min;
    }

    @Override
    public Picture call() throws Exception {
        double x = x_min + col * width / n;
        double y = y_min + row * height / n;

        Complex z = new Complex(x, y);


        picture.set(col, row, Julia.Jul(c, z, number_of_Iterations));
//uncomment to check performance time
        // if (row % 400 == 0)
        //  System.out.println("Ran Task : " + sdf.format(new Date(System.currentTimeMillis())));

        return picture;
    }
}
