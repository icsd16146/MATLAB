package dvdstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DVDStore {

    public static void main(String[] args) throws ParseException {

        //εδω φτιαχνω ημερομηνιες για τις ταινιες
        String dvd1Date = "04/10/2019";
        Date releaseDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(dvd1Date);

        //εδω φτιαχνω ημ/νιες για τις καρτες
        String expD1 = "10/2022";
        Date card1 = new SimpleDateFormat("MM/yyyy").parse(expD1);

        //λιστες γλωσσων
        ArrayList<Languages> dvdLanguages1 = new ArrayList<>();
        dvdLanguages1.add(Languages.greek);
        dvdLanguages1.add(Languages.albanian);
        dvdLanguages1.add(Languages.chinese);
        ArrayList<Languages> dvdLanguages2 = new ArrayList<>();
        dvdLanguages2.add(Languages.italian);
        dvdLanguages2.add(Languages.japanese);
        dvdLanguages2.add(Languages.portugese);

        //λιστες υποτίτλων
        ArrayList<Subtitles> dvdSubtitles1 = new ArrayList<>();
        dvdSubtitles1.add(Subtitles.greek);
        dvdSubtitles1.add(Subtitles.english);
        dvdSubtitles1.add(Subtitles.albanian);
        ArrayList<Subtitles> dvdSubtitles2 = new ArrayList<>();
        dvdSubtitles2.add(Subtitles.chinese);
        dvdSubtitles2.add(Subtitles.french);
        dvdSubtitles2.add(Subtitles.german);

        //εδω φτιαχνω λιστες με ηθοποιους
        ArrayList<String> dvd1Actors = new ArrayList<String>();
        dvd1Actors.add("Joaquin Phoenix");
        dvd1Actors.add("Robert De Niro");
        dvd1Actors.add("Zazie Beetz");

        //εδω φτιαχνω μερικα DVD
        DVD dvd1 = new DVD("Joker", "Todd Phillips", releaseDate1, 122, (float) 18.99, dvdLanguages1, dvdSubtitles1, dvd1Actors, MovieGenre.drama);
        //System.out.println(dvd1.showDVD());

        //εδω φτιάχνω την λίστα με τα dvd
        ArrayList<DVD> dvds = new ArrayList<>();
        dvds.add(dvd1);

        //εδω φτιαχνω μερικούς πελάτες
        Customer c1 = new Customer("CreepyFox", "11101998", "Sotos", "Papaioannou", "Michalakopoulou179");

        //εδώ φτιαχνω υποκειμενη ΒΔ χρηστων
        ArrayList<Customer> DB = new ArrayList();
        DB.add(c1);

        //εδω γινεται συνδεση χρηστων
        c1.logIn("CreepyFox", "11101998");

        //εδω φτιάχνω μερικές καρτες
        c1.createCard(CardType.credit, "123456789", card1, "179");
        Card c = c1.giveActiveCard();

        //εδω φτιαχνω μερικές παραγγελίες
        Order orderC1 = c1.giveActiveCard().createOrder(dvds);

        //εμφανιση παραγγελιας
        orderC1.showOrder();

        //εμφανιση ιστορικου πελατων
        c1.showOrderHistory();

        //τροποποιηση παραγγελιων
        c.modifyOrder(orderC1, expD1, dvds);

        //πληρωμη παραγγελιων
        c.purchaseOrder(orderC1);

        //ακυρωση παραγγελιων
        c.cancelOrder(orderC1);
    }

}
