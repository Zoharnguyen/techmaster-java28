package com.techmaster.java28.exception_handling_learning.assignment_2;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500);

        try {
            System.out.println("Số dư hiện tại: " + account.getBalance());
            account.deposit(200); // Tổng: 700
            account.withdraw(300); // Thành công
            account.withdraw(500); // Thất bại → ném exception
        } catch (InsufficientFundsException e) {
            System.out.println("Giao dịch thất bại: " + e.getMessage());
        }

        System.out.println("Số dư cuối cùng: " + account.getBalance());
    }
}

