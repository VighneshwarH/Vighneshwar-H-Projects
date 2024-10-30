import java.util.Scanner;

class Bank_Account {
    private double balance;

    public Bank_Account(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposition successful");
        } else {
            System.out.println("Deposition failed");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance");
            return false;
        } else {
            System.out.println("Invalid amount entered. Please enter valid amount:$");
            return false;
        }
    }
}

public class ATM_Machine {
    private Bank_Account account;
    private Scanner sc;

    public ATM_Machine(Bank_Account account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Bank_Account account = new Bank_Account(1000.00);
        ATM_Machine atm = new ATM_Machine(account);
        atm.displayMenu();
    }

    public void displayMenu() {
        int option = 0;
        while (option != 4) {
            System.out.println("---------------ATM Machine----------------");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("Choose an option to continue:");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for visiting our ATM");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    public void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = sc.nextDouble();
        account.deposit(amount);
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = sc.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdraw successful. Please collect the cash.");
        }
    }
}
