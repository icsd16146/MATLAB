package dvdstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DVD {

    private int id, minutes, copies, purchasedItems = 0, numOfOrdersParticipated = 0;
    private float cost; //αλλαζει δυναμικα (?)
    private String title, director, ID;
    private ArrayList<Languages> dvdLanguages;
    private ArrayList<Subtitles> dvdSubtitles;
    private ArrayList<String> actors = new ArrayList<>();
    private Date productionDate;
    private MovieGenre mg;

    DVD(String title, String director, Date pro, int m, float cost, ArrayList<Languages> dvdLanguages, ArrayList<Subtitles> dvdSubtitles, ArrayList<String> actors, MovieGenre mg) {
        this.title = title;
        this.director = director;
        this.productionDate = pro;
        this.minutes = m;
        this.cost = cost;
        this.dvdLanguages = dvdLanguages;
        this.dvdSubtitles = dvdSubtitles;
        this.actors = actors;
        this.mg = mg;

        //τα αντιγραφα αλλαζουν δυναμικα... με καποιον τροπο...
        Random rand = new Random();
        this.id = rand.nextInt(9999);   //παιρνω τυχαιο τετραψηφιο
        String stringId = Integer.toString(id); //μετατροπη τετραψηφιου σε αλφαρηθμιτικο
        String date = productionDate.toString();    //μετατροπη ημερομηνιας παραγωγής σε αλφαριθμητικο
        this.ID = title + date + stringId;   //δημιουργια μοναδικου αναγνωριστικου

        this.copies = rand.nextInt(50);
    }

    protected void raiseOrderParticipation() {
        this.numOfOrdersParticipated++;
    }

    protected void raisePurchasedItems(int i) {
        this.purchasedItems += i;
    }

    protected String giveID() {
        return this.ID;
    }

    protected float giveCost() {
        return this.cost;
    }

    protected int giveCopies() {
        return this.copies;
    }

    protected String showDVD() {
        String n = " ";
        for (int i = 0; i < actors.size(); i++) {
            n = n + "\n    " + actors.get(i);
        }
        String l = " ";
        for (int i = 0; i < dvdLanguages.size(); i++) {
            l = l + "\n     " + dvdLanguages.get(i).toString();
        }
        String sub = " ";
        for (int i = 0; i < dvdSubtitles.size(); i++) {
            sub = sub + "\n     " + dvdSubtitles.get(i).toString();
        }
        String s = "Title: " + this.title
                + "\nProduction: " + this.productionDate
                + "\nGenre: " + this.mg
                + "\nCost: " + this.cost
                + "$\nDuration: " + this.minutes
                + " min.\nDirector: " + this.director
                + "\nActors: " + n
                + "\nLanguages: " + l
                + "\nSubtitles: " + sub;
        return s;
    }

    protected String showTitle() {
        return this.title;
    }
}
