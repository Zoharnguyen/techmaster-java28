package com.techmaster.java28.logs_banking;

import com.techmaster.java28.logs_banking.service.BankingService;

public class Main {
    public static void main(String[] args) {
        BankingService bankingService = new BankingService();

        try {
            // Create accounts
            bankingService.createAccount("ACC001", "John Doe", 1000.0);
            bankingService.createAccount("ACC002", "Jane Smith", 500.0);

            // Perform some transactions
            bankingService.deposit("ACC001", 500.0);
            bankingService.withdraw("ACC001", 200.0);
            bankingService.transfer("ACC001", "ACC002", 300.0);

            // Check balances
            System.out.println("ACC001 Balance: " + bankingService.getBalance("ACC001"));
            System.out.println("ACC002 Balance: " + bankingService.getBalance("ACC002"));

            // Try to withdraw more than available
            bankingService.withdraw("ACC001", 2000.0);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
} 