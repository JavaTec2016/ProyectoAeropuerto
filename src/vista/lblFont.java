package vista;

import javax.swing.*;
import java.awt.*;

class lblFont extends JLabel {
    public lblFont(String t, String font, int style, int size, int r, int g, int b) {
        super(t);
        setFont(new Font(font, style, size));
        setForeground(new Color(r, g, b));
    }
}
