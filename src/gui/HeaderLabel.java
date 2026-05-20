package gui;

import javax.swing.JLabel;

import util.UIConstants;

public class HeaderLabel extends JLabel {

    public HeaderLabel() {

        super(UIConstants.APP_TITLE);

        setFont(UIConstants.HEADER_FONT);

        setBounds(170, 30, 200, 30);
    }
}