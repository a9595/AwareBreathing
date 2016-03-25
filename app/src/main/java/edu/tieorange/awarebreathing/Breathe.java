package edu.tieorange.awarebreathing;

import java.io.Serializable;

/**
 * Created by tieorange on 25/03/16.
 */
public class Breathe implements Serializable {
    private int inhalesAmount;
    private int exhalesAmount;

    public Breathe(int inhalesAmount, int exhalesAmount) {
        this.inhalesAmount = inhalesAmount;
        this.exhalesAmount = exhalesAmount;
    }

    public int getInhalesAmount() {
        return inhalesAmount;
    }

    public void setInhalesAmount(int inhalesAmount) {
        this.inhalesAmount = inhalesAmount;
    }

    public int getExhalesAmount() {
        return exhalesAmount;
    }

    public void setExhalesAmount(int exhalesAmount) {
        this.exhalesAmount = exhalesAmount;
    }
}
