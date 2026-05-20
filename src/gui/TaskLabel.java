package gui;

import javax.swing.JLabel;

import util.UIConstants;

public class TaskLabel extends JLabel {

    public TaskLabel() {

        super(UIConstants.TASK_LABEL);

        setFont(UIConstants.NORMAL_FONT);

        setBounds(80, 90, 100, 30);
    }
}