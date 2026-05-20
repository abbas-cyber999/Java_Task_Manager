package gui;

import javax.swing.JButton;

import util.UIConstants;

public class DeleteButton extends JButton {

    public DeleteButton() {

        super(UIConstants.DELETE_BUTTON_TEXT);

        setBounds(260, 150, 100, 35);
    }
}