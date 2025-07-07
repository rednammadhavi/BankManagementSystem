import java.util.*;

class Account {
    private static int idCounter = 1000;
    private final int accountNumber;
    private String holderName;
    private double balance;

    public Account(String holderName) {
        this.accountNumber = idCounter++;
        this.holderName = holderName;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void displayDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name   : " + holderName);
        System.out.println("Balance       : ₹" + balance);
    }
}

public class BankManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> checkBalance();
                case 5 -> {
                    System.out.println("Thank you for using our banking system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Account newAccount = new Account(name);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created successfully!");
        System.out.println("Your Account Number is: " + newAccount.getAccountNumber());
    }

    private static void depositMoney() {
        Account account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ₹");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void withdrawMoney() {
        Account account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        Account account = findAccount();
        if (account != null) {
            account.displayDetails();
        }
    }

    private static Account findAccount() {
        System.out.print("Enter your Account Number: ");
        int accNo = scanner.nextInt();
        Account account = accounts.get(accNo);
        if (account == null) {
            System.out.println("Account not found.");
        }
        return account;
    }
}
