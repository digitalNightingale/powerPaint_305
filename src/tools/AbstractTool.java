/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * The Abstract Tool class.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public abstract class AbstractTool implements Tool {
    
    /** The first point of Tool. */
    private Point2D myFirstPoint;
    
    /** The second point of Tool. */
    private Point2D mySecondPoint;
    
    /**
     * Initialize the instance fields.
     * 
     * @param theFirstPoint the first point of the tools shape.
     * @param theSecondPoint the second point of the tools shape.
     */
    protected AbstractTool(final Point2D theFirstPoint, final Point2D theSecondPoint) {

        myFirstPoint = theFirstPoint;
        mySecondPoint = theSecondPoint;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFirstPoint(final Point2D theFirstPoint) {
        myFirstPoint = theFirstPoint;  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSecondPoint(final Point2D theSecondPoint) {
        mySecondPoint = theSecondPoint;   
    }

    /** 
     * Getting the first point of the tools shape.
     * 
     * @return myFirstPoint of the tool shape.
     */
    public Point2D getFirstPoint() {
        return myFirstPoint;
    }
    
    /** 
     * Getting the second point of the tools shape.
     * 
     * @return mySecondPoint tool shape.
     */
    public Point2D getSecondPoint() {
        return mySecondPoint;
    }

    /** 
     * Getting the Shape of tool.
     *  
     * @return shape of the tool.
     */
    public abstract Shape getToolShape();
}

