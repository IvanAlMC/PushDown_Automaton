package ndfa;


import ndfa.stack.Stack;
import utils.StateType;

import java.util.ArrayList;

public class NDFA {
	
	private ArrayList<State> states;
	private ArrayList<Transition> transitions;
	private Stack stack;
	
	public NDFA() {
		states = new ArrayList<>();
		transitions = new ArrayList<>();
		stack = new Stack();
	}
	
	public void addState(String name, StateType type) {
		states.add(new State(name,type));
	}
	
	public void deleteState(State state) {
		states.remove(state);
	}
	
	public void addTransition(char transitionValue, char pushValue, char popValue, State initialState, State finalState) {
		transitions.add(new Transition(transitionValue, pushValue, popValue, initialState, finalState));
	}
	
	public void deleteTransition(Transition transition) {
		transitions.remove(transition);
	}

	
	//Para determinista
	public boolean validateWordDeterministic(String word) {
		boolean validation = false;
		String[] splitWord = word.split(""); 
		ArrayList<Transition> stateTransitions = searchTransitions(this.states.get(0));
		State currentState = stateTransitions.get(0).getFinalState();

		for (String s : splitWord) {
			for (Transition transition: stateTransitions) {
				if (s.equals(String.valueOf(transition.getTransitionValue()))) {
					if (processTransition(transition.getPushValue(), transition.getPopValue())) {
						System.out.println(s + ": " + transition.getInfo());
						currentState = transition.getFinalState();
						stateTransitions = searchTransitions(currentState);
						validation = true;
						break;
					}
				} else {
					validation = false;
				}
			}

			if (!validation) {
				return false;
			}
		}
		if (currentState.getType().equals(StateType.FINAL) && stack.isEmpty()){
			validation = true;
		}
		
		return validation;
	}

	public boolean processTransition(char pushValue, char popValue){
		if (pushValue != Stack.LAMBDA){
			stack.pushSymbol(pushValue);
		}
		if (popValue != Stack.LAMBDA){
			return stack.popSymbol(popValue) != Stack.NULL_CHAR;
		}
		return true;
	}

	//Para no determinista 
	public boolean validateWordNonDeterministic(String word){
		return false;
	}

	public ArrayList<Transition> searchTransitions(State state){
		ArrayList<Transition> stateTransitions = new ArrayList<Transition>();
		for (Transition transition : this.transitions) {
			if (transition.getInitialState().equals(state)) {
				stateTransitions.add(transition);
			}
		}
		return stateTransitions;
	}

	public State getState(String stateName){
		return states.stream()
				.filter(s -> s.getName().equals(stateName))
				.findAny().orElse(null);
	}
	
	public ArrayList<State> getStates(){
		return this.states;
	}

	public ArrayList<Transition> gTransitions(){
		return this.transitions;
	}
}
