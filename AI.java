package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AI {

    public AI(ArrayList<Node> nodeList, ArrayList<Node> nextDepth) {
        Scanner in = new Scanner(System.in);
        System.out.println("press:\n\t1 for BFS\n\t2 for DFS\n\t3 for IDS\n\t4 for UCS");
        int choice = in.nextInt();
        if (choice == 1) {
            System.out.println(BFS(1, nodeList, 1));
        }
        if (choice == 2) {
            System.out.println(DFS(1, nodeList.get(0), nodeList, nextDepth, 0));
        }
        if (choice == 3) {
            System.out.print("Give a depth limit for IDS: ");

            int limit = in.nextInt();

            System.out.println(IDS(1, nodeList.get(0), limit));
        }
        if (choice == 4) {
            ArrayList<Node> extended = new ArrayList();
            System.out.println(UCS(1, nodeList, extended));
        }

    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public String BFS(int depth, ArrayList<Node> nodeList, int created) {
        created += nodeList.size();

        ArrayList<Node> nextDepth = null;
        int cost = 0, cost1 = 0;
        String str = null, str1 = null;

        for (int node = 0; node < nodeList.size(); node++) {//για καθε κομβο που εχει δημιουργηθει για αυτο το βαθος

            Person[] sh1, sh2;//δημιουργω καινουριες ακτες
            nextDepth = new ArrayList();//δημιουργω λιστα αποθηκευσης απογονων κομβων για τον συγκεκριμενο κομβο

            int count = 0;//και μετραω τις αδειες θεσεις της ακτης1 για τον συγκεκριμενο κομβο
            Node n = nodeList.get(node);
            for (int s = 0; s < n.s1.length; s++) {

                if (n.s1[s] == null) {
                    count++;
                }
            }
            if (count == nodeList.get(node).s1.length) {//αν η ακτη1 ειναι αδεια τοτε βρηκα τη λυση μου
                ArrayList<Node> path = new ArrayList();

                path.add(n);

                System.out.println("Solution is: ");
                n.show();
                for (int i = 0; i < depth - 1; i++) {
                    Node q = path.get(i).father;
                    path.add(q);
                    q.show();

                }
                System.out.println("\n");
                System.out.println("Nodes created: " + nodeList.size() + created);
                System.out.println("Nodes to be etended: " + nodeList.size());

                return "Cost = " + n.cost
                        + "\n and the path is " + path.toString();
            }

            int c = 0;//βρισκω κενες θεσεις τον προορισμο
            for (int i = 0; i < n.s2.length; i++) {
                if (n.s2[i] == null) {
                    c++;
                }
            }

            if (c == n.s2.length) {//αν ειναι μονο κενες θεσεις, αρα ειναι ο αρχικος κομβος τοτε:

                for (int i = 0; i < n.s1.length; i++) {//παιρνω καθε πιθανο ζευγαρι
                    for (int j = i + 1; j < n.s1.length; j++) {//ι+1 για να αποφυγω διπλοτυπα
                        if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                            if (n.s1[i] != null && n.s1[j] != null) {

                                sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                                sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                                for (int q = 0; q < n.s1.length; q++) {
                                    sh1[q] = n.s1[q];
                                    sh2[q] = n.s2[q];
                                }

                                sh2[i] = n.s1[i];
                                sh2[j] = n.s1[j];

                                //System.out.println("person: " + n.s1[i].toString() + " goes to shore 2");
                                //System.out.println("person: " + n.s1[j].toString() + " goes to shore 2\n");
                                cost = max(n.s1[i].time, n.s1[j].time);

                                str = "person: " + n.s1[i].toString() + " goes to shore 2\n"
                                        + "\tperson: " + n.s1[j].toString() + " goes to shore 2\n"
                                        + "with cost " + cost + "\n";

                                sh1[i] = null;
                                sh1[j] = null;

                                Random rand = new Random();
                                int num = rand.nextInt(1000) + 1;
                                String name = String.valueOf(depth) + "." + String.valueOf(node + 1) + "-" + String.valueOf(num);
                                System.out.println(name);

                                Node childNode = new Node(sh1, sh2, cost, depth, n, name, str);

                                childNode.show();
                                nextDepth.add(childNode);
                                System.out.println();

                            }
                        }
                    }
                }
            } else {
                for (int lamp = 0; lamp < n.s2.length; lamp++) {//για καθε πιαθνο ατομο που μπορει να φερει τον φακο

                    sh1 = new Person[0];//αντιγραφη 
                    sh2 = new Person[0];//βαζω 0 αρχικα για ναμην τρεξει παρακατω και εχω διπλοτυπα

                    if (n.s2[lamp] != null) {//αν εχω ναθρωπο με φακο

                        sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                        sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                        for (int q = 0; q < n.s1.length; q++) {
                            sh1[q] = n.s1[q];
                            sh2[q] = n.s2[q];

                        }

                        sh1[lamp] = n.s2[lamp];
                        sh2[lamp] = null;

                        //System.out.println("person: " + n.s2[lamp].toString() + " goes back to shore 1 ==========");
                        cost1 = n.s2[lamp].time;

                        str1 = "person: " + n.s2[lamp].toString() + " goes back to shore 1\n"
                                + "with cost " + cost1 + "\n";

                    }

                    Person[] sh1a, sh2a;

                    for (int i = 0; i < sh1.length; i++) {//παιρνω καθε ζευγαρι
                        for (int j = i + 1; j < sh1.length; j++) {
                            if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                                if (sh1[i] != null && sh1[j] != null) {
                                    sh1a = new Person[sh1.length];
                                    sh2a = new Person[sh2.length];

                                    for (int q = 0; q < sh1.length; q++) {
                                        sh1a[q] = sh1[q];
                                        sh2a[q] = sh2[q];
                                    }

                                    sh2a[i] = sh1[i];
                                    sh2a[j] = sh1[j];

                                    //System.out.println("person: " + sh1[i].toString() + " goes to shore 2");
                                    //System.out.println("person: " + sh1[j].toString() + " goes to shore 2\n");
                                    cost = max(sh1[i].time, sh1[j].time);

                                    str = str1 + "person: " + sh1[i].toString() + " goes to shore 2\n"
                                            + "person: " + sh1[j].toString() + " goes to shore 2\n"
                                            + "with cost " + cost + "\n";

                                    cost = cost1 + max(sh1[i].time, sh1[j].time);

                                    sh1a[i] = null;
                                    sh1a[j] = null;

                                    Random rand = new Random();
                                    int num = rand.nextInt(1000) + 1;
                                    String name = String.valueOf(depth) + "." + String.valueOf(node + 1) + "-" + String.valueOf(num);
                                    System.out.println(name);

                                    Node childNode = new Node(sh1a, sh2a, cost + n.cost, depth, n, name, str);

                                    childNode.show();
                                    nextDepth.add(childNode);
                                    System.out.println();

                                }
                            }
                        }
                    }

                }
            }
        }

        return BFS(depth + 1, nextDepth, created);
    }

    public String DFS(int depth, Node expand, ArrayList<Node> nodeList, ArrayList<Node> nextDepth, int l) {//αλγοριθμος DFS

        if (!nextDepth.isEmpty()) {

            for (int i = 0; i < nodeList.size(); i++) {
                if (Arrays.equals(nodeList.get(i).s1, expand.s1) && Arrays.equals(nodeList.get(i).s2, expand.s2)) {
                    if (l < nextDepth.size()) {
                        return DFS(depth, nextDepth.get(l), nodeList, nextDepth, l + 1);
                    }
                }
            }
        }

        int cost = 0, cost1 = 0;
        String str = null, str1 = null;

        Person[] sh1, sh2;//δημιουργω καινουριες ακτες
        nextDepth = new ArrayList();//δημιουργω λιστα αποθηκευσης απογονων κομβων για τον συγκεκριμενο κομβο

        int count = 0;//και μετραω τις αδειες θεσεις της ακτης1 για τον συγκεκριμενο κομβο

        for (int s = 0; s < expand.s1.length; s++) {

            if (expand.s1[s] == null) {
                count++;
            }
        }
        if (count == expand.s1.length) {//αν η ακτη1 ειναι αδεια τοτε βρηκα τη λυση μου
            ArrayList<Node> path = new ArrayList();

            path.add(expand);

            System.out.println("Solution is: ");
            expand.show();
            for (int i = 0; i < depth - 1; i++) {
                Node q = path.get(i).father;
                path.add(q);
                q.show();

            }
            System.out.println("\n");
            System.out.println("Nodes created: " + nodeList.size());
            System.out.println("Nodes to be etended: " + nextDepth.size());

            return "Cost = " + expand.cost
                    + "\n and the path is " + path.toString();
        }

        int c = 0;//βρισκω κενες θεσεις τον προορισμο
        for (int i = 0; i < expand.s2.length; i++) {
            if (expand.s2[i] == null) {
                c++;
            }
        }

        if (c == expand.s2.length) {//αν ειναι μονο κενες θεσεις, αρα ειναι ο αρχικος κομβος τοτε:

            for (int i = 0; i < expand.s1.length; i++) {//παιρνω καθε πιθανο ζευγαρι
                for (int j = i + 1; j < expand.s1.length; j++) {//ι+1 για να αποφυγω διπλοτυπα
                    if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                        if (expand.s1[i] != null && expand.s1[j] != null) {

                            sh1 = new Person[expand.s1.length];//αντιγραφη ακτης1
                            sh2 = new Person[expand.s1.length];//αντιγραφη ακτης2

                            for (int q = 0; q < expand.s1.length; q++) {
                                sh1[q] = expand.s1[q];
                                sh2[q] = expand.s2[q];
                            }

                            sh2[i] = expand.s1[i];//μετακινηση ατομων
                            sh2[j] = expand.s1[j];

                            System.out.println("person: " + expand.s1[i].toString() + " goes to shore 2");
                            System.out.println("person: " + expand.s1[j].toString() + " goes to shore 2\n");

                            cost = max(expand.s1[i].time, expand.s1[j].time);//ευρεση κοστους

                            str = "\nperson: " + expand.s1[i].toString() + " goes to shore 2\n"
                                    + "person: " + expand.s1[j].toString() + " goes to shore 2\n"
                                    + "with cost " + cost + "\n";

                            sh1[i] = null;
                            sh1[j] = null;//αδειασμα θεσεων

                            Random rand = new Random();
                            int num = rand.nextInt(1000) + 1;
                            String name = String.valueOf(depth) + "." + String.valueOf(1) + "-" + String.valueOf(num);
                            System.out.println(name);//ονομα κομβου

                            Node childNode = new Node(sh1, sh2, cost, depth, expand, name, str);//κομβος

                            childNode.show();
                            nextDepth.add(childNode);
                            System.out.println();

                        }
                    }
                }
            }
        } else {
            for (int lamp = 0; lamp < expand.s2.length; lamp++) {//για καθε πιθανο ατομο που μπορει να φερει τον φακο

                sh1 = new Person[0];//αντιγραφη 
                sh2 = new Person[0];//βαζω 0 αρχικα για να μην τρεξει παρακατω και εχω διπλοτυπα

                if (expand.s2[lamp] != null) {//αν εχω αθρωπο με φακο

                    sh1 = new Person[expand.s1.length];//αντιγραφη ακτης1
                    sh2 = new Person[expand.s1.length];//αντιγραφη ακτης2

                    for (int q = 0; q < expand.s1.length; q++) {
                        sh1[q] = expand.s1[q];
                        sh2[q] = expand.s2[q];

                    }

                    sh1[lamp] = expand.s2[lamp];//μετακινηση ατομου με φακο
                    sh2[lamp] = null;

                    System.out.println("person: " + expand.s2[lamp].toString() + " goes back to shore 1 ==========");

                    cost1 = expand.s2[lamp].time;//ευρεση κοστους για το ατομο με τον φακο

                    str1 = "\nperson: " + expand.s2[lamp].toString() + " goes back to shore 1\n"
                            + "with cost " + cost1;

                }

                Person[] sh1a, sh2a;

                for (int i = 0; i < sh1.length; i++) {//παιρνω καθε ζευγαρι
                    for (int j = i + 1; j < sh1.length; j++) {
                        if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                            if (sh1[i] != null && sh1[j] != null) {
                                sh1a = new Person[sh1.length];
                                sh2a = new Person[sh2.length];//δημιουργια καινουριων ακτων που εχουν αποθηκευμενη την μεακινηση του φακου

                                for (int q = 0; q < sh1.length; q++) {
                                    sh1a[q] = sh1[q];
                                    sh2a[q] = sh2[q];
                                }

                                sh2a[i] = sh1[i];
                                sh2a[j] = sh1[j];//μετακινηση ατομων

                                System.out.println("person: " + sh1[i].toString() + " goes to shore 2");
                                System.out.println("person: " + sh1[j].toString() + " goes to shore 2\n");

                                cost = max(sh1[i].time, sh1[j].time);//ευρεση κοστους

                                str = str1 + "\nperson: " + sh1[i].toString() + " goes to shore 2\n"
                                        + "person: " + sh1[j].toString() + " goes to shore 2\n"
                                        + "with cost " + cost + "\n";

                                cost = cost1 + max(sh1[i].time, sh1[j].time);//προσθεση νεου κοστους

                                sh1a[i] = null;
                                sh1a[j] = null;//αδειασμα θεσεων

                                Random rand = new Random();
                                int num = rand.nextInt(1000) + 1;
                                String name = String.valueOf(depth) + "." + String.valueOf(1) + "-" + String.valueOf(num);
                                System.out.println(name);//ονομα κομβου

                                Node childNode = new Node(sh1a, sh2a, cost + expand.cost, depth, expand, name, str);//κομβος

                                childNode.show();
                                nextDepth.add(childNode);
                                System.out.println();

                            }
                        }
                    }
                }

            }
        }

        nodeList.addAll(nextDepth);

        return DFS(depth + 1, nodeList.get(0), nodeList, nextDepth, 0);
    }

    public String IDS(int currentDepth, Node start, int limit) {

        if (currentDepth - 1 == limit) {
            return "Limit reached. Failure";
        }

        ArrayList<Node> nodeList = new ArrayList();
        nodeList.add(start);

        String s = nestedIDS(1, nodeList, currentDepth++);
        System.out.println("Next depth: " + currentDepth + "=======================\n");

        if (s.equals("1")) {
            return "Success";
        }

        return IDS(currentDepth++, start, limit);
    }

    public String nestedIDS(int depth, ArrayList<Node> nodeList, int currentDepth) {

        ArrayList<Node> nextDepth = null;
        int cost = 0, cost1 = 0;
        String str = null, str1 = null;

        for (int node = 0; node < nodeList.size(); node++) {//για καθε κομβο που εχει δημιουργηθει για αυτο το βαθος

            Person[] sh1, sh2;//δημιουργω καινουριες ακτες
            nextDepth = new ArrayList();//δημιουργω λιστα αποθηκευσης απογονων κομβων για τον συγκεκριμενο κομβο

            Node n = nodeList.get(node);

            int c = 0;//βρισκω κενες θεσεις τον προορισμο
            for (int i = 0; i < n.s2.length; i++) {
                if (n.s2[i] == null) {
                    c++;
                }
            }

            if (c == n.s2.length) {//αν ειναι μονο κενες θεσεις, αρα ειναι ο αρχικος κομβος τοτε:

                for (int i = 0; i < n.s1.length; i++) {//παιρνω καθε πιθανο ζευγαρι
                    for (int j = i + 1; j < n.s1.length; j++) {//ι+1 για να αποφυγω διπλοτυπα
                        if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                            if (n.s1[i] != null && n.s1[j] != null) {

                                sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                                sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                                for (int q = 0; q < n.s1.length; q++) {
                                    sh1[q] = n.s1[q];
                                    sh2[q] = n.s2[q];
                                }

                                sh2[i] = n.s1[i];
                                sh2[j] = n.s1[j];

                                //System.out.println("person: " + n.s1[i].toString() + " goes to shore 2");
                                //System.out.println("person: " + n.s1[j].toString() + " goes to shore 2\n");
                                cost = max(n.s1[i].time, n.s1[j].time);

                                str = "\nperson: " + n.s1[i].toString() + " goes to shore 2\n"
                                        + "person: " + n.s1[j].toString() + " goes to shore 2\n"
                                        + "with cost " + cost;

                                sh1[i] = null;
                                sh1[j] = null;

                                Random rand = new Random();
                                int num = rand.nextInt(1000) + 1;
                                String name = String.valueOf(depth) + "." + String.valueOf(node + 1) + "-" + String.valueOf(num);
                                System.out.println(name);

                                Node childNode = new Node(sh1, sh2, cost, depth, n, name, str);

                                childNode.show();
                                nextDepth.add(childNode);
                                System.out.println();

                                int count = 0;//και μετραω τις αδειες θεσεις της ακτης1 για τον συγκεκριμενο κομβο
                                for (int s = 0; s < n.s1.length; s++) {

                                    if (childNode.s1[s] == null) {
                                        count++;
                                    }
                                }
                                if (count == nodeList.get(node).s1.length) {//αν η ακτη1 ειναι αδεια τοτε βρηκα τη λυση μου
                                    ArrayList<Node> path = new ArrayList();//μονοπατι λυσης

                                    path.add(childNode);

                                    System.out.println("Solution is: ");
                                    childNode.show();
                                    for (int k = 0; k < depth - 1; k++) {
                                        Node q = path.get(k).father;
                                        path.add(q);
                                        q.show();
                                    }
                                    System.out.println("\n");
                                    System.out.println("Nodes created: " + nodeList.size());
                                    System.out.println("Nodes to be etended: " + nextDepth.size());

                                    System.out.println("Cost = " + childNode.cost
                                            + "\n and the path is " + path.toString());
                                    return "1";
                                }

                            }
                        }
                    }
                }
            } else {
                for (int lamp = 0; lamp < n.s2.length; lamp++) {//για καθε πιαθνο ατομο που μπορει να φερει τον φακο

                    sh1 = new Person[0];//αντιγραφη 
                    sh2 = new Person[0];//βαζω 0 αρχικα για ναμην τρεξει παρακατω και εχω διπλοτυπα

                    if (n.s2[lamp] != null) {//αν εχω ναθρωπο με φακο

                        sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                        sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                        for (int q = 0; q < n.s1.length; q++) {
                            sh1[q] = n.s1[q];
                            sh2[q] = n.s2[q];

                        }

                        sh1[lamp] = n.s2[lamp];
                        sh2[lamp] = null;

                        //System.out.println("person: " + n.s2[lamp].toString() + " goes back to shore 1 ==========");
                        cost1 = n.s2[lamp].time;

                        str1 = "\nperson: " + n.s2[lamp].toString() + " goes back to shore 1\n"
                                + "with cost " + cost1;

                    }

                    Person[] sh1a, sh2a;

                    for (int i = 0; i < sh1.length; i++) {//παιρνω καθε ζευγαρι
                        for (int j = i + 1; j < sh1.length; j++) {
                            if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                                if (sh1[i] != null && sh1[j] != null) {
                                    sh1a = new Person[sh1.length];
                                    sh2a = new Person[sh2.length];

                                    for (int q = 0; q < sh1.length; q++) {
                                        sh1a[q] = sh1[q];
                                        sh2a[q] = sh2[q];
                                    }

                                    sh2a[i] = sh1[i];
                                    sh2a[j] = sh1[j];

                                    //System.out.println("person: " + sh1[i].toString() + " goes to shore 2");
                                    //System.out.println("person: " + sh1[j].toString() + " goes to shore 2\n");
                                    cost = max(sh1[i].time, sh1[j].time);

                                    str = str1 + "\nperson: " + sh1[i].toString() + " goes to shore 2\n"
                                            + "person: " + sh1[j].toString() + " goes to shore 2\n"
                                            + "with cost " + cost;

                                    cost = cost1 + max(sh1[i].time, sh1[j].time);

                                    sh1a[i] = null;
                                    sh1a[j] = null;

                                    Random rand = new Random();
                                    int num = rand.nextInt(1000) + 1;
                                    String name = String.valueOf(depth) + "." + String.valueOf(node + 1) + "-" + String.valueOf(num);
                                    System.out.println(name);

                                    Node childNode = new Node(sh1a, sh2a, cost + n.cost, depth, n, name, str);

                                    childNode.show();
                                    nextDepth.add(childNode);
                                    System.out.println();

                                    int count = 0;//και μετραω τις αδειες θεσεις της ακτης1 για τον συγκεκριμενο κομβο
                                    for (int s = 0; s < n.s1.length; s++) {

                                        if (childNode.s1[s] == null) {
                                            count++;
                                        }
                                    }
                                    if (count == nodeList.get(node).s1.length) {//αν η ακτη1 ειναι αδεια τοτε βρηκα τη λυση μου
                                        ArrayList<Node> path = new ArrayList();//μονοπατι λυσης

                                        path.add(childNode);

                                        System.out.println("Solution is: ");
                                        childNode.show();
                                        for (int k = 0; k < depth; k++) {
                                            Node q = path.get(k).father;
                                            path.add(q);
                                            q.show();
                                        }
                                        System.out.println("\n");
                                        System.out.println("Nodes created: " + nodeList.size());
                                        System.out.println("Nodes to be etended: " + nextDepth.size());

                                        System.out.println("Cost = " + childNode.cost
                                                + "\n and the path is " + path.toString());
                                        return "1";
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        if (depth < currentDepth) {
            return nestedIDS(depth + 1, nextDepth, currentDepth);
        }
        System.out.println("Depth " + currentDepth + " reached==================");
        return "2";

    }

    public String UCS(int depth, ArrayList<Node> nodeList, ArrayList<Node> extended) {
        Node n = nodeList.get(0);
        int min = nodeList.get(0).cost;

        for (int i = 1; i < nodeList.size(); i++) {//βρισκω κομβο με μικροτερο κοστος
            if (nodeList.get(i).cost <= min) {

                min = nodeList.get(i).cost;
                n = nodeList.get(i);
            }
        }

        int cost = 0, cost1 = 0;
        String str = null, str1 = null;

        Person[] sh1, sh2;//δημιουργω καινουριες ακτες

        int count = 0;//και μετραω τις αδειες θεσεις της ακτης1 για τον συγκεκριμενο κομβο

        for (int s = 0; s < n.s1.length; s++) {

            if (n.s1[s] == null) {
                count++;
            }
        }
        if (count == n.s1.length) {//αν η ακτη1 ειναι αδεια τοτε βρηκα τη λυση μου
            ArrayList<Node> path = new ArrayList();

            path.add(n);

            System.out.println("Solution is: ");
            n.show();

            try {
                for (int i = 0; i < path.size(); i++) {
                    Node q = path.get(i).father;
                    path.add(q);
                    q.show();

                }
            } catch (NullPointerException e) {
            };
            System.out.println("\n");
            System.out.println("Nodes created: " + nodeList.size() + extended.size());
            System.out.println("Nodes to be etended: " + nodeList.size());

            return "Cost = " + n.cost
                    + "\n and the path is " + path.toString();
        }

        int c = 0;//βρισκω κενες θεσεις τον προορισμο
        for (int i = 0; i < n.s2.length; i++) {
            if (n.s2[i] == null) {
                c++;
            }
        }

        if (c == n.s2.length) {//αν ειναι μονο κενες θεσεις, αρα ειναι ο αρχικος κομβος τοτε:

            for (int i = 0; i < n.s1.length; i++) {//παιρνω καθε πιθανο ζευγαρι
                for (int j = i + 1; j < n.s1.length; j++) {//ι+1 για να αποφυγω διπλοτυπα
                    if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                        if (n.s1[i] != null && n.s1[j] != null) {

                            sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                            sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                            for (int q = 0; q < n.s1.length; q++) {
                                sh1[q] = n.s1[q];
                                sh2[q] = n.s2[q];
                            }

                            sh2[i] = n.s1[i];
                            sh2[j] = n.s1[j];

                            //System.out.println("person: " + n.s1[i].toString() + " goes to shore 2");
                            //System.out.println("person: " + n.s1[j].toString() + " goes to shore 2\n");
                            cost = max(n.s1[i].time, n.s1[j].time);

                            str = "\nperson: " + n.s1[i].toString() + " goes to shore 2\n"
                                    + "person: " + n.s1[j].toString() + " goes to shore 2\n"
                                    + "with cost " + cost + "\n";

                            sh1[i] = null;
                            sh1[j] = null;

                            Random rand = new Random();
                            int num = rand.nextInt(1000) + 1;
                            String name = String.valueOf(n.depth + 1) + "-" + String.valueOf(num);
                            System.out.println(name);

                            Node childNode = new Node(sh1, sh2, cost, n.depth + 1, n, name, str);

                            childNode.show();
                            nodeList.add(childNode);
                            System.out.println();

                        }
                    }
                }
            }
        } else {
            for (int lamp = 0; lamp < n.s2.length; lamp++) {//για καθε πιαθνο ατομο που μπορει να φερει τον φακο

                sh1 = new Person[0];//αντιγραφη 
                sh2 = new Person[0];//βαζω 0 αρχικα για ναμην τρεξει παρακατω και εχω διπλοτυπα

                if (n.s2[lamp] != null) {//αν εχω ναθρωπο με φακο

                    sh1 = new Person[n.s1.length];//αντιγραφη ακτης1
                    sh2 = new Person[n.s1.length];//αντιγραφη ακτης2

                    for (int q = 0; q < n.s1.length; q++) {
                        sh1[q] = n.s1[q];
                        sh2[q] = n.s2[q];

                    }

                    sh1[lamp] = n.s2[lamp];
                    sh2[lamp] = null;

                    System.out.println("person: " + n.s2[lamp].toString() + " goes back to shore 1 ==========");

                    cost1 = n.s2[lamp].time;

                    str1 = "\nperson: " + n.s2[lamp].toString() + " goes back to shore 1\n"
                            + "with cost " + cost1;

                }

                Person[] sh1a, sh2a;

                for (int i = 0; i < sh1.length; i++) {//παιρνω καθε ζευγαρι
                    for (int j = i + 1; j < sh1.length; j++) {
                        if (i != j) {//για να μην συγκρινω τα ιδια ατομα
                            if (sh1[i] != null && sh1[j] != null) {
                                sh1a = new Person[sh1.length];
                                sh2a = new Person[sh2.length];

                                for (int q = 0; q < sh1.length; q++) {
                                    sh1a[q] = sh1[q];
                                    sh2a[q] = sh2[q];
                                }

                                sh2a[i] = sh1[i];
                                sh2a[j] = sh1[j];

                                System.out.println("person: " + sh1[i].toString() + " goes to shore 2");
                                System.out.println("person: " + sh1[j].toString() + " goes to shore 2\n");

                                cost = max(sh1[i].time, sh1[j].time);

                                str = str1 + "\nperson: " + sh1[i].toString() + " goes to shore 2\n"
                                        + "person: " + sh1[j].toString() + " goes to shore 2\n"
                                        + "with cost " + cost + "\n";

                                cost = cost1 + max(sh1[i].time, sh1[j].time);

                                sh1a[i] = null;
                                sh1a[j] = null;

                                Random rand = new Random();
                                int num = rand.nextInt(1000) + 1;
                                String name = String.valueOf(n.depth + 1) + "-" + String.valueOf(num);
                                System.out.println(name);

                                Node childNode = new Node(sh1a, sh2a, cost + n.cost, n.depth + 1, n, name, str);

                                childNode.show();
                                nodeList.add(childNode);
                                System.out.println();

                            }
                        }
                    }
                }

            }
        }
        extended.add(n);

        nodeList.remove(n);

        return UCS(0, nodeList, extended);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Give the number of people: ");

        int people = in.nextInt();//δινω αριθμο ανθρωπων

        //δημιουργω τις οχθες
        Person[] shore1 = new Person[people];
        Person[] shore2 = new Person[people];

        //αποθηκευση ταχυτητων καθε ανθρωπου
        int times[] = new int[people];

        for (int i = 0; i < people; i++) {
            System.out.println("Give the speed for person " + (i + 1) + ": ");
            times[i] = in.nextInt();//ο χρηστης δινει ταχυτητες

            //αρχικη κατασταση
            shore1[i] = new Person(times[i], i);//γεμιζω την μια οχθη
        }

        ArrayList<Node> nodeList = new ArrayList();//αποθηκευση κομβων

        Random rand = new Random();
        int num = rand.nextInt(1000) + 1;//τυχαιος αριθμος

        String name = "0.1-" + String.valueOf(num);//ονομα κομβου
        //System.out.println(name);

        System.out.println(name);
        Node start = new Node(shore1, shore2, 0, 0, null, name, null);//δημιουργια αρχικου κομβου
        start.show();
        nodeList.add(start);
        System.out.println();

        ArrayList<Node> nextDepth = new ArrayList();

        //System.out.println(nodeList.toString());
        AI ai = new AI(nodeList, nextDepth);
    }

}
