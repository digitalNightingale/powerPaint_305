/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Pencil class to create a new Pencil.
 * 
 * @author Leah
 * @version November 2016
 */
public class Pencil extends AbstractTool {

    /** Pencil. */
    private Path2D.Double myPencil;
    
    /**
     * Constructs the new Pencil.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Pencil(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
        myPencil = new Path2D.Double();
    }
    
    /**
     * Sets the first point for the path.
     */
    @Override
    public void setFirstPoint(final Point2D theFirstPoint) {
        myPencil = new Path2D.Double();
        myPencil.moveTo(theFirstPoint.getX(), theFirstPoint.getY());
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
     * @return Pencil path which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        final Point2D secondPoint = getSecondPoint();
        myPencil.lineTo(secondPoint.getX(), secondPoint.getY());
        return (Shape) myPencil.clone();
    }
}