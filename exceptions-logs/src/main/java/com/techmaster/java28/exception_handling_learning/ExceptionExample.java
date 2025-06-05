package com.techmaster.java28.exception_handling_learning;
public class ExceptionExample {

    public static void main(String[] args) {
        System.out.println("Program started.");
        try {
            methodA();
        } catch (RuntimeException e) {
            System.out.println("Caught exception in main: " + e.getMessage());
        }
        System.out.println("Program finished.");
    }

    // This method calls methodB, which might throw an exception.
    public static void methodA() {
        System.out.println("Entering methodA.");
        try {
            methodB();
        } catch (RuntimeException e) {
            // This block handles the exception if methodB throws one.
            // "Runtime System sẽ tìm cách xử lý ngoại lệ phù hợp được sử dụng tại method ấy."
            // (Runtime System will try to find a suitable exception handler used in that method.)
            System.out.println("Caught exception in methodA: " + e.getMessage());
            // If methodA doesn't re-throw the exception, it's considered handled here.
            // If methodA didn't have a catch block, the exception would propagate up to main.
            // "Nếu không có thì JVM tiếp tục tìm xử lý ngoại lệ phù hợp ở các method trên (là method gọi lớp hiện tại)."
            // (If not, the JVM continues to look for a suitable exception handler in the methods above (the method calling the current class).)
        }
        System.out.println("Exiting methodA."); // This line will be reached if methodA catches the exception.
    }

    // This method simulates an error and throws an exception.
    public static void methodB() {
        System.out.println("Entering methodB.");
        // "Khi một lỗi xảy ra trên một method, method đó sẽ tạo ra một object và đưa nó vào Runtime System."
        // (When an error occurs in a method, that method will create an object and pass it to the Runtime System.)
        // "Object đó được gọi là Exception Object, nó chứa tất cả các thông tin về lỗi và trạng thái của chương trình khi xảy ra lỗi."
        // (That object is called an Exception Object, it contains all the information about the error and the state of the program when the error occurred.)
        throw new RuntimeException("Something went wrong in methodB!");
        // System.out.println("Exiting methodB."); // This line will NOT be reached because of the exception.
    }
} 