import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Medicine {
    // Private variables for encapsulation
    private String medicineName;
    private String batchNumber;
    private double pricePerUnit;
    private int quantityInStock;
    private LocalDate expiryDate;

    // Constructor
    public Medicine(String name, String batch, double price, int qty, String dateStr) {
        this.medicineName = name;
        this.batchNumber = batch;
        this.pricePerUnit = price;
        this.quantityInStock = qty;
        try {
            this.expiryDate = LocalDate.parse(dateStr); // Expecting format YYYY-MM-DD
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid Date Format. Defaulting to today.");
            this.expiryDate = LocalDate.now();
        }
    }

    // Getters and Setters
    public String getMedicineName() { return medicineName; }
    
    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }

    public double getPricePerUnit() { return pricePerUnit; }
    
    public LocalDate getExpiryDate() { return expiryDate; }

    // Display method to print details nicely
    @Override
    public String toString() {
        return String.format("Name: %-15s | Batch: %-10s | Price: $%-6.2f | Qty: %-4d | Exp: %s",
                medicineName, batchNumber, pricePerUnit, quantityInStock, expiryDate);
    }
}