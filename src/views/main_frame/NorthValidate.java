package views.main_frame;

import javax.swing.*;
import java.awt.*;


public class NorthValidate extends JPanel {

    private static final long serialVersionUID = 1L;
    JLabel text;

    public NorthValidate() {
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.BLUE);
        text = new JLabel();
        setLayout(new BorderLayout());
        add(text, BorderLayout.CENTER);
    }

    public void validateData(boolean data, String message) {
        if (data) {
            setBackground(Color.GREEN);
            text.setText(message);
            repaint();
        } else {
            setBackground(Color.RED);
            text.setText(message);
            repaint();
        }
    }

    public void drawCondition(Graphics g, String message) {
        Graphics2D gr = (Graphics2D) g;
        gr.setColor(Color.BLACK);
        gr.drawString(message, 50, 50);
    }

}
