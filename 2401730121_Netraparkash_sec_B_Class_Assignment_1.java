import java.util.Scanner;

class Account {
    // attributes
    private long accNumber;
    private String accHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // constructor
    public Account(long accNumber, String accHolderName, double balance, String email, String phoneNumber) {
        this.accNumber = accNumber;
        this.accHolderName = accHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    // getter for account number
    public long getAccNumber() {
        return accNumber;
    }
    
    // deposit money into account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully");
        } else {
            System.out.println("Enter a valid amount.");
        }
    }
    
    // withdraw money from account
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // display account details
    public void displayDetails() {
        System.out.println("--- Account Details --- ");
        System.out.println("Account Number: " + accNumber);
        System.out.println("Account Holder Name: " + accHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("E-mail: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

    // update email and phone
    public void updateContactDetails(String email, String phone) {
        this.email = email;
        this.phoneNumber = phone;
        System.out.println("Details updated successfully.");
    }
}

// --- User Interface Class ---
class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner input;
    private long nextAccNumber;

    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        input = new Scanner(System.in);
        nextAccNumber = 1001;
    }

    // method to create a new account
    public void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Account limit reached. Cannot create more accounts.");
            return;
        }
        
        System.out.println("Enter Account Holder Name:");
        String name = input.nextLine();
        
        System.out.print("Enter Initial Deposit Amount:");
        double initialDeposit = input.nextDouble();
        input.nextLine();
        
        System.out.print("Enter Email:");
        String email = input.nextLine();
        
        System.out.print("Enter Phone Number:");
        String phoneNumber = input.nextLine();
        
        Account newAccount = new Account(nextAccNumber, name, initialDeposit, email, phoneNumber);
        accounts[accountCount] = newAccount;
        accountCount++;
        
        System.out.println("Account created successfully. Your Account Number is: " + nextAccNumber);
        nextAccNumber++;
    }

    // helper method to find account by account number
    private Account findAccount(long accNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccNumber() == accNumber) {
                return accounts[i];
            }
        }
        return null; // not found
    }

    // method for deposit operation
    public void deposit() {
        System.out.print("Enter Account Number: ");
        long accNumber = input.nextLong();
        input.nextLine();
        
        Account account = findAccount(accNumber);
        if (account != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = input.nextDouble();
            input.nextLine();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // method for withdrawal operation
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        input.nextLine();
        
        System.out.print("Enter amount to withdraw: ");
        double amount = input.nextDouble();
        input.nextLine();
        
        Account account = findAccount(accNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // method to show account details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        input.nextLine();
        
        Account account = findAccount(accNumber);
        if (account != null) {
            account.displayDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // method to update contact details
    public void updateContact() {
        System.out.print("Enter account number: ");
        long accNumber = input.nextLong();
        input.nextLine();
        
        Account account = findAccount(accNumber);
        if (account != null) {
            System.out.print("Enter new email: ");
            String email = input.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = input.nextLine();
            account.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // main menu method
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100);
        ui.mainMenu();
    }
}