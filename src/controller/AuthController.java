package controller;

import exception.EmptyCartException;
import exception.InvalidCredentialsException;
import exception.ProductNotFoundException;
import model.*;
import view.ConsoleView;

import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AuthController {
    private User user;
    SoundPlayer soundPlayer;
    public AuthController(User user) {
        this.user = user;
    }

    public boolean login(String username, String password) throws InvalidCredentialsException {
        if(user.authenticate(username, password)) {
            System.out.println("Login successful!");
            return true;
        }else{
            throw new InvalidCredentialsException("Invalid creadentails");
        }
    }
    public void start (){
        Scanner scanner = new Scanner(System.in);
        User user = new User("admin", "12345678");
        AuthController authController = new AuthController(user);
        Cart cart = new Cart();
        ConsoleView view = new ConsoleView();
        List<Product> products = Arrays.asList(
                new Product(1, "Burger", 5.99),
                new Product(2, "Pizza", 8.99),
                new Product(3, "Pasta", 6.49),
                new Product(4, "Coffee", 2.99),
                new Product(5, "CocaCola", 0.99),
                new Product(6, "Milk Tea", 3.49)
        );


        boolean loggedIn = false;
        while (!loggedIn) {
            try {
                System.out.print("Enter username: ");
                String username = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();
                loggedIn = authController.login(username, password);
                soundPlayer.playSound("src/resources/success-1-6297.mp3");
            } catch (InvalidCredentialsException e) {
                System.out.println(e.getMessage());
                soundPlayer.playSound("src/resources/invalid-selection-39351.mp3");
            }
        }

        int choice;
        do {
            System.out.println("\n1. List Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Place Order");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        view.displayProducts(products);
                        break;
                    case 2:
                        try {
                            System.out.print("Enter Product ID to add: ");
                            int addId = scanner.nextInt();

                            Product product = products.stream()
                                    .filter(p -> p.getId() == addId)
                                    .findFirst()
                                    .orElseThrow(() -> new ProductNotFoundException("Invalid Product ID"));

                            cart.addProduct(product);
                            System.out.println("Product added!");

                        } catch (ProductNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 3:
                        view.displayCart(cart.getProducts());
                        break;
                    case 4:
                        try{
                            System.out.print("Enter Product ID to remove: ");
                            int removeId = scanner.nextInt();
                            if(cart.getProducts().stream().noneMatch(p -> p.getId() == removeId)){
                                throw new ProductNotFoundException("Product ID not found in the cart!");
                            }
                            cart.removeProduct(removeId);
                            System.out.println("Product removed!");
                        }catch(ProductNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        try{
                            System.out.println("Order Placed!");
                            if (cart.getProducts().isEmpty()) {
                                throw new EmptyCartException("Your cart is empty! Add products before placing an order.");
                            }
                            Order order = new Order(cart.getProducts());
                            order.displayOrder();
                            cart = new Cart();
                        }catch (EmptyCartException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option!");
                }

            } catch (InputMismatchException exception) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                choice = 0;
            }
        } while (choice != 6);
    }
}
