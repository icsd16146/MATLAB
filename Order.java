package dvdstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Order {

    private int id;
    private float cost;
    private String address, reasonOfPending = null, reasonOfCancellation = null, ID;
    private Customer orderOwner;
    private Card card;
    private OrderStatus os; //αλλαζει δυναμικα
    private Date orderDate, deliveredDate;
    private ArrayList<DVD> dvds;
    private ArrayList<Integer> copies;

    Order(Customer owner, Card card, Date od, String address, float cost, ArrayList<DVD> movies, ArrayList<Integer> copies) {
        this.orderOwner = owner;
        this.card = card;
        this.orderDate = od;
        this.address = address;
        this.os = os.created;
        this.cost = cost;
        this.copies = copies;
        this.dvds = movies;
        if (this.os == os.created) {
            System.out.println("Congrats! Your order has been created.");
        }

        Random rand = new Random();
        this.id = rand.nextInt(9999);   //παιρνω τυχαιο τετραψηφιο
        String stringId = Integer.toString(id); //μετατροπη τετραψηφιου σε αλφαρηθμιτικο
        this.ID = card.giveID() + stringId;   //δημιουργια μοναδικου αναγνωριστικου
    }

    protected void modifyOrder(Date od, String address, float cost, ArrayList<DVD> movies, ArrayList<Integer> copies) {
        if (this.os == OrderStatus.created) {
            this.address = address;
            this.copies = copies;
            this.cost = cost;
            this.dvds = movies;
            this.orderDate = od;
        }else{
            System.out.println("Order cannot be modified or does not exist.");
        }
    }

    protected void changeStatus(OrderStatus os) {
        this.os = os;
        System.out.println("The order is now "+this.os);
    }

    protected float giveReceipt() {
        return this.cost;
    }

    protected Date giveDate() {
        return this.orderDate;
    }

    protected ArrayList<DVD> giveDVDs() {
        return this.dvds;
    }

    protected ArrayList<Integer> giveCopies() {
        return this.copies;
    }

    protected OrderStatus giveStatus() {
        return this.os;
    }

    protected void getReasonOfCancelation(String s) {
        this.reasonOfCancellation = s;
    }

    protected void getReasonOfPending(String s) {
        this.reasonOfPending = s;
    }

    protected String giveReasonOfPending() {
        return "Your order is pending because: " + this.reasonOfPending;
    }

    protected void getDeliveryDate(Date d) {
        this.deliveredDate = d;
    }

    protected Customer giveCustomer() {
        return this.orderOwner;
    }

    protected Card giveCard() {
        return this.card;
    }

    protected void showOrder() {
        System.out.println("Order data:");
        String movies = "";
        for (int i = 0; i < this.dvds.size(); i++) {
            movies += "Movie" + (i + 1) + ": " + this.dvds.get(i).showTitle() + "Copy(-ies): " + this.copies.get(i) + "\n";
        }

        System.out.println("Name: " + this.orderOwner.name + this.orderOwner.surname
                + "\nCard: " + this.card.showCard()
                + "\nDate: " + this.orderDate
                + "\nAddress: " + this.address
                + "\nCost: " + this.cost
                + "\nOrder: \n" + movies);
    }
}
