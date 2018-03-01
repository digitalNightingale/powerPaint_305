/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Circle class to create a circle.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class Circle extends AbstractTool {
    
    /** Circle. */
    private final Ellipse2D.Double myCircle;
    
    /**
     * Constructs the new Circle.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Circle(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
        myCircle = new Ellipse2D.Double();
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
     * @return myCircle which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        final double minX = Math.min(getFirstPoint().getX(), getSecondPoint().getX());
        final double minY = Math.min(getFirstPoint().getY(), getSecondPoint().getY());
        final double maxDia = Math.max(getFirstPoint().getX(), getSecondPoint().getX()) - minX;
        myCircle.setFrame(minX, minY, maxDia, maxDia);
        
//        final double x = Math.pow(getSecondPoint().getX() - getFirstPoint().getX(), 2);
//        final double y = Math.pow(getSecondPoint().getY() - getFirstPoint().getY(), 2);
//        final double hyp = Math.sqrt(x + y);
//        final int maxDia = (int) (hyp * (Math.sqrt(2) / 2));
//        myCircle.setFrame(getFirstPoint().getX(), getFirstPoint().getY(), maxDia, maxDia);
        return (Shape) myCircle.clone();
    }
}
