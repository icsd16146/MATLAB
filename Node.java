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
public class Node {
    
    private Node father;
    private int depth, cost;
    private String action, name;
    public PlexParticles a;
    
    Node(Node father, int depth, String action, int cost, PlexParticles a) {
        this.father = father;
        this.action = action;
        this.depth = depth;
        this.cost = cost;
        this.a = a;
        
        Random rand = new Random();
        int num = rand.nextInt(1000) + 1;
        this.name = String.valueOf(depth) + "-" + String.valueOf(a.getX()) + "," + String.valueOf(a.getY() + "-" + String.valueOf(num));
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public PlexParticles givePP() {
        return a;
    }
    
    public Node getFather() {
        return this.father;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    void show() {
        System.out.println("Position: " + a.getX() + "-" + a.getY());
        if (father == null) {
            System.out.println("Node's name: " + this.name + "\tNode's father: null");
        } else {
            System.out.println("Node's name: " + this.name + "\tNode's father: " + this.father.name);
        }
        System.out.println("Depth: " + this.depth + "\tCost: " + this.cost);
        System.out.println("Action performed: " + this.action);
    }
    
}
