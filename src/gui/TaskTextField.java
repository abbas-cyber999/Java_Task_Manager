package gui;

import javax.swing.JTextField;
import util.UIConstants;

/**
 * Text field for entering a task.
 *
 * @author Abbas
 */
public class TaskTextField extends JTextField {

    /**
     * Creates the task text field.
     */
    public TaskTextField() {
        setFont(UIConstants.NORMAL_FONT);
    }
}