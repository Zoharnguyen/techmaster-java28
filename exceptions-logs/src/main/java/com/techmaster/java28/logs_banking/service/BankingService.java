package com.techmaster.java28.logs_banking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.techmaster.java28.logs_banking.exception.InsufficientFundsException;
import com.techmaster.java28.logs_banking.model.Account;

import java.util.HashMap;
import java.util.Map;

public class BankingService {
    private final Map<String, Account> accounts;

    private static final Logger LOGGER = LogManager.getLogger(BankingService.class);

    public BankingService() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        LOGGER.info("Creating account");

        LOGGER.debug("Creating account {}", accountNumber);

        if (accounts.containsKey(accountNumber)) {
            LOGGER.error("Account number already exists");

            throw new IllegalArgumentException("Account number already exists");
        }
        Account account = new Account(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);

        LOGGER.info("Creating account successfully");
    }

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
    }

    public void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        Account account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Current balance: %.2f, Required: %.2f",
                    account.getBalance(), amount));
        }
        account.setBalance(account.getBalance() - amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
    }

    public double getBalance(String accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    private Account getAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }
} 