package com.techmaster.java28.logs_demo;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppController {

    private static final Logger LOGGER = LogManager.getLogger(AppController.class);

    public static void main(String[] args) {
        String accountNumber = "1111";
        LOGGER.debug("Start process main method {}", accountNumber); // 1000 / 1day
//        System.out.println("Proccessing sys out");
        divide(4, 2);
        divide(4, 0);
        LOGGER.info("Process success"); // 100 success / day
    }

    public static void divide(int number1, int number2) {
        try {
            int number = number1 / number2;
            LOGGER.info("Info: {} / {} = {}", number1, number2, number);
        } catch (ArithmeticException e) {
            String message = MessageFormat.format("Error: Cannot divide two number {0}/{1}", number1, number2);
            LOGGER.error(message, e);
        }
    }
} 