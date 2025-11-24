import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryManager {
    
    // List to store all medicine objects
    private ArrayList<Medicine> medicines = new ArrayList<>();

    // 1. ADD MEDICINE
    public void addNewMedicine(String name, String batch, double price, int qty, String date) {
        Medicine newMed = new Medicine(name, batch, price, qty, date);
        medicines.add(newMed);
        System.out.println(">> Success: " + name + " added to inventory.");
        saveToFile(); // Auto-save after adding
    }

    // 2. SHOW ALL MEDICINES
    public void displayInventory() {
        if (medicines.isEmpty()) {
            System.out.println(">> Inventory is empty.");
        } else {
            System.out.println("\n--- Current Inventory ---");
            for (Medicine m : medicines) {
                System.out.println(m.toString());
            }
            System.out.println("-------------------------");
        }
    }

    // 3. SELL MEDICINE (Billing)
    public void sellMedicine(String name, int qtyWanted) {
        boolean found = false;
        
        for (Medicine m : medicines) {
            // Case insensitive search
            if (m.getMedicineName().equalsIgnoreCase(name)) {
                found = true;
                
                if (m.getQuantityInStock() >= qtyWanted) {
                    // Calculate Cost
                    double totalCost = qtyWanted * m.getPricePerUnit();
                    
                    // Update Stock
                    m.setQuantityInStock(m.getQuantityInStock() - qtyWanted);
                    
                    // Print Bill
                    System.out.println("\n** BILL RECEIPT **");
                    System.out.println("Item: " + m.getMedicineName());
                    System.out.println("Quantity: " + qtyWanted);
                    System.out.println("Total Cost: $" + totalCost);
                    System.out.println("********\n");
                } else {
                    System.out.println(">> Error: Insufficient Stock. Available: " + m.getQuantityInStock());
                }
                break; // Stop searching once found
            }
        }
        
        if (!found) {
            System.out.println(">> Error: Medicine not found in database.");
        }
    }

    // 4. CHECK EXPIRY (The Main Logic)
    public void checkExpiryAlerts() {
        LocalDate today = LocalDate.now();
        System.out.println("\n--- Expiry Safety Check ---");
        boolean alertFound = false;

        for (Medicine m : medicines) {
            long daysUntilExpiry = ChronoUnit.DAYS.between(today, m.getExpiryDate());

            if (daysUntilExpiry < 0) {
                System.out.println("CRITICAL: " + m.getMedicineName() + " is EXPIRED! (Discard Immediately)");
                alertFound = true;
            } else if (daysUntilExpiry <= 30) {
                System.out.println("WARNING: " + m.getMedicineName() + " expires in " + daysUntilExpiry + " days.");
                alertFound = true;
            }
        }

        if (!alertFound) {
            System.out.println(">> No immediate expiry risks found.");
        }
    }

    // 5. FILE I/O (Saves data to inventory.txt)
    // This fulfills the "Structured Development" and "Tools" requirement
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("inventory_data.txt")) {
            for (Medicine m : medicines) {
                writer.write(m.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save data to file.");
        }
    }
}