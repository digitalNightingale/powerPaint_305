/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Rectangle class to create a new rectangle.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class Rectangle extends AbstractTool {
    
    /** Rectangle. */
    private final Rectangle2D.Double myRectangle;
    
    /**
     * Constructs the new Rectangle.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Rectangle(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
        myRectangle = new Rectangle2D.Double();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canItFill() {
        return true;
    }
    
    /**
     * Gets the tool shape.
     * 
     * @return myRectangle which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        myRectangle.setFrameFromDiagonal(getFirstPoint(), getSecondPoint());
        return (Shape) myRectangle.clone();    
    }
}