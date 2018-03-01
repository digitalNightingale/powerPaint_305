/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Ellipse class to create a new ellipse.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class Ellipse extends AbstractTool {
    
    /** Ellipse. */
    private final Ellipse2D.Double myEllipse;
    
    /**
     * Constructs the new Ellipse.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Ellipse(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
        myEllipse = new Ellipse2D.Double();
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
     * @return myEllipse which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        myEllipse.setFrameFromDiagonal(getFirstPoint(), getSecondPoint());
        return (Shape) myEllipse.clone();
    }
}