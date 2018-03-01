/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * Creating Shapes to be drawn.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class DrawShape {
    
    /** My fill Color for the shape. */
    private final Color myFillColor;
    
    /** If the shape should be filled. */
    private final boolean myIsFilled;
    
    /** If the shape can be filled. */
    private final boolean myCanItFill;
    
    /** My Color of the shape. */
    private final Color myColor;
    
    /** My Thickness of the shape. */
    private final int myThickness;
    
    /** My Shape. */
    private final Shape myShape;
    
    /**
     * Constructor for initializing fields.
     * 
     * @param theFillColor is the color of the fill.
     * @param theIsFilled is if the shape should be filled.
     * @param theCanItFill  is if the shape can be filled.
     * @param theColor is the color of the shape.
     * @param theThickness is the thickness of the shape.
     * @param theShape of the shape drawn by the tool.
     */
    public DrawShape(final Color theFillColor, final boolean theIsFilled, 
                     final boolean theCanItFill, final Color theColor, 
                     final int theThickness, final Shape theShape) {
        myFillColor = theFillColor;
        myIsFilled = theIsFilled;
        myCanItFill = theCanItFill;
        myColor = theColor;
        myThickness = theThickness;
        myShape = theShape;
    }
    
    /**
    * Getting the fill color for the shape.
    * 
    * @return myFillColor which is the fill color of the shape.
    */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
    * Getting the if shape should be filled.
    * 
    * @return myIsFilled is if the shape should be filled.
    */
    public boolean isFilled() {
        return myIsFilled;
    }
    
    /**
    * Getting if the shape can be filled.
    * 
    * @return myFillColor which is the fill color of the shape.
    */
    public boolean isCanItFill() {
        return myCanItFill;
    }
    
    /**
     * Getting the color of the shape.
     *
     * @return myColor which is the color of the shape.
     */
    public Color getColor() {
        return myColor;
    }
      
    /**
     * Getting the thickness of the shape.
     * 
     * @return myThickness which is the thickness of the shape.
     */
    public int getThickness() {
        return myThickness;
    }
    
    /**
     * Getting the Shape of the tool that is drawing.
     * 
     * @return myShape which is the shape of the tool.
     */
    public Shape getShape() {
        return myShape;
    }
}