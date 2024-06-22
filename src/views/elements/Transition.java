package views.elements;

import views.main_frame.CanvasFA;

import java.awt.*;

public class Transition {
	public char condicion;
	public char pop;
	public char push;

	public StateElement start;
	public StateElement end;
	private int index = 0;

	public void drawTransition(Graphics g) {
		g.setColor(Color.BLACK);
		if(end != null && start != null)
			g.drawLine(start.x + CanvasFA.CIRCLE_RADIUS/2, start.y + CanvasFA.CIRCLE_RADIUS/2, end.x + CanvasFA.CIRCLE_RADIUS/2, end.y + CanvasFA.CIRCLE_RADIUS/2);
	}
	public void drawCondition(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		gr.drawString(condicion + ","+ pop + ";" + push, ( start.x + end.x ) / 2 + CanvasFA.CIRCLE_RADIUS/2,  ((start.y + end.y) / 2 + CanvasFA.CIRCLE_RADIUS/2) - (index * 15));
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
