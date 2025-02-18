import java.util.Scanner;

class ATM {
    private static final String PIN = "1234"; 
    private static double balance = 3000; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.nextLine();

            
            if (!enteredPin.equals(PIN)) {
                throw new InvalidPinException("Error: Invalid PIN.");
            }

            
            System.out.print("Withdraw Amount: ");
            double withdrawAmount = scanner.nextDouble();

            
            if (withdrawAmount > balance) {
                throw new InsufficientBalanceException("Error: Insufficient balance. Current Balance: " + balance);
            }

           
            balance -= withdrawAmount;
            System.out.println("Withdrawal successful! Remaining Balance: " + balance);

        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            
            System.out.println("Current Balance: " + balance);
        }
    }
}


class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}


class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
