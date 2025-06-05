package com.techmaster.java28.exception_handling_learning.assignment_1;
import java.io.*;
import java.util.Scanner;


public class AssignmentReadFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = null;

        try {
            System.out.print("Nhập đường dẫn file: ");
            String filePath = scanner.nextLine();

            throw new IOException("Lỗi giả lập khi đọc file!"); // Thêm dòng này để giả lập lỗi

            // Gợi ý lỗi NullPointerException:
            // filePath = null; // Bỏ comment dòng này để test lỗi

            // FileReader fr = new FileReader(filePath);
            // reader = new BufferedReader(fr);

            // String line;
            // while ((line = reader.readLine()) != null) {
            //     System.out.println(line);
            // }

        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Lỗi: Biến bị null.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("Đã đóng file.");
                }
            } catch (IOException e) {
                System.out.println("Lỗi khi đóng file.");
            }
        }
    }
}
