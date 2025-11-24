import java.util.Scanner;

public class MediStockApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        // Pre-loading some dummy data for demonstration
        manager.addNewMedicine("Paracetamol", "B101", 5.00, 100, "2026-12-31");
        manager.addNewMedicine("CoughSyrup", "C992", 12.50, 20, "2023-01-01"); // Already expired example

        while (true) {
            System.out.println("\n=== MEDISTOCK PHARMACY SYSTEM ===");
            System.out.println("1. Add New Medicine");
            System.out.println("2. View Inventory");
            System.out.println("3. Sell Medicine");
            System.out.println("4. Check Expiry Dates");
            System.out.println("5. Exit");
            System.out.print("Select Option: ");

            // Basic input validation to prevent crashing
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear buffer
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Medicine Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter Batch ID: ");
                    String batch = scanner.nextLine();
                    
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    
                    System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                    String date = scanner.next();
                    
                    manager.addNewMedicine(name, batch, price, qty, date);
                    break;

                case 2:
                    manager.displayInventory();
                    break;

                case 3:
                    System.out.print("Enter Name of Medicine to Sell: ");
                    String sellName = scanner.nextLine();
                    System.out.print("Enter Quantity Required: ");
                    int sellQty = scanner.nextInt();
                    manager.sellMedicine(sellName, sellQty);
                    break;

                case 4:
                    manager.checkExpiryAlerts();
                    break;

                case 5:
                    System.out.println("Saving data and exiting... Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}