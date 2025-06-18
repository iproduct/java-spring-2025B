package course.spring.io;

import course.spring.exceptions.CustomDatabaseException;

import java.io.*;
import java.util.Scanner;

public class ScannerDemo {
    public String readDataFromFile(String filename) throws CustomDatabaseException {
        StringBuilder sb = new StringBuilder();
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (s.hasNext()) {
                sb.append(s.nextLine()).append("\n");
            }
        }
        catch (FileNotFoundException ex) {
            System.out.printf("Try to process in readData - '%s' does not exist: %s\n", filename, ex.getMessage());
            throw new CustomDatabaseException("Database file missing", ex);
        }
//        } finally {
//            if (s != null) {
//                s.close();
//            }
//        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String filename = "./src/course/spring/io/ScannerDemo2.java";
        var demo = new ScannerDemo();
        String data = null;
        try {
            data = demo.readDataFromFile(filename);
        } catch (CustomDatabaseException e) {
            System.out.printf("Catched in main: %s <- %s\n", e.getMessage(), e.getCause().getMessage());
//            e.printStackTrace();
        }
        System.out.println(data);
        System.out.println("Demo complete.");
    }
}