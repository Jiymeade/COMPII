import java.util.ArrayList;
import java.util.List;

public class Invoice {
   private Customers customers;
   private List<LineItem> lineItems;


   public Invoice(Customers customers) {
       this.customers = customers;
       this.lineItems = new ArrayList<>();

   }

   public void  addLineItem(LineItem lineItem) {
       this.lineItems.add(lineItem);
   }

       public double getTotal(){
         return lineItems.stream().mapToDouble(LineItem::getTotal).sum();
       }

       @Override

       public String toString(){
           StringBuilder sb = new StringBuilder();
           sb.append("=============Invoice===========\n");
           sb.append("Customer Name: " + customers.getName() +"\n");
           sb.append("Customer Address: " + customers.getAddress() +"\n");
           sb.append("---------------------------------------\n");
           sb.append(String.format("%-20s %-10s %-10s %-10s\n", "Product", "QTY", "Price", "Total"));
           sb.append("---------------------------------------\n");

           for(LineItem lineItem : lineItems){
               sb.append(String.format("%-20s %-10d $%-9.2f $%-9.2f\n",
                       lineItem.getProduct().getName(),
                       lineItem.getQuantity(),
                       lineItem.getProduct().getUnitPrice(),
                       lineItem.getTotal()));
           }
           sb.append("-------------------------------------------------------\n");
           sb.append(String.format("Total Amount Due: $%.2f\n", getTotal()));
           sb.append("========================================================\n");
           return sb.toString();

       }
   }
