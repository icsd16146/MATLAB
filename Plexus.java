package ai2;

import java.util.ArrayList;
import java.util.Random;

public class Plexus {

    private int lines, max;
    // private Knob[][] plex;
    private int acmes;
    //private Acme[][] acm;
    private PlexParticles p, start, finish;
    // private ArrayList<Acme> sub;
    public PlexParticles[][] pp;

    Plexus(int lines, int max, float percent) {
        this.lines = lines;
        //this.plex = new Knob[lines][lines];
        this.acmes = 2 * lines * (lines - 1);
        this.max = max;
        //this.acm = new Acme[lines * 2][lines];
        this.pp = new PlexParticles[(lines * 2) - 1][(lines * 2) - 1];

        System.out.println("acmes needed=" + acmes);

        Random rand = new Random();

        for (int i = 0; i < (lines * 2) - 1; i++) {
            for (int j = 0; j < (lines * 2) - 1; j++) {
                if (j % 2 == 0 && i % 2 == 0) {

                    p = new PlexParticles("*", max,i,j);
                    pp[i][j] = p;

                }
                if (i % 2 != 0) {//αν το ι ειναι μονος

                    p = new PlexParticles("+", max,i,j);
                    pp[i][j] = p;

                }
                if (j % 2 != 0) {//αν το ξ ειναι μονος

                    p = new PlexParticles("+", max,i,j);
                    pp[i][j] = p;

                }
                if (i % 2 != 0 && j % 2 != 0) {//αν ι και ξ ειναι μονοι αριθμοι

                    pp[i][j] = null;
                }
            }

        }

        float submitted = acmes * percent;//ακμες που θα αφαιρεθουν

        System.out.println("acmes submitted=" + submitted);

        ArrayList<PlexParticles> subed = new ArrayList();//αποθηκευση ακμων που θα διαγραφουν
        int x, y;

        for (int i = 0; i < submitted;) {
            int eq = 0;
            x = rand.nextInt((lines * 2) - 1);//τυχαιες συντεταγμενες
            y = rand.nextInt((lines * 2) - 1);
            for (int j = 0; j < subed.size(); j++) {
                if (pp[x][y].equals(subed.get(j))) {
                    eq++;
                }
            }
            if (eq == 0) {
                if (pp[x][y] != null) {
                    if (pp[x][y].getSymbol().equals("+")) {
                        pp[x][y].setSymbol("x");
                        i++;
                    }
                }
            }
        }

        //τυχαια αναπαραγωγη αρχικου κομβου και κομβου στοχου
        int x1, x2, y1, y2;

        do {
            x1 = rand.nextInt((lines * 2) - 1);
            x2 = rand.nextInt((lines * 2) - 1);
            y1 = rand.nextInt((lines * 2) - 1);
            y2 = rand.nextInt((lines * 2) - 1);

        } while ((x1 == x2 && y1 == y2) || (x1 % 2 != 0 || y1 % 2 != 0) || (x2 % 2 != 0 || y2 % 2 != 0));

        this.start = pp[x1][y1];
        this.finish = pp[x2][y2];

        pp[x1][y1].setSymbol("S");
        pp[x2][y2].setSymbol("F");
        //System.out.println(" " + x1 + "-" + y1 + " " + x2 + "-" + y2);

    }

    public void show() {
        for (int i = 0; i < (lines * 2) - 1; i++) {
            for (int j = 0; j < (lines * 2) - 1; j++) {
                if (i % 2 == 0) {//αν το ι ειναι ζυγος

                    System.out.print("   " + pp[i][j].getSymbol());
                } else {//αν το ι ειναι μονος

                    if (pp[i][j] == null) {//αν ειναι κενη η τιμη

                        System.out.print("    ");

                    } else {//αν δεν ειναι κενη

                        System.out.print("   " + pp[i][j].getSymbol());
                    }

                }
            }
            System.out.println();
        }

    }

    public void showValues() {
        for (int i = 0; i < (lines * 2) - 1; i++) {
            for (int j = 0; j < (lines * 2) - 1; j++) {
                if (i % 2 == 0) {//αν το ι ειναι ζυγος

                    System.out.print("   " + pp[i][j].giveValue());
                } else {//αν το ι ειναι μονος

                    if (pp[i][j] == null) {//αν ειναι κενη η τιμη

                        System.out.print("    ");

                    } else {//αν δεν ειναι κενη

                        System.out.print("   " + pp[i][j].giveValue());
                    }

                }
            }
            System.out.println();
        }
    }

    public PlexParticles getStart() {
        return this.start;
    }

    public PlexParticles getFinish() {
        return this.finish;
    }
    
    //public PlexParticles[][] givePlexus(){ return this.pp; }

}
