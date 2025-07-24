/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai2;

import java.util.ArrayList;
import java.util.Scanner;

public class AI2 {

    public AI2(ArrayList<Node> nodeList, Plexus plexus) {
        Scanner in = new Scanner(System.in);
        int choice, numOfNodes;
        do {
            System.out.println("press:\n\t1 for UCS\n\t2 for A*\n\t3 for BFS\n\t4 to exit");
            choice = in.nextInt();

            System.out.print("");

            if (choice == 1) {
                System.out.println("type the number of nodes to extend: ");
                numOfNodes = in.nextInt();
                ArrayList<Node> extended = new ArrayList();
                System.out.println(UCS(1, nodeList, plexus, numOfNodes, extended));
            }
            if (choice == 2) {
                System.out.println("type the number of nodes to extend: ");
                numOfNodes = in.nextInt();
                ArrayList<Node> extended = new ArrayList();
                System.out.println(Astar(0, nodeList, plexus, numOfNodes, extended));
            }
            if (choice == 3) {
                System.out.println("type the number of nodes to extend: ");
                numOfNodes = in.nextInt();
                ArrayList<Node> extended = new ArrayList();

                System.out.println(BFS(1, nodeList, plexus, numOfNodes, extended));
            }
        } while (choice != 4);

    }

    public String UCS(int depth, ArrayList<Node> nodeList, Plexus plexus, int numOfNodes, ArrayList<Node> extended) {

        if (extended.size() >= numOfNodes) {
            return "Reached number of nodes which can be extended\nSolution NOT found!!";
        }

        Node node = nodeList.get(0);//ευρεση κομβου με το μικροτερο κοστος
        int min = node.getCost();

        for (int n = 1; n < nodeList.size(); n++) {
            if (nodeList.get(n).getCost() < min) {

                node = nodeList.get(n);
                min = nodeList.get(n).getCost();
            }
        }

        if (plexus.getFinish().getX() == node.a.getX() && plexus.getFinish().getY() == node.a.getY()) {//αν ειναι ο στοχος βρικαμε λυση
            System.out.print("\n\nSolution on node: ");
            node.show();
            System.out.println();

            ArrayList<Node> path = new ArrayList();
            path.add(node);

            try {
                for (int i = 0; i < path.size(); i++) {
                    Node q = path.get(i).getFather();
                    path.add(q);
                    q.show();
                    System.out.println();

                }
            } catch (NullPointerException e) {
            };
            System.out.println("\n");
            System.out.println("Nodes created: " + nodeList.size() + extended.size());
            System.out.println("Nodes to be etended: " + nodeList.size());

            return "Solution found !!!";
        }

        //διαφορετικα επεκτινω τον κομβο
        //ο καθε ο κομβος εχει 4 πιθανα παιδια, πανω, κατω, δεξια, αριστερα, καθως αυτες ειναι οι ενεργειες που μπορει να κανει ο κομβος στο πλεγμα
        //χρησιμοποιω try catch διοτι μπορει να βρισκομαστε σε κομβο που βρισκεται στην ακρη του πλεγματος
        try {
            if (plexus.pp[node.a.getX() - 2][node.a.getY()] != null) {
                if (plexus.pp[node.a.getX() - 1][node.a.getY()].getSymbol().equals("+")) {

                    Node up = new Node(node, node.getDepth() + 1, "up", plexus.pp[node.a.getX() - 2][node.a.getY()].giveValue() + node.getCost(), plexus.pp[node.a.getX() - 2][node.a.getY()]);
                    up.show();
                    nodeList.add(up);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX() + 2][node.a.getY()] != null) {
                if (plexus.pp[node.a.getX() + 1][node.a.getY()].getSymbol().equals("+")) {

                    Node down = new Node(node, node.getDepth() + 1, "down", plexus.pp[node.a.getX() + 2][node.a.getY()].giveValue() + node.getCost(), plexus.pp[node.a.getX() + 2][node.a.getY()]);
                    down.show();
                    nodeList.add(down);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX()][node.a.getY() + 2] != null) {
                if (plexus.pp[node.a.getX()][node.a.getY() + 1].getSymbol().equals("+")) {

                    Node right = new Node(node, node.getDepth() + 1, "right", plexus.pp[node.a.getX()][node.a.getY() + 2].giveValue() + node.getCost(), plexus.pp[node.a.getX()][node.a.getY() + 2]);
                    right.show();
                    nodeList.add(right);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX()][node.a.getY() - 2] != null) {
                if (plexus.pp[node.a.getX()][node.a.getY() - 1].getSymbol().equals("+")) {

                    Node left = new Node(node, node.getDepth() + 1, "left", plexus.pp[node.a.getX()][node.a.getY() - 2].giveValue() + node.getCost(), plexus.pp[node.a.getX()][node.a.getY() - 2]);
                    left.show();
                    nodeList.add(left);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        extended.add(node);
        nodeList.remove(node);
        return UCS(0, nodeList, plexus, numOfNodes, extended);
    }

    public String Astar(int depth, ArrayList<Node> nodeList, Plexus plexus, int numOfNodes, ArrayList<Node> extended) {
        if (extended.size() >= numOfNodes) {
            return "Reached number of nodes which can be extended\nSolution NOT found!!";
        }

        Node node = nodeList.get(0);//ευρεση κομβου με το μικροτερο κοστος
        int min = node.getCost();

        for (int n = 1; n < nodeList.size(); n++) {
            if (nodeList.get(n).getCost() < min) {

                node = nodeList.get(n);
                min = nodeList.get(n).getCost();
            }
        }

        if (plexus.getFinish().getX() == node.a.getX() && plexus.getFinish().getY() == node.a.getY()) {//αν ειναι ο στοχος βρικαμε λυση
            System.out.print("\n\nSolution on node:\n ");
            node.show();
            System.out.println("\n");

            ArrayList<Node> path = new ArrayList();
            path.add(node);

            try {
                for (int i = 0; i < path.size(); i++) {
                    Node q = path.get(i).getFather();
                    path.add(q);
                    q.show();
                    System.out.println();

                }
            } catch (NullPointerException e) {
            };
            System.out.println("\n");
            System.out.println("Nodes created: " + nodeList.size() + extended.size());
            System.out.println("Nodes to be etended: " + nodeList.size());

            return "Solution found !!!";
        }

        try {
            if (plexus.pp[node.a.getX() - 2][node.a.getY()] != null) {
                if (plexus.pp[node.a.getX() - 1][node.a.getY()].getSymbol().equals("+")) {
                    int x = plexus.pp[node.a.getX() - 2][node.a.getY()].getX() - plexus.getFinish().getX();

                    int y = plexus.pp[node.a.getX() - 2][node.a.getY()].getY() - plexus.getFinish().getY();

                    Node up = new Node(node, node.getDepth() + 1, "up", Math.abs(x) + Math.abs(y) + plexus.pp[node.a.getX() - 2][node.a.getY()].giveValue(), plexus.pp[node.a.getX() - 2][node.a.getY()]);
                    up.show();
                    nodeList.add(up);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX() + 2][node.a.getY()] != null) {
                if (plexus.pp[node.a.getX() + 1][node.a.getY()].getSymbol().equals("+")) {
                    int x = plexus.pp[node.a.getX() + 2][node.a.getY()].getX() - plexus.getFinish().getX();

                    int y = plexus.pp[node.a.getX() + 2][node.a.getY()].getY() - plexus.getFinish().getY();

                    Node down = new Node(node, node.getDepth() + 1, "down", Math.abs(x) + Math.abs(y) + plexus.pp[node.a.getX() + 2][node.a.getY()].giveValue(), plexus.pp[node.a.getX() + 2][node.a.getY()]);
                    down.show();
                    nodeList.add(down);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX()][node.a.getY() + 2] != null) {
                if (plexus.pp[node.a.getX()][node.a.getY() + 1].getSymbol().equals("+")) {

                    int x = plexus.pp[node.a.getX()][node.a.getY() + 2].getX() - plexus.getFinish().getX();

                    int y = plexus.pp[node.a.getX()][node.a.getY() + 2].getY() - plexus.getFinish().getY();

                    Node right = new Node(node, node.getDepth() + 1, "right", Math.abs(x) + Math.abs(y) + plexus.pp[node.a.getX()][node.a.getY() + 2].giveValue(), plexus.pp[node.a.getX()][node.a.getY() + 2]);
                    right.show();
                    nodeList.add(right);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            if (plexus.pp[node.a.getX()][node.a.getY() - 2] != null) {
                if (plexus.pp[node.a.getX()][node.a.getY() - 1].getSymbol().equals("+")) {
                    int x = plexus.pp[node.a.getX()][node.a.getY() - 2].getX() - plexus.getFinish().getX();

                    int y = plexus.pp[node.a.getX()][node.a.getY() - 2].getY() - plexus.getFinish().getY();

                    Node left = new Node(node, node.getDepth() + 1, "left", Math.abs(x) + Math.abs(y) + plexus.pp[node.a.getX()][node.a.getY() - 2].giveValue(), plexus.pp[node.a.getX()][node.a.getY() - 2]);
                    left.show();
                    nodeList.add(left);
                    System.out.println();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        extended.add(node);
        nodeList.remove(node);

        return Astar(0, nodeList, plexus, numOfNodes, extended);
    }

    public String BFS(int depth, ArrayList<Node> nodeList, Plexus plexus, int numOfNodes, ArrayList<Node> extended) {
        if (extended.size() == numOfNodes) {
            return "Reached number of nodes which can be extended\nSolution NOT found!!";
        }

        ArrayList<Node> nextDepth = new ArrayList();

        for (int n = 0; n < nodeList.size(); n++) {

            Node node = nodeList.get(n);

            if (plexus.getFinish().getX() == node.a.getX() && plexus.getFinish().getY() == node.a.getY()) {//αν ειναι ο στοχος βρικαμε λυση
                System.out.print("\n\nSolution on node: \n");
                node.show();
                System.out.println();

                ArrayList<Node> path = new ArrayList();
                path.add(node);

                try {
                    for (int i = 0; i < path.size(); i++) {
                        Node q = path.get(i).getFather();
                        path.add(q);
                        q.show();
                        System.out.println();

                    }
                } catch (NullPointerException e) {
                };
                System.out.println("\n");
                System.out.println("Nodes created: " + nodeList.size() + extended.size());
                System.out.println("Nodes to be etended: " + nodeList.size());

                return "Solution found !!!";
            }

            try {
                if (plexus.pp[node.a.getX() - 2][node.a.getY()] != null) {
                    if (plexus.pp[node.a.getX() - 1][node.a.getY()].getSymbol().equals("+")) {
                        int x = plexus.pp[node.a.getX() - 2][node.a.getY()].getX() - plexus.getFinish().getX();

                        int y = plexus.pp[node.a.getX() - 2][node.a.getY()].getY() - plexus.getFinish().getY();

                        Node up = new Node(node, node.getDepth() + 1, "up", Math.abs(x) + Math.abs(y), plexus.pp[node.a.getX() - 2][node.a.getY()]);
                        up.show();
                        nextDepth.add(up);
                        extended.add(up);
                        System.out.println();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }

            try {
                if (plexus.pp[node.a.getX() + 2][node.a.getY()] != null) {
                    if (plexus.pp[node.a.getX() + 1][node.a.getY()].getSymbol().equals("+")) {
                        int x = plexus.pp[node.a.getX() + 2][node.a.getY()].getX() - plexus.getFinish().getX();

                        int y = plexus.pp[node.a.getX() + 2][node.a.getY()].getY() - plexus.getFinish().getY();

                        Node down = new Node(node, node.getDepth() + 1, "down", Math.abs(x) + Math.abs(y), plexus.pp[node.a.getX() + 2][node.a.getY()]);
                        down.show();
                        nextDepth.add(down);
                        extended.add(down);
                        System.out.println();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }

            try {
                if (plexus.pp[node.a.getX()][node.a.getY() + 2] != null) {
                    if (plexus.pp[node.a.getX()][node.a.getY() + 1].getSymbol().equals("+")) {
                        int x = plexus.pp[node.a.getX()][node.a.getY() + 2].getX() - plexus.getFinish().getX();

                        int y = plexus.pp[node.a.getX()][node.a.getY() + 2].getY() - plexus.getFinish().getY();

                        Node right = new Node(node, node.getDepth() + 1, "right", Math.abs(x) + Math.abs(y), plexus.pp[node.a.getX()][node.a.getY() + 2]);
                        right.show();
                        nextDepth.add(right);
                        extended.add(right);
                        System.out.println();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }

            try {
                if (plexus.pp[node.a.getX()][node.a.getY() - 2] != null) {
                    if (plexus.pp[node.a.getX()][node.a.getY() - 1].getSymbol().equals("+")) {
                        int x = plexus.pp[node.a.getX()][node.a.getY() - 2].getX() - plexus.getFinish().getX();

                        int y = plexus.pp[node.a.getX()][node.a.getY() - 2].getY() - plexus.getFinish().getY();

                        Node left = new Node(node, node.getDepth() + 1, "left", Math.abs(x) + Math.abs(y), plexus.pp[node.a.getX()][node.a.getY() - 2]);
                        left.show();
                        nextDepth.add(left);
                        extended.add(left);
                        System.out.println();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }

        return BFS(depth + 1, nextDepth, plexus, numOfNodes, extended);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Give the number of lines: ");
        int lines = in.nextInt();//γραμμες πλεγματος

        System.out.println("Give the percent(%) of nodes to disappear: ");
        int percent = in.nextInt();

        float p = (float) percent / 100;//ποσοστο διαγραφης
        System.out.println("percent=" + p);

        System.out.println("The max value of a node: ");
        int m = in.nextInt();//μαξ τιμη κομβου

        Plexus ple = new Plexus(lines, m, p);
        ple.show();
        System.out.println("\n");
        ple.showValues();

        System.out.println("This is the starting Node:\n");
        Node start = new Node(null, 0, " ", 0, ple.getStart());
        start.show();

        System.out.println("This is the finishing Node:\n");
        Node finish = new Node(null, 0, " ", 0, ple.getFinish());
        finish.show();

        System.out.println();

        ArrayList<Node> nodeList = new ArrayList();
        nodeList.add(start);

        AI2 ai2 = new AI2(nodeList, ple);

        //System.out.println(acmes);
    }
}
