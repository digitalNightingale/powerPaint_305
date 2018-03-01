/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Creating a Color Icon for the MenuBar.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class ColorSwatchIcon implements Icon {

    /** The height of the color icon. */
    private static final int HEIGHT = 14;

    /** The width of the color icon. */
    private static final int WIDTH = 14;

    /** The color of the icon. */
    private Color myIconColor;

    /**
     * Constructor for initializing fields.
     * 
     * @param theColor The color of the icon.
     */
    public ColorSwatchIcon(final Color theColor) {
        myIconColor = theColor;
    }

    /**
     * To change the color of the icon.
     * 
     * @param theColor The color for changing the icon.
     */
    public void setColor(final Color theColor) {
        myIconColor = theColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphics,
                          final int theX, final int theY) {
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setPaint(myIconColor);
        g2d.fillRect(theX, theY, WIDTH, HEIGHT);
        g2d.setPaint(Color.BLACK);
        g2d.drawRect(theX, theY, WIDTH, HEIGHT);
    }

}
