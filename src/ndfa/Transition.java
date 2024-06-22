package ndfa;

public class Transition {

	private char transitionValue;
	private char popValue;
	private char pushValue;
	private State initialState;
	private State finalState;

	public Transition(char transitionValue, char popValue, char pushValue, State initialState, State finalState) {
		this.transitionValue = transitionValue;
		this.popValue = popValue;
		this.pushValue = pushValue;
		this.initialState = initialState;
		this.finalState = finalState;
	}

	public char getTransitionValue() {
		return transitionValue;
	}

	public void setTransitionValue(char transitionValue) {
		this.transitionValue = transitionValue;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public State getFinalState() {
		return finalState;
	}

	public void setFinalState(State finalState) {
		this.finalState = finalState;
	}

	public char getPushValue() {
		return pushValue;
	}

	public char getPopValue() {
		return popValue;
	}

	public String getInfo(){
		return initialState.getName() + ", " + initialState.getType() + ", " +
				finalState.getName() + ", " + finalState.getType() + ", " +
				transitionValue + ", " + popValue + ", " + pushValue;
	}
}
