package gui;

import javax.swing.JLabel;
import util.UIConstants;
import java.awt.Color;

/**
 * Label for section titles.
 *
 * @author Abbas
 */
public class TaskLabel extends JLabel {

    /**
     * Creates a section label.
     *
     * @param text label text
     * @param color label color
     */
    public TaskLabel(String text, Color color) {
        super(text);
        setFont(UIConstants.BOLD_FONT);
        setForeground(color);
    }
}