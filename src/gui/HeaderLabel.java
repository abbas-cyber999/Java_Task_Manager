package gui;

import javax.swing.JLabel;
import util.UIConstants;
import java.awt.Color;

/**
 * Label for the application title.
 *
 * @author Abbas
 */
public class HeaderLabel extends JLabel {

    /**
     * Creates the header label.
     */
    public HeaderLabel() {
        super("ToDo Dashboard");
        setForeground(Color.WHITE);
        setFont(UIConstants.TITLE_FONT);
    }
}