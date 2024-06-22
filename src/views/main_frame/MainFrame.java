package views.main_frame;

import controller.PushdownControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screen.height;
    int width = screen.width;
    private CanvasFA canvas;
    private WestPanel menuBar;
    private MyNorthPanel north;

    public MainFrame(ActionListener actionListener) {
        this.setTitle("PushDown Automaton");
        this.setSize(width - 500, height - 200);
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        this.canvas = new CanvasFA(100, 100, (PushdownControl) actionListener);
        this.menuBar = new WestPanel(actionListener);
        this.north = new MyNorthPanel(actionListener);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        setLayout(null);
        north.setBounds(10, 10, 100, 100);
        north.setPreferredSize(new Dimension(width - 500, 100));
        this.add(north);
        this.menuBar.setBounds(110, 130, 100, 100);
        menuBar.setPreferredSize(new Dimension(width - 1250, height - 100));
        this.add(menuBar);
        canvas.setPreferredSize(new Dimension(width, height - 100));
        this.canvas.setBounds(250, 10, 800,630);
        this.add(canvas);
    }

    public void restartAutomaton() {
        canvas.restartAutomaton();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}