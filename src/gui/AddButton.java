package gui;

import javax.swing.JButton;

import util.UIConstants;

public class AddButton extends JButton {

    public AddButton() {

        super(UIConstants.ADD_BUTTON_TEXT);

        setBounds(110, 150, 130, 35);
    }
}