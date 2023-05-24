import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GroceryShop {

    public static void main(String[] args) {
        // list of the grocery items
        GroceryItem[] groceryItems = {
                new GroceryItem("Lemon", 1.0, 0.4),
                new GroceryItem("Banana", 0.5, 0.5),
                new GroceryItem("Hotdo", 2.0, 0.1),
                new GroceryItem("Bread", 1.5, 0.3),
                new GroceryItem("Eggs", 2.5, 0.7),
                new GroceryItem("Chicken", 5.0, 0.2),
                new GroceryItem("Rice", 3.0, 0.4),
                new GroceryItem("Pasta", 2.0, 0.3),
                new GroceryItem("Cheese", 4.0, 0.1),
                new GroceryItem("Dairy", 0.75, 0.5)
        };

        // Display the grocery items table
        System.out.println("------ Grocery Items ------");
        System.out.println("No.\tName\t\tPrice\t\tDiscount");

        for (int i = 0; i < groceryItems.length; i++) {
            GroceryItem item = groceryItems[i];
            System.out.printf("%d.\t%s\t\t$%.2f\t\t%.0f%%\n", i + 1, item.getName(), item.getPrice(), item.getDiscount() * 100);
        }

        // Get user input
        Scanner input = new Scanner(System.in);
        List<GroceryItem> selectedItems = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        boolean addMoreProducts = true;

        while (addMoreProducts) {
            System.out.println("\nEnter the item number you want to purchase:");
            int itemNumber = getInputInt(input);

            if (itemNumber < 1 || itemNumber > groceryItems.length) {
                System.out.println("Invalid item number: " + itemNumber);
                continue;
            }

            System.out.print("Enter the quantity for item " + itemNumber + ": ");
            int quantity = getInputInt(input);

            if (quantity <= 0) {
                System.out.println("Invalid quantity: " + quantity);
                continue;
            }

            selectedItems.add(groceryItems[itemNumber - 1]);
            quantities.add(quantity);

            System.out.print("Do you want to add more products? (y/n): ");
            String addMore = input.next();

            if (addMore.equalsIgnoreCase("y")) {
                addMoreProducts = true;
            }else if (addMore.equalsIgnoreCase("n")) {
                addMoreProducts = false;
            }
        }

        // Calculate total amount
        double totalAmount = 0.0;

        System.out.println("\n------ Purchase Details ------");
        System.out.println("Item\t\tPrice\t\tDiscount\tQuantity\tTotal");

        for (int i = 0; i < selectedItems.size(); i++) {
            GroceryItem item = selectedItems.get(i);
            int quantity = quantities.get(i);

            double itemPrice = item.getPrice();
            double itemDiscount = item.getDiscount();
            double itemTotal = itemPrice * quantity * (1 - itemDiscount);

            totalAmount += itemTotal;

            System.out.printf("%s\t\t$%.2f\t\t%.0f%%\t\t%d\t\t$%.2f\n",
                    item.getName(), itemPrice, itemDiscount * 100, quantity, itemTotal);
        }

        System.out.println("-----------------------------------------");
        System.out.printf("Total Amount:\t\t\t\t\t\t$%.2f\n", totalAmount);

        input.close();
    }

    private static int getInputInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

}
