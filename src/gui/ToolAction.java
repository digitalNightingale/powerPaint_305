/*
 * TCSS 305 - Autumn 2016
 * Assignment 5b - PowerPaint
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import tools.Tool;

/**
 * ToolAction is creating the Actions of the tools
 * for the menu bar and tool bar.
 * 
 * @author Leah Ruisenor
 * @version November 2016
 */
public class ToolAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 8784271177695500761L;

    /** The component where this action is applied. */
    private final DrawingPanel myPanel;
    
    /** The tool to sync to the button. */
    private final Tool myTool;
    
    /**
     * Constructs an action with the name and the icon and tool.
     * 
     * @param theName is the name of the button.
     * @param theIcon is the name of the icon.
     * @param thePanel is the name of the drawing panel.
     * @param theTool is the tool to be used.
     * @param theEnabled if the key is set to enable.
     */
    ToolAction(final String theName, final Icon theIcon, final DrawingPanel thePanel, 
               final boolean theEnabled, final Tool theTool) {
        super(theName);
        myPanel = thePanel;
        final boolean isEnabled = theEnabled;
        myTool = theTool;
        putValue(Action.LARGE_ICON_KEY, theIcon);
        putValue(Action.MNEMONIC_KEY, 
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        putValue(Action.SELECTED_KEY, isEnabled); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setTool(myTool);
    }
}