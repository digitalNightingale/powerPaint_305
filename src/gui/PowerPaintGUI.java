/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import tools.Circle;
import tools.Ellipse;
import tools.Line;
import tools.Pencil;
import tools.Rectangle;
import tools.Square;


/**
 * The GUI for PowerPaint.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class PowerPaintGUI {

    /** Initial default point. */
    public static final Point INITIAL_PT = new Point(-50, -50);
    
    /** JMenuItem for clearing the drawing panel. */
    protected static JMenuItem clearItem;

    /** The window for this GUI. */
    private final JFrame myFrame;
    
    /** The menu bar for the window. */
    private final JMenuBar myMenuBar;
    
    /** The drawing panel for the window. */    
    private final DrawingPanel myDrawingPanel;
    
    /** Image for Tool Icons. */
    private final java.awt.Image myImage;
    
    /** A list of tool actions. */
    private List<ToolAction> myToolActions;
    
    /**
     * Constructor for initializing fields.
     */
    public PowerPaintGUI() {

        myFrame = new JFrame("PowerPaint");
        myMenuBar = new JMenuBar();
        myDrawingPanel = new DrawingPanel();
        myImage = Toolkit.getDefaultToolkit().getImage("./images/lmr.gif");
    }
    
    /**
     * Starts the GUI and sets it to visible.
     */
    public final void start() {    

        myFrame.setIconImage(myImage);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setJMenuBar(buildMenuBar());
        myFrame.add(myDrawingPanel, BorderLayout.CENTER);
        myFrame.add(buildToolBar(), BorderLayout.SOUTH);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Building the Menu Bar.
     * 
     * @return menuBar for the window setup.
     */
    private JMenuBar buildMenuBar() {
        setupToolActions();
        buildFileMenu();
        buildOptionsMenu();
        buildToolMenuBar();
        buildHelpMenu();
        return myMenuBar;
    }
    
    /**
     * Sets up all the Tool Actions.
     */
    private void setupToolActions() {
        
        myToolActions = new ArrayList<ToolAction>();
        
        myToolActions.add(new ToolAction("Pencil", new ImageIcon("./images/pencil_bw.gif"),
                                         myDrawingPanel, true,
                                         new Pencil(INITIAL_PT, INITIAL_PT)));
        myToolActions.add(new ToolAction("Line", new ImageIcon("./images/line_bw.gif"),
                                         myDrawingPanel, false,
                                         new Line(INITIAL_PT, INITIAL_PT)));
        myToolActions.add(new ToolAction("Rectangle", 
                                         new ImageIcon("./images/rectangle_bw.gif"), 
                                         myDrawingPanel, false,
                                         new Rectangle(INITIAL_PT, INITIAL_PT)));
        myToolActions.add(new ToolAction("Ellipse", new ImageIcon("./images/ellipse_bw.gif"),
                                         myDrawingPanel, false,
                                         new Ellipse(INITIAL_PT, INITIAL_PT)));
        myToolActions.add(new ToolAction("Square", new ImageIcon("./images/square_bw.gif"),
                                         myDrawingPanel, false,
                                         new Square(INITIAL_PT, INITIAL_PT)));
        myToolActions.add(new ToolAction("Circle", new ImageIcon("./images/circle_bw.gif"),
                                         myDrawingPanel, false,
                                         new Circle(INITIAL_PT, INITIAL_PT)));
    }

    /**
     * Building the Tool Bar for the window.
     * 
     * @return the full tool bar.
     */
    private JToolBar buildToolBar() {
        
        final JToolBar toolBar = new JToolBar();
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        for (final ToolAction t : myToolActions) {
            final JToggleButton toggleButton = new JToggleButton(t);
            buttonGroup.add(toggleButton);
            toolBar.add(toggleButton);
        }
        return toolBar;
    }
    
    /**
     * Building the Tool Menu Bar for the window.
     * 
     * @return a full menu bar.
     */
    private JMenuBar buildToolMenuBar() {
        
        final JMenu toolMenu = new JMenu("Tools");
        toolMenu.setMnemonic(KeyEvent.VK_T);

        final ButtonGroup buttonGroup = new ButtonGroup();

        for (final ToolAction t : myToolActions) {
            final JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(t);
            buttonGroup.add(radioButton);
            toolMenu.add(radioButton);
        }
        myMenuBar.add(toolMenu);
        return myMenuBar;
    }

    /**
     * Building the File menu for the Menu Bar.
     * 
     * @return fileMenu to the menu Bar.
     */
    private JMenu buildFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        //final JMenuItem clearItem = new JMenuItem("Clear");
        clearItem = new JMenuItem("Clear");
        clearItem.setMnemonic(KeyEvent.VK_C);
        clearItem.setEnabled(false);
        
        clearItem.addActionListener((theEvent) -> { 
            myDrawingPanel.clearAll(true);
        });
        
        final JMenuItem exitItem = new JMenuItem("Quit");
        exitItem.setMnemonic(KeyEvent.VK_Q);

        exitItem.addActionListener((theEvent) -> { 
            myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
        });

        fileMenu.add(clearItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        myMenuBar.add(fileMenu);
        return fileMenu;
    }
    
    /**
     * Building the Option Menu.
     * 
     * @return optionMenu to the Menu Bar.
     */
    private JMenu buildOptionsMenu() {    
        final JMenu optionMenu = new JMenu("Options");
        optionMenu.setMnemonic(KeyEvent.VK_O);
        optionMenu.add(buildThickSubMenu());
        
        final Color defaultColor = Color.decode("#4b2e83");
        final ColorSwatchIcon colorIcon = new ColorSwatchIcon(defaultColor);
        final JMenuItem drawCItem = new JMenuItem("Draw Color...", colorIcon);
        drawCItem.setMnemonic(KeyEvent.VK_D);
        
        drawCItem.addActionListener((theEvent) -> {
            final Color color = JColorChooser.showDialog(myDrawingPanel,
                                                         "Choose Drawing Color", defaultColor);
            colorIcon.setColor(color);    
            DrawingPanel.setColor(color);
        });

        final Color defaultFillColor = Color.decode("#b7a57a");
        final ColorSwatchIcon fillColorIcon = new ColorSwatchIcon(defaultFillColor);
        final JMenuItem fillCItem = new JMenuItem("Fill Color...", fillColorIcon);
        fillCItem.setMnemonic(KeyEvent.VK_F);
        
        fillCItem.addActionListener((theEvent) -> {
            final Color colorF = JColorChooser.showDialog(myDrawingPanel, "Choose Fill Color",
                                                          defaultFillColor);
            fillColorIcon.setColor(colorF);
            DrawingPanel.setFillColor(colorF);
        });

        optionMenu.addSeparator();
        optionMenu.add(drawCItem);
        optionMenu.add(fillCItem);
        optionMenu.addSeparator();
        optionMenu.add(buildFillCheckBox());
        myMenuBar.add(optionMenu);
        return optionMenu;
    }
    
    /**
     * Building the thickness slider subMenu.
     * 
     * @return thicknessMenu added slider to the Menu Bar.
     */
    private JMenu buildThickSubMenu() {
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        
        final JSlider thicknessSlider = new JSlider(0, 20, 1);
        final int majrTick = 5;
        final int defaultTick = 4;
        
        thicknessSlider.addChangeListener((theEvent) -> {
            final int thickness = thicknessSlider.getValue();
            DrawingPanel.setThickness(thickness);
        });
        
        thicknessSlider.setMajorTickSpacing(majrTick);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setValue(defaultTick);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);
        thicknessMenu.add(thicknessSlider);
        
        return thicknessMenu;
    }
    
    /** 
     * Building the fill check box to the MenuBar.
     * 
     * @return fillCheck check box added to the Menu Bar.
     */
    private JMenuItem buildFillCheckBox() {
        final JCheckBoxMenuItem fillCheck = new JCheckBoxMenuItem("Fill");
        fillCheck.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        
        fillCheck.addActionListener((theEvent) -> {
            if (fillCheck.isSelected()) {
                DrawingPanel.setIsFilled(true);
            } else {
                DrawingPanel.setIsFilled(false);
            }
        });

        return fillCheck;
    }
    
    /**
     * Building the help subMenu.
     * 
     * @return subMenu with the Help and About menus.
     */
    private JMenu buildHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(buildDialogMenu("About..."));
        myMenuBar.add(helpMenu);
        return helpMenu;
    }
    
    /**
     * Builds the JOptionPane for the About menu.
     * 
     * @param theText accepts the text to be displayed on the pane.
     * @return dialogItem which is the Message to be place on the pane.
     */
    private JMenuItem buildDialogMenu(final String theText) {
        final String newLine = System.getProperty("line.separator");
        final JMenuItem dialogItem = new JMenuItem(theText);
        
        dialogItem.addActionListener((theEvent) -> { 
            JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint" + newLine
                            + "Autumn 2016" + newLine + "Leah Ruisenor", 
                            "About", 0, new ImageIcon(myImage)); 
        });
        return dialogItem;
    }
}