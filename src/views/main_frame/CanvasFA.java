package views.main_frame;

import controller.PushdownControl;
import utils.StateType;
import views.elements.StateElement;
import views.elements.Transition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
 * Clase CanvasFA: Es el canvas del Finite Automata, donde se van a dibujar los distintos elementos graficos
 * */
public class CanvasFA extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static int CIRCLE_RADIUS = 50;
    public int width, height;
    private StateElement se = new StateElement();
    private ArrayList<StateElement> states = new ArrayList<StateElement>();
    private ArrayList<Transition> transitions = new ArrayList<Transition>();
    private boolean creatingTransition = false;

    public CanvasFA(int width, int height, PushdownControl control) {
        this.setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //Checks if there's no State in the clicked position if there's one it takes it as first State for a new Transition and the next one clicked is the final State
                boolean isOverlapping = false;
                StateElement selected = null;

                int counter = 0;

                while (!isOverlapping && counter < states.size()) {
                    if (dist(states.get(counter).x + CIRCLE_RADIUS / 2, states.get(counter).y + CIRCLE_RADIUS / 2, e.getX(), e.getY()) < CIRCLE_RADIUS) {
                        selected = states.get(counter);
                        isOverlapping = true;
                    }
                    counter++;
                }
                if (!isOverlapping) {

                    control.addState("q" + states.size());
                    states.add(new StateElement(e.getX() - CIRCLE_RADIUS / 2, e.getY() - CIRCLE_RADIUS / 2, "q" + states.size()));
                    for (StateElement state : states) {
                        drawState(state.x, state.y);
                    }

                } else if (!creatingTransition) {
                    if (e.getButton() == 1) {
                        Transition t = new Transition();
                        t.start = selected;
                        creatingTransition = true;
                        transitions.add(t);
                    }
                    if (e.getButton() == 3) {
                        MyJOption o = new MyJOption();
                        int option = o.myMenu();
                        if (option == 0) {
                            boolean initialSetted = false;
                            for (StateElement s : states) {
                                if (s.getType() == StateType.INITIAL) {
                                    initialSetted = true;
                                }
                            }
                            if (!initialSetted)
                                selected.setType(StateType.INITIAL);
                            control.updateState(selected.tag, StateType.INITIAL);
                        } else if (option == 1) {
                            selected.setType(StateType.FINAL);
                            control.updateState(selected.tag, StateType.FINAL);
                        }
                    }

                } else if (creatingTransition) {
                    Transition aux = transitions.get(transitions.size() - 1);
                    transitions.get(transitions.size() - 1).end = selected;
                    JDialogTransition o = new JDialogTransition(null,true);
                    o.setVisible(true);
                    Transition tElement = transitions.get(transitions.size() - 1);
                    tElement.condicion = (o.getTransitionInfo().getSimbol()).charAt(0);
                    tElement.pop = (o.getTransitionInfo().getPop()).charAt(0);
                    tElement.push = (o.getTransitionInfo().getPush()).charAt(0);
                    char condition = (o.getTransitionInfo().getSimbol()).charAt(0);
                    char pop = (o.getTransitionInfo().getPop()).charAt(0);
                    char push = (o.getTransitionInfo().getPush()).charAt(0);
                    System.out.println(tElement.condicion + "; " + pop + " ; " + push);
                    creatingTransition = false;
                    int index = 0;
                    for (Transition t : transitions) {
                        if (aux.start == t.start && aux.end == t.end && aux.condicion != t.condicion) {
                            index++;
                        }
                    }
                    transitions.get(transitions.size() - 1).setIndex(index);
                    control.addTransition(aux.start.tag, aux.end.tag, aux.condicion, aux.pop, aux.push);
                }
                drawState();
            }
        });

    }

    private void drawState(int x, int y) {
        final int CURR_X = se.getX();
        final int CURR_Y = se.getY();
        final int OFFSET = 1;

        if ((CURR_X != x) || (CURR_Y != y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X, CURR_Y, CIRCLE_RADIUS, CIRCLE_RADIUS);

            // Update coordinates.
            se.setX(x);
            se.setY(y);

            // Repaint the square at the new location.
            repaint(se.getX(), se.getY(), CIRCLE_RADIUS + OFFSET, CIRCLE_RADIUS + OFFSET);
        }
    }


    private void drawState() {
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Draw Text
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
        for (Transition t : transitions) {
            t.drawTransition(g2);
            if (t.end != null) {
                t.drawCondition(g2);
            }
        }
        for (StateElement state : states) {
            state.paintState(g2);
        }
    }

    public void restartAutomaton() {
        states.clear();
        transitions.clear();
        this.drawState();
    }

    private double dist(float x1, float y1, float x2, float y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
