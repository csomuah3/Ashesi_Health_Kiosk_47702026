import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // TASK 0: Welcome message
        System.out.println("=== Welcome to Ashesi Health Kiosk ===\n");
        
        // TASK 1: Service Router (switch statement)
        System.out.print("Enter service code (P/L/T/C): ");
        char serviceCode = input.next().charAt(0);
        serviceCode = Character.toUpperCase(serviceCode); // Make case-insensitive
        
        String serviceName = "";
        
        switch (serviceCode) {
            case 'P':
                serviceName = "PHARMACY";
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
                serviceName = "LAB";
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
                serviceName = "TRIAGE";
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
                serviceName = "COUNSELING";
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
                input.close();
                return; // Exit if invalid
        }
        
        // TASK 2: Mini Health Metric (only for Triage, but we'll make it available for all)
        System.out.println("\nHealth Metric Options:");
        System.out.println("1 - BMI Calculator");
        System.out.println("2 - Dosage Round-up");
        System.out.println("3 - Simple Trig Helper");
        System.out.print("Enter metric choice (1/2/3): ");
        int metricChoice = input.nextInt();
        
        double metricValue = 0; // Will store the final metric for Task 4
        String metricDisplay = ""; // For display in summary
        
        if (metricChoice == 1) {
            // Option A: BMI Calculator
            System.out.print("Enter weight(kg): ");
            double weight = input.nextDouble();
            System.out.print("Enter height(m): ");
            double height = input.nextDouble();
            
            double bmi = weight / Math.pow(height, 2);
            double bmiRounded = Math.round(bmi * 10) / 10.0;
            
            String category;
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                category = "Normal";
            } else if (bmi >= 25.0 && bmi <= 29.9) {
                category = "Overweight";
            } else {
                category = "Obese";
            }
            
            System.out.println("BMI: " + bmiRounded + " Category: " + category);
            metricValue = Math.round(bmi); // Rounded to nearest int for Task 4
            metricDisplay = "BMI=" + bmiRounded;
            
        } else if (metricChoice == 2) {
            // Option B: Dosage Round-up
            System.out.print("Enter required dosage (mg): ");
            double dosage = input.nextDouble();
            
            int tablets = (int) Math.ceil(dosage / 250.0);
            
            System.out.println("Tablets needed: " + tablets);
            metricValue = tablets;
            metricDisplay = "Tablets=" + tablets;
            
        } else if (metricChoice == 3) {
            // Option C: Simple Trig Helper
            System.out.print("Enter angle (degrees): ");
            double degrees = input.nextDouble();
            
            double radians = Math.toRadians(degrees);
            double sinValue = Math.round(Math.sin(radians) * 1000) / 1000.0;
            double cosValue = Math.round(Math.cos(radians) * 1000) / 1000.0;
            
            System.out.println("sin(" + degrees + "°) = " + sinValue);
            System.out.println("cos(" + degrees + "°) = " + cosValue);
            metricValue = Math.round(sinValue * 100);
            metricDisplay = "Sin*100=" + (int)metricValue;
        }
        
        // TASK 3: ID Sanity Check
        System.out.println("\n--- Generating Random ID ---");
        
        // Generate random uppercase letter (A-Z)
        char randomLetter = (char) ('A' + (int)(Math.random() * 26));
        
        // Generate 4 random digits between 3 and 9 (inclusive)
        int digit1 = 3 + (int)(Math.random() * 7);
        int digit2 = 3 + (int)(Math.random() * 7);
        int digit3 = 3 + (int)(Math.random() * 7);
        int digit4 = 3 + (int)(Math.random() * 7);
        
        // Concatenate to form ID
        String shortID = "" + randomLetter + digit1 + digit2 + digit3 + digit4;
        System.out.println("Generated ID: " + shortID);
        
        // Validate the ID
        boolean isValid = true;
        String errorMessage = "";
        
        if (shortID.length() != 5) {
            isValid = false;
            errorMessage = "Invalid length";
        } else if (!Character.isLetter(shortID.charAt(0))) {
            isValid = false;
            errorMessage = "Invalid: first char must be a letter";
        } else if (!Character.isDigit(shortID.charAt(1)) || 
                   !Character.isDigit(shortID.charAt(2)) || 
                   !Character.isDigit(shortID.charAt(3)) || 
                   !Character.isDigit(shortID.charAt(4))) {
            isValid = false;
            errorMessage = "Invalid: last 4 must be digits";
        }
        
        if (isValid) {
            System.out.println("ID OK");
        } else {
            System.out.println(errorMessage);
        }
        
        // TASK 4: "Secure" Display Code
        System.out.print("\nEnter your first name: ");
        String firstName = input.next();
        
        // Get first character and uppercase it
        char baseCode = Character.toUpperCase(firstName.charAt(0));
        System.out.println("Base code = " + baseCode);
        
        // Shift letter forward by 2 (with wrapping)
        char shiftedLetter = (char)('A' + (baseCode - 'A' + 2) % 26);
        System.out.println("Shifted letter of base code = " + shiftedLetter);
        
        // Extract last two characters from ID
        String lastTwo = shortID.substring(3, 5);
        System.out.println("Last two characters for ID (task 3): " + lastTwo);
        
        // Build display code
        String displayCode = shiftedLetter + lastTwo + "-" + (int)metricValue;
        System.out.println("Display Code: " + displayCode);
        
        // TASK 5: Service Summary
        System.out.println("\n=== SERVICE SUMMARY ===");
        String summary = "Summary: " + serviceName + " | ID=" + shortID + " | ";
        
        // Add metric if Triage
        if (serviceCode == 'T') {
            summary += metricDisplay + " | ";
        }
        
        summary += "Code=" + displayCode;
        System.out.println(summary);
        
        input.close();
    }
}