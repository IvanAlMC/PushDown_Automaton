/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.elements;

/**
 *
 * @author novoa
 */
public class TransitionInfo {
 
    private String simbol;
    private String pop;
    private String push;

    public TransitionInfo(String simbol, String pop, String push) {
        this.simbol = simbol;
        this.pop = pop;
        this.push = push;
    }

    public String getSimbol() {
        return simbol;
    }

    public String getPop() {
        return pop;
    }

    public String getPush() {
        return push;
    }

    @Override
    public String toString() {
        return "Simbol: " + simbol + " Pop: " + pop + " Push: "+ push;
    }
    
    
    
}
