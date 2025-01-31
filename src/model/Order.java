package model;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Order extends Cart {
    private List<Product> products;
    private int orderID;
    private Date orderDate;

    public Order(List<Product> products) {
         this.products = products;
         this.orderID = orderID;
         this.orderDate = new Date();
    }

    public void displayOrder() {
        System.out.println("Order Details: ");
        for (Product product : products) {
            System.out.println(product.getId()+" - "+product.getName()+" - "+product.getPrice());
        }
        System.out.println("Date: "+ orderDate);
    }
}
