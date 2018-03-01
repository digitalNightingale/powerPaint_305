/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import tools.DrawShape;
import tools.Pencil;
import tools.Tool;

/**
 * Creating a Drawing Panel for PowerPaint.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class DrawingPanel extends JPanel {

    /** Serialization. */
    private static final long serialVersionUID = 4693107400480860483L;
    
    /** The color of the shape to be drawn. */
    private static Color myColor;
    
    /** The thickness from the slider. */
    private static int myThickness;
    
    /** True or false if its a new Shape. */
    private static boolean mySameShape;
    
    /** Is the color of fill for the shape. */
    private static Color myFillColor;

    /** True or false if the Shape should be filled. */
    private static boolean myIsFilled;
    
    /** True or false if the Shape can be filled. */
    private static boolean myCanItFill;
    
    /** The list of all shapes that are drawn. */
    private final List<DrawShape> myShapes;
    
    /** The current tool. */
    private Tool myTool;
    
    /** The first point. */
    private Point myFirstPoint;
    
    /** The second point. */
    private Point mySecondPoint;

    /**
     * Constructor for initializing fields. 
     */
    public DrawingPanel() {
        super();
        createDrawingPanel();
        setDefaultTool();
        final MouseAdapter mouseIA = new MyMouseAdapter();
        addMouseListener(mouseIA);
        addMouseMotionListener(mouseIA);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        myShapes = new ArrayList<DrawShape>();
    }
    
    /**
     * Method to create the default Drawing Panel.
     */
    private void createDrawingPanel() {
        final int width = 640;
        final int height = 320;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
    }
    
    /**
     * Method for creating the pencil default tool.
     */
    private void setDefaultTool() {
        myTool = new Pencil(PowerPaintGUI.INITIAL_PT, PowerPaintGUI.INITIAL_PT);
        myColor = Color.decode("#4b2e83");
        myFillColor = Color.decode("#b7a57a");
        final int thickDefault = 4;
        myThickness = thickDefault;
        mySameShape = false;
    }
    
    /**
     * Setting the current tool.
     * 
     * @param theTool current tool.
     * @return myTool is the current tool.
     */
    public Tool setTool(final Tool theTool) {
        myTool = theTool;
        return myTool;
    }
    
    /**
     * Setting the current color of the shape.
     * 
     * @param theColor current color.
     * @return myColor is the current color.
     */
    public static Color setColor(final Color theColor) {
        myColor = theColor;
        return myColor;
    }
    
    /**
     * Setting the current thickness of the shape.
     * 
     * @param theThickness current thickness setting.
     * @return theThickness is the current thickness setting.
     */
    public static int setThickness(final int theThickness) {
        myThickness = theThickness;
        return myThickness;
    }
    
    /**
     * Setting the current fill color of the shape.
     * 
     * @param theFillColor the current fill color of the shape.
     * @return myColor the fill color.
     */
    public static Color setFillColor(final Color theFillColor) {
        myFillColor = theFillColor;
        return myFillColor;
    }
    
    /**
     * True or false if need to fill the shape.
     * 
     * @param theIsFilled is if the shape should be filled.
     * @return myIsFilled if the shapes should be filled.
     */
    public static boolean setIsFilled(final boolean theIsFilled) {
        myIsFilled = theIsFilled;
        return myIsFilled;
    }
    
    /**
     * Clearing of the Drawing Panel.
     * 
     * @param theClearStatus True of False if the Panel needs to clear.
     */
    public void clearAll(final boolean theClearStatus) {
        if (theClearStatus) {
            myShapes.clear();
            repaint();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (final DrawShape s : myShapes) {
            if (s.isFilled() && s.isCanItFill()) {
                g2d.setPaint(s.getFillColor());
                g2d.fill(s.getShape());
            }
            g2d.setPaint(s.getColor());
            g2d.setStroke(new BasicStroke(s.getThickness()));
            g2d.draw(s.getShape());
        }
        if (mySameShape) {
            if (myIsFilled && myCanItFill) {
                g2d.setPaint(myFillColor);
                g2d.fill(myTool.getToolShape());   
            }
            g2d.setPaint(myColor);
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.draw(myTool.getToolShape());
        }
        
        if (myShapes.isEmpty()) {
            PowerPaintGUI.clearItem.setEnabled(false);
        } else {
            PowerPaintGUI.clearItem.setEnabled(true);
        }
        repaint();
    }

    /**
     * A MouseInputAdapter implementation.
     * 
     * @author Leah Ruisenor
     * @version November 2015 
     */
    class MyMouseAdapter extends MouseAdapter {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (myThickness != 0) {
                mySameShape = true;
                myFirstPoint = theEvent.getPoint();
                mySecondPoint = myFirstPoint;
                myTool.setFirstPoint(myFirstPoint);
                myTool.setSecondPoint(mySecondPoint);
                repaint();
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myThickness != 0) {
                mySameShape = false;
                mySecondPoint = theEvent.getPoint();
                myTool.setSecondPoint(mySecondPoint);
                myShapes.add(new DrawShape(myFillColor, myIsFilled, myTool.canItFill(),
                                           myColor, myThickness, myTool.getToolShape()));
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myThickness != 0) {
                mySecondPoint = theEvent.getPoint();
                myTool.setSecondPoint(mySecondPoint);
                repaint();
            }
        }
    }
}