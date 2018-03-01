/*
 * TCSS 305 - Autumn 2016
 * Assignment 5a - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Line class to create a Line.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class Line extends AbstractTool {
       
    /**
     * Constructs the new Line.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Line(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canItFill() {
        return false;
    }
    
    /**
     * Gets the tool shape.
     * 
     * @return a line which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        return (Shape) new Line2D.Double(getFirstPoint(), getSecondPoint()).clone();
    }
}
