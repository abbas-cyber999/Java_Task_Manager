package gui;

import javax.swing.JButton;
import java.awt.Color;
import util.UIConstants;

/**
 * Basic styled button for the application.
 *
 * @author Abbas
 */
public class AppButton extends JButton {

    private static final long serialVersionUID = 1L;

    private Color normalColor;

    /**
     * Creates a styled button.
     *
     * @param text button text
     * @param color button color
     */
    public AppButton(String text, Color color) {
        super(text);
        this.normalColor = color;
        setupButton();
        addHoverEffect();
    }

    /**
     * Sets the button style.
     */
    private void setupButton() {
        setBackground(normalColor);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(UIConstants.BOLD_FONT);
    }

    /**
     * Adds hover effect.
     */
    private void addHoverEffect() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(normalColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(normalColor);
            }
        });
    }
}