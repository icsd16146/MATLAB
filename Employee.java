
package dvdstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends User{
    final private EmployeeRole er;
    
    Employee(EmployeeRole er){
        this.er=er;
    }
    
    protected void pendOrder(Order order, String pending){
        order.getReasonOfPending(pending);
        order.changeStatus(OrderStatus.pending);
        order.giveCustomer().addPendingOrder(order);
                
    }
    
    protected void deliverOrder(Order order, String date) throws ParseException{
        if(this.er==EmployeeRole.deliveryboy){
            Date deliveredDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            order.getDeliveryDate(deliveredDate);   //παιρνω ημ/νια παραδοσης
            
            order.changeStatus(OrderStatus.finished);   //ολοκληρωση παραγγελιας
            order.giveCustomer().giveActiveCard().changeCardStatus(CardStatus.finished);//ολοκληρωση καρτας
            
            order.giveCustomer().addFinishedOrder(order);   //προσθηκη παραγγελιας στις ολοκληρωμενες
            order.giveCustomer().removePendingOrder(order); //διαγραφη παραγγελιας απο τις εκκρεμης
            System.out.println("The order has been delivered.");
        }
        else{
            System.out.println("Inappropriate role for such action.");
        }  
    }
}
