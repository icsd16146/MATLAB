package dvdstore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {

    private String address;

    private ArrayList<Order> everyOrder = new ArrayList<>();
    private ArrayList<Order> cancelledOrders = new ArrayList<>();
    private ArrayList<Order> finishedOrders = new ArrayList<>();
    private ArrayList<Order> pendingOrders = new ArrayList<>();

    private ArrayList<Card> cancelledCards = new ArrayList<>();
    private ArrayList<Card> frozenCards = new ArrayList<>();
    private ArrayList<Card> finishedCards = new ArrayList<>();
    private ArrayList<Card> everyCard = new ArrayList<>();
    private Card activeCard = null;

    Customer(String username, String password, String name, String surname, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    protected void addEveryOrder(Order o) {
        if (this.us == UserStatus.logedIn) {
            everyOrder.add(o);
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void addFinishedOrder(Order o) {
        if (this.us == UserStatus.logedIn) {
            finishedOrders.add(o);
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void addPendingOrder(Order o) {
        if (this.us == UserStatus.logedIn) {
            pendingOrders.add(o);
        } else {
            System.out.println("You are not loged in.");
        }
    }
    
    protected void addCancelledOrder(Order o) {
        if (this.us == UserStatus.logedIn) {
            cancelledOrders.add(o);
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void createCard(CardType ct, String cardNumber, Date expD, String safeCode) {
        if (this.us == UserStatus.logedIn) {
            if (this.activeCard == null) {
                Card c = new Card(this, ct, cardNumber, expD, safeCode);
                this.activeCard = c;
                this.everyCard.add(c);
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void cancelCard(Card card) {
        if (this.us == UserStatus.logedIn) {
            if (card.giveCardStatus() == CardStatus.frozen) {
                card.changeCardStatus(CardStatus.cancelled);
                this.cancelledCards.add(card);
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void deleteCard(Card c) {
        if (this.us == UserStatus.logedIn) {
            if (c.giveCardStatus() != CardStatus.frozen) {
                everyCard.remove(c);
                finishedCards.remove(c);
                cancelledCards.remove(c);
            }
            if (activeCard.equals(c)) {
                activeCard = null;
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void disableActiveCard() {
        if (this.us == UserStatus.logedIn) {
            activeCard = null;
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected Card giveActiveCard() {
        if (this.us == UserStatus.logedIn) {
            return this.activeCard;
        } else {
            System.out.println("You are not loged in.");
        }
        return null;
    }

    protected String giveAddress() {
        return this.address;
    }

    protected void removePendingOrder(Order o) {
        if (this.us == UserStatus.logedIn) {
            pendingOrders.remove(o);
        } else {
            System.out.println("You are not loged in.");
        }
    }

    @Override
    protected void logIn(String username, String password) {
        if (this.username.matches(username) && this.password.matches(password)) {
            System.out.println("Welcome!");
            this.us = UserStatus.logedIn;
        }
    }

    @Override
    protected void logOut() {
        this.us = UserStatus.logedOut;
    }

    protected void modifyCard(Card card, Date d, String n, String z) {
        if (this.us == UserStatus.logedIn) {
            if (card.giveCardStatus() == CardStatus.active) {
                card.takeExpDate(d);
                card.takeNumber(n);
                card.takeZIP(z);
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void showActiveCard() {
        if (this.us == UserStatus.logedIn) {
            System.out.println(activeCard.showCard());
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void showAllCards() { //εδω χρειαζεται exception
        if (this.us == UserStatus.logedIn) {
            //System.out.println("Active Card: " + activeCard.showCard());
            System.out.println("Below's the list of all your frozen cards.");
            for (int i = 0; i < frozenCards.size(); i++) {
                System.out.println("Card" + (i + 1) + ": " + frozenCards.get(i).showCard());
            }
            System.out.println("Below's the list of all your finished cards.");
            for (int i = 0; i < finishedCards.size(); i++) {
                System.out.println("Card" + (i + 1) + ": " + finishedCards.get(i).showCard());
            }
            System.out.println("Below's the list of all your cancelled cards.");
            for (int i = 0; i < cancelledCards.size(); i++) {
                System.out.println("Card" + (i + 1) + ": " + cancelledCards.get(i).showCard());
            }
        } else {
            System.out.println("You are not loged in.");
        }
    }

    protected void showOrderHistory() {  //εμφανιση ιστορικου παραγγελιων
        if (this.us == UserStatus.logedIn) {
            if (!everyOrder.isEmpty()) {
                System.out.println("This is the order history of " + this.name + " " + this.surname + ".");
                for (int i = 0; i < everyOrder.size(); i++) {
                    String order = "";
                    Date d = everyOrder.get(i).giveDate();
                    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
                    String strDate = dateFormat.format(d);
                    for (int j = 0; j < everyOrder.get(i).giveDVDs().size(); j++) {
                        order += everyOrder.get(i).giveDVDs().get(j).showTitle() + " movie, " + everyOrder.get(i).giveCopies().get(i) + " copy(-ies).\n";
                    }
                    System.out.println("Order " + (i + 1) + ":\nDate: " + strDate + "\n" + order);
                }
            } else {
                System.out.println(this.name + " " + this.surname + " has not made any order.");
            }
        } else {
            System.out.println("You are not loged in.");
        }

    }

}
