/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Tools Interface for all tools behavior.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public interface Tool {
    
    /**
     * Setting the first point of the Tool.
     * 
     * @param theFirstPoint initial point.
     */
    void setFirstPoint(final Point2D theFirstPoint);
    
    /**
     * Setting the second point of the Tool.
     * 
     * @param theSecondPoint the end point.
     */
    void setSecondPoint(final Point2D theSecondPoint);

    /**
     * True or False if the Tool can use fill.
     * 
     * @return if the tool can use fill.
     */
    boolean canItFill();
    
    /**
     * Getting the Tool shape.
     * 
     * @return the shape of the tool to be drawn.
     */
    Shape getToolShape();
}
