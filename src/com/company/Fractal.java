
/**
 * E/13/058
 * De Silva M.D.R.A.M
 * Co225 Project :Fractal Designs
 * Mandelbrot and Julia set Graphical Representaions.
 */
package com.company;
import static java.lang.System.exit;

public class Fractal {
    public static void main(String[] args) throws InterruptedException {

        String fractalType = "";

        if (null != args[0]) {
            switch (args[0]) {
                case "Mandelbrot":
                case "Julia":
                    fractalType = args[0];
                    break;
                default:
                    System.out.println("Invalid Fractal Type. Please try again.");
                    exit(-1);

            }
        }
        switch (fractalType) {
            case "Mandelbrot":
                MandelParameters mandelParameters;
                mandelParameters = validateMandelbrot(args);
                Mandelbrot mandel = new Mandelbrot(mandelParameters);
                mandel.showfigure_Mandel();
                break;

            case "Julia":
                JuliaParameters juliaParameters;
                juliaParameters = validateJulia(args);
                Julia jul = new Julia(juliaParameters);
                jul.showfigure_julia();
                break;

            default:
                break;
        }

    }

    public static MandelParameters validateMandelbrot(String[] args) {
        MandelParameters parameters = new MandelParameters();
        try {
            if (args.length > 1 && args.length < 7) {

                try {
                    if (null != args[1]) {
                        parameters.realMin = Double.parseDouble(args[1]);


                    } else {
                        parameters.realMin = -1;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing real min.Try again. error : " + ex);
                    exit(-1);
                }

                try {

                    if (null != args[2]) {
                        parameters.realMax = Double.parseDouble(args[2]);

                    } else {

                        parameters.realMax = 1;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing real max.Try again. error : " + ex);
                    exit(-1);
                }

                try {
                    if (null != args[3]) {
                        parameters.imaginaryMin = Double.parseDouble(args[3]);
                    }else {
                            parameters.imaginaryMin = -1;
                        }

                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing imaginary min.Try again. error : " + ex);

                    exit(-1);
                }

                try {
                    if (null != args[4]) {
                        parameters.imaginaryMax = Double.parseDouble(args[4]);

                    } else {
                        parameters.imaginaryMax = 1;
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing imaginary max.Try again. error : " + ex);
                    exit(-1);
                }

                try {
                    if (null != args[5]) {
                        parameters.iterationCount = Integer.parseInt(args[5]);
                        if (parameters.iterationCount < 0) {
                            System.out.println("Invalid Iteration count.");
                            exit(-1);
                        }
                    } else {
                        parameters.iterationCount = 1000;
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing iteration count. Try again. error : " + ex);
                   exit(-1);
                }
            } else {
                System.out.println("Parsing default values.");
                parameters.iterationCount = 1000;
                parameters.realMin = -1;
                parameters.realMax = 1;
                parameters.imaginaryMin = -1;
                parameters.imaginaryMax = 1;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(" Parsing Default iteration count : 1000");
            parameters.iterationCount = 1000;
        }


        return parameters;
    }

    public static JuliaParameters validateJulia(String[] args) {
        JuliaParameters JulParameters = new JuliaParameters();
        try {
            if (args.length > 1 && args.length < 5) {

                try {
                    if (null != args[1]) {
                        JulParameters.real = Double.parseDouble(args[1]);
                    } else {
                        JulParameters.real = -0.4;

                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing real part.Try again.:. error : " + ex);
                    exit(-1);
                }


                try {
                    if (null != args[2]) {
                        JulParameters.imaginary = Double.parseDouble(args[2]);


                    } else {
                        JulParameters.imaginary = 0.6;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing imaginary part.Try again. error : " + ex);
                    exit(-1);
                }


                try {
                    if (null != args[3]) {
                        JulParameters.iterationCount = Integer.parseInt(args[3]);
                        if (JulParameters.iterationCount < 0) {
                            System.out.println("Invalid Iteration count.");
                            exit(-1);
                        }
                    } else {
                        JulParameters.iterationCount = 1000;
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Error occurred when parsing iteration count.Try again. error : " + ex);
                    exit(-1);
                }
            } else {
                System.out.println("Parsing default values: c = -0.4 +0.6,iterations = 1000");
                JulParameters.real = -0.4;
                JulParameters.imaginary = 0.6;
                JulParameters.iterationCount = 1000;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Parsing default value for iterations = 1000");

            JulParameters.iterationCount = 1000;

        }

        return JulParameters;
    }
}
