/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai2;

import java.util.Random;

/**
 *
 * @author konstantina
 */
public class PlexParticles {

    private String symbol;
    private int value, x, y;

    PlexParticles(String symbol, int max, int x, int y) {
        this.symbol = symbol;
        if (symbol.equals("*")) {
            Random rand = new Random();
            int val = rand.nextInt(max) + 1;

            this.value = val;
        } else {
            this.value = 0;
        }
        this.x = x;
        this.y = y;

    }

    public void setSymbol(String symb) {
        this.symbol = symb;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setValue() {
        this.value = 0;
    }

    public int giveValue() {
        return value;
    }
    
    public int getX(){ return this.x;}
    public int getY(){ return this.y; }

}
