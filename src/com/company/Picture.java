package com.company;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public final class Picture {

    private BufferedImage image;
    private JFrame frame;
    private String filename;
    private boolean isOriginUpperLeft = true;
    private final int width, height;

    public Picture(int width, int height) {
        if (width < 0) throw new IllegalArgumentException("can't be negative");
        if (height < 0) throw new IllegalArgumentException("can't be negative");
        this.width = width;
        this.height = height;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        filename = width + "-by-" + height + " Window";
    }


    public JLabel getJLabel() {
        if (image == null) return null;
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }


    public void show() {


        if (frame == null) {
            frame = new JFrame();
            frame.setContentPane(getJLabel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle(filename);
            frame.setResizable(true);
            frame.pack();
            frame.setVisible(true);
        }

        frame.repaint();
    }


    public int height() {
        return height;
    }


    public int width() {
        return width;
    }

    public void set(int col, int row, Color color) {
        if (col < 0 || col >= width())
            throw new IndexOutOfBoundsException("Error in handling column" );
        if (row < 0 || row >= height())
            throw new IndexOutOfBoundsException("Error in handling row");
        if (color == null) throw new NullPointerException("can't set Color to null");
        if (isOriginUpperLeft) image.setRGB(col, row, color.getRGB());
        else image.setRGB(col, height - row - 1, color.getRGB());
    }
}