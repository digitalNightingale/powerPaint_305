/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Square class to create a new Square.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class Square extends AbstractTool {

    /** Square. */
    private final Rectangle2D.Double mySquare;
    
    /**
     * Constructs the new Square.
     * 
     * @param theFirstPoint is the first 2D point of shape.
     * @param theSecondPoint is the second 2D point of the shape.
     */
    public Square(final Point2D theFirstPoint, final Point2D theSecondPoint) {
        super(theFirstPoint, theSecondPoint);
        mySquare = new Rectangle2D.Double();
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
     * @return mySquare which is the tool Shape.
     */
    @Override
    public Shape getToolShape() {
        final double minX = Math.min(getFirstPoint().getX(), getSecondPoint().getX());
        final double minY = Math.min(getFirstPoint().getY(), getSecondPoint().getY());
        final double width = Math.max(getFirstPoint().getX(), getSecondPoint().getX()) - minX;
        mySquare.setFrame(minX, minY, width, width);
        
//        final double x = Math.pow(getSecondPoint().getX() - getFirstPoint().getX(), 2);
//        final double y = Math.pow(getSecondPoint().getY() - getFirstPoint().getY(), 2);
//        final double hyp = Math.sqrt(x + y);
//        final int width = (int) (hyp * (Math.sqrt(2) / 2));
//        mySquare.setFrame(getFirstPoint().getX(), getFirstPoint().getY(), 
//                          width, width);
        return (Shape) mySquare.clone();
    }
}