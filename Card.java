package dvdstore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Card {

    SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
    private int id;
    private Date creationDate, expirationDate;
    private CardType ct;
    private CardStatus cs;
    private String cardNumber, safeCode, ID;
    private Customer cardOwner;
    private float balance;
    private HashMap<DVD, Integer> dvds = new HashMap();

    Card(Customer c, CardType ct, String cardNumber, Date expD, String safeCode) {
        this.cardOwner = c;
        this.ct = ct;
        this.cardNumber = cardNumber;
        this.expirationDate = expD;
        this.safeCode = safeCode;
        this.cs = CardStatus.active;
        this.creationDate = Calendar.getInstance().getTime();

        Random rand = new Random();
        this.id = rand.nextInt(9999);   //παιρνω τυχαιο τετραψηφιο
        String stringId = Integer.toString(id); //μετατροπη τετραψηφιου σε αλφαρηθμιτικο
        String customerId = cardOwner.toString();   //μετατροπη ιδιοκτητη καρτας σε αλφαριθμητικο
        this.ID = cardNumber + customerId + stringId;   //δημιουργια μοναδικου αναγνωριστικου

        this.balance = rand.nextInt(100);
    }

    protected Order createOrder(ArrayList<DVD> dvds) {
        if (this.cardOwner.us == UserStatus.logedIn) {
            if (this.cardOwner.giveActiveCard() != null) {
                Scanner sc = new Scanner(System.in);
                String answer = null;
                ArrayList<DVD> picked = null;
                ArrayList<Integer> copies = null;
                System.out.println("Wanna buy a DVD?\nPress: y for 'yes' or n for 'no'");
                do {
                    answer = sc.nextLine();
                    if (answer.matches("n")) {
                        return null;
                    }
                    if (answer.matches("y")) {
                        picked = pickDVDs(dvds);    //επιλογη dvd πελατη
                        showPickedMovies(picked);   //εμφανιση επιλεγμενων dvd
                        System.out.println("Are you happy with this list? Press: 'y' for yes or 'n' for no.");
                        answer = sc.nextLine();
                        if (answer.matches("y")) {
                            break;
                        }
                        if (answer.matches("n")) {
                            System.out.println("Ordering action has been cancelled.");
                            return null;
                        }
                    } else {
                        System.out.println("Press: 'y' for yes or 'n' for no");
                    }
                } while (!answer.matches("n") & !answer.matches("y"));
                copies = pickCopies(picked);    //επιλογη αριθμου τεμαxιων και ελεγχος αριθμου αντιγραφων
                float receipt = cost(picked, copies);   //υπολογισμος συνολικου κόστους
                System.out.println("The cost will be " + receipt + ".\nSave this order?(Type: 'y' for yes or 'n' for no): ");
                String ans = sc.nextLine();
                if (ans.matches("y")) {
                    Date currentDate = new Date();
                    Order o = new Order(this.cardOwner, this.cardOwner.giveActiveCard(), currentDate, this.cardOwner.giveAddress(), receipt, picked, copies);    //η παραγγελια εχει δημιουργηθει αλλα δεν εχει χρεωθει ακομα
                    this.cardOwner.addEveryOrder(o);
                    this.cardOwner.giveActiveCard().changeCardStatus(CardStatus.frozen);
                    this.cardOwner.disableActiveCard();
                    return o;
                } else {
                    return null;
                }

            } else {
                System.out.println("You need to have an active card to make an order.\n");
            }
            return null;
        } else {
            System.out.println("You are not loged in.");
        }
        return null;
    }

    protected void modifyOrder(Order o, String address, ArrayList<DVD> dvds) {
        System.out.println("You chose to modify your order");
        if (this.cardOwner.us == UserStatus.logedIn) {
            if (o != null && o.giveStatus() == OrderStatus.created) {
                if (o.giveCard().giveCardStatus() == CardStatus.frozen) {
                    Scanner sc = new Scanner(System.in);
                    ArrayList<DVD> picked = null;
                    ArrayList<Integer> copies = null;
                    String answer = "";
                    do {
                        picked = pickDVDs(dvds);    //επιλογη dvd πελατη
                        showPickedMovies(picked);   //εμφανιση επιλεγμενων dvd
                        System.out.println("Are you happy with this list? Press: 'y' for yes or 'n' for no.");
                        answer = sc.nextLine();
                        if (answer.matches("y")) {

                        }
                        if (answer.matches("n")) {
                            System.out.println("Modifying order action has been cancelled.");

                        }
                    } while (!answer.matches("n") & !answer.matches("y"));
                    copies = pickCopies(picked);    //επιλογη αριθμου τεμαxιων και ελεγχος αριθμου αντιγραφων
                    float receipt = cost(picked, copies);   //υπολογισμος συνολικου κόστους
                    System.out.println("The cost will be " + receipt + ".\nSave this order?(Type: 'y' for yes or 'n' for no): ");
                    String ans = sc.nextLine();
                    if (ans.matches("y")) {
                        Date currentDate = new Date();
                        o.modifyOrder(currentDate, address, receipt, dvds, copies);
                    } else {
                        System.out.println("Changes to your order were saved.");
                    }
                } else {
                    System.out.println("Card Status must be frozen to modify the order.");
                }
            } else {
                System.out.println("Such order does not exist.");
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void purchaseOrder(Order order) {  //αγορα παραγγελιας
        if (this.cardOwner.us == UserStatus.logedIn) {
            //εδω το συστημα διαχειρισης πληρωμων αντλει το ποσο
            if (order != null && order.giveStatus() == OrderStatus.created) {
                if (order.giveCard().giveCardStatus() == CardStatus.frozen) {
                    if (this.giveBalance() >= order.giveReceipt()) {   //αν εχει ο πελατης αρκετα λεφτα στην καρτα για να πληρωσει
                        this.saveDVDs(order.giveDVDs(), order.giveCopies());
                        //ενημερωση μεσω triggers για εγγραφη
                        //προχωρα στην χρεωση
                        //ενημερωση για επιτυχια/αποτυχια χρεωσης
                        //σε περιπτωση επιτυχης χρεωσης δινεται εντολη στο συστ. διαχ. προμηθ. για restocking
                        order.changeStatus(OrderStatus.paid);           //αλλαγη καταστασης παραγγελιας σε πληρωμενη
                        this.changeBalance(order.giveReceipt());   //μειωνονται λεφτα στην καρτα
                        System.out.println("Congrats! Your order has been paid.");
                        addPurchases(order.giveDVDs(), order.giveCopies());
                        addParticipation(order.giveDVDs());
                    }
                } else {
                    System.out.println("Card Status must be frozen for purchase.");
                }
            } else {
                System.out.println("Order has already been paid or does not exist.");
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void cancelOrder(Order order) {
        if (this.cardOwner.us == UserStatus.logedIn) {
            Scanner sc = new Scanner(System.in);
            if (order.giveCard().giveCardStatus() == CardStatus.frozen) {
                if (order.giveStatus() == OrderStatus.created) {
                    System.out.println("Please give us the reason for cancelling this order: ");
                    String s = sc.nextLine();
                    order.getReasonOfCancelation(s);    //δινεται ο λογος ακυρωσης και αποθηκευεται

                    Date now = Calendar.getInstance().getTime();
                    order.getDeliveryDate(now);             //αποθηκευση μερας ακυρωσης

                    order.changeStatus(OrderStatus.cancelled);

                    this.cardOwner.addCancelledOrder(order);
                    System.out.println("Your order has been cancelled successfully.");
                } else {
                    System.out.println("Your order cannot be cancelled.");
                }
            } else {
                System.out.println("Card Status must be frozen.");
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    private void addPurchases(ArrayList<DVD> dvd, ArrayList<Integer> copy) {
        for (int i = 0; i < dvd.size(); i++) {
            dvd.get(i).raisePurchasedItems(i);
        }
    }

    private void addParticipation(ArrayList<DVD> dvd) {
        for (int i = 0; i < dvd.size(); i++) {
            dvd.get(i).raiseOrderParticipation();
        }
    }

    protected void saveDVDs(ArrayList<DVD> dvdList, ArrayList<Integer> copies) {    //αποθηκευση ποσοτητων dvd
        for (int i = 0; i < dvdList.size(); i++) {
            for (Map.Entry<DVD, Integer> entry : dvds.entrySet()) {
                if (entry.getKey().equals(dvdList.get(i))) {    //αν το dvd υπαρχει ηδη στον χαρτη
                    dvds.replace(entry.getKey(), copies.get(i) + dvds.get(entry.getKey())); //προσθεσε τα επιπλεον αγορασμενα
                } else {
                    dvds.put(dvdList.get(i), copies.get(i));    //προσθηκη νεων dvd
                }
            }
        }

    }

    protected float giveBalance() {
        return this.balance;
    }

    protected void changeBalance(float b) {
        this.balance -= b;
    }

    protected String giveID() {
        return this.ID;
    }

    protected String showCard() {
        String card = "\tName: " + this.cardOwner.name + " " + this.cardOwner.surname + "\n\tType: " + this.ct
                + "\n\tNumber: " + this.cardNumber + "\tDate: " + formatter.format(this.expirationDate)
                + "\tZIP: " + this.safeCode;
        return card;
    }

    protected void takeNumber(String n) {
        this.cardNumber = n;
    }

    protected void takeExpDate(Date d) {
        this.expirationDate = d;
    }

    protected void takeZIP(String z) {
        this.safeCode = z;
    }

    protected void changeCardStatus(CardStatus cs) {
        this.cs = cs;
    }

    protected CardStatus giveCardStatus() {
        return this.cs;
    }

    private ArrayList<DVD> pickDVDs(ArrayList<DVD> moviesList) { //λιστα που δείχνει τις ταινίες που έχει επιλέξει ο πελάτης, μπορεί να διαλέξει το ίδιο dvd πάνω απο μια φορα
        if (this.cardOwner.us == UserStatus.logedIn) {
            ArrayList<DVD> pickedMovies = new ArrayList();

            Scanner sc = new Scanner(System.in);
            System.out.println("Here is the collection of our DVDs:");
            for (int i = 0; i < moviesList.size(); i++) {
                System.out.println("DVD-" + i + ": \n" + moviesList.get(i).showDVD() + "\n==========");
            }
            System.out.println("Type the numer of different movies you want to buy: ");
            int numOfDvds = sc.nextInt();
            for (int i = 0; i < numOfDvds; i++) {
                System.out.println("Type the number that corresponds to the DVD you want. (i.e. 0 corresponds to Joker): ");
                int movie = -1;
                do {
                    movie = sc.nextInt();
                    if (movie > -1 && movie < moviesList.size()) {
                        pickedMovies.add(moviesList.get(movie));
                        System.out.println("You picked " + moviesList.get(movie).showTitle());
                    } else {
                        System.out.println("Invalid number.");
                    }
                } while (movie < 0 || movie > moviesList.size());
            }
            System.out.println("Thank you for choosing!");
            return pickedMovies;
        } else {
            System.out.println("You are not loged in.");
        }
        return null;
    }

    private ArrayList<Integer> pickCopies(ArrayList<DVD> pickedMovies) {    //διαλεγει ποσα αντιγραφα θελει ενω παραλληλα γινεται ελεχγος πως υπαρχουν αρκετα αντιγραφα
        if (this.cardOwner.us == UserStatus.logedIn) {
            System.out.println("Now you have to type how many copies of each DVD you want(use numbers): ");
            ArrayList<Integer> copies = new ArrayList();
            Scanner sc = new Scanner(System.in);
            int c;
            for (int i = 0; i < pickedMovies.size(); i++) {
                System.out.println("How many copies of " + pickedMovies.get(i).showTitle() + " do you want?");
                c = sc.nextInt();
                if (pickedMovies.get(i).giveCopies() <= 0) {
                    System.out.println("This item has been sold out.");
                    i++;
                }
                if (c <= pickedMovies.get(i).giveCopies()) {
                    System.out.println("Are you sure " + c + " is the number of copies you want for the movie " + pickedMovies.get(i).showTitle() + "?(Type: 'y' for yes or 'n' for no): ");
                    String line = sc.nextLine();
                    String ans = sc.nextLine();
                    if (ans.matches("y")) {
                        copies.add(c);
                    } else {
                        i--;
                    }
                } else {
                    do {
                        System.out.println("Not enough copies. Please type a lower number.");
                        c = sc.nextInt();
                    } while (c > pickedMovies.get(i).giveCopies());
                    System.out.println("That's an acceptable number.");
                    i--;
                }
            }
            return copies;
        } else {
            System.out.println("You are not loged in.");
        }
        return null;
    }

    private void showPickedMovies(ArrayList<DVD> pickedList) {
        if (this.cardOwner.us == UserStatus.logedIn) {
            System.out.println("This is the list of the DVDs you chose: ");
            for (int i = 0; i < pickedList.size(); i++) {
                System.out.println(i + 1 + ": " + pickedList.get(i).showTitle());
            }

        } else {
            System.out.println("You are not loged in.");
        }
    }

    private float cost(ArrayList<DVD> dvdCost, ArrayList<Integer> numOfCopies) {
        if (this.cardOwner.us == UserStatus.logedIn) {
            float receipt = 0;
            for (int i = 0; i < dvdCost.size(); i++) {
                receipt += dvdCost.get(i).giveCost() * numOfCopies.get(i);
            }
            return receipt;
        } else {
            System.out.println("You are not loged in.");
        }
        return 0;
    }
}
