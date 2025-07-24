package ai;

import java.util.Arrays;

public class Node {

    int people, times[], cost = 0, depth = 0;
    Person[] s1, s2;
    Node father;
    String name, action;
    

    Node(Person[] shore1, Person[] shore2, int cost, int depth, Node father, String name, String action) {
        this.people = people;
        this.times = times;
        this.s1 = shore1;
        this.s2 = shore2;
        this.father = father;
        this.depth = depth;
        if (father != null) {
            this.depth = father.getDepth() + 1;
        }
        this.cost = cost;

        this.name = name;
        this.action=action;

    }

    private int getDepth() {
        return depth;
    }

    private int getCost() {
        return cost;
    }

    public void show() {
        System.out.print("Shore1 = " + Arrays.toString(s1) + "\nShore2 = " + Arrays.toString(s2)
                + "\ndepth = " + depth + "\tcost = " + cost
                + "\nnode's name= " + name);
        if (father != null) {
            System.out.print("\tfather's name = " + father.name+"\n");
        }
        else{
            System.out.println("\t father = null");
        }
        System.out.println("Action performed: "+ action);
        System.out.println("");
    }

}
