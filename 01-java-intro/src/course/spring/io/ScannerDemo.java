package course.spring.io;

import java.io.*;
import java.util.Scanner;

public class ScannerDemo {
    public String readDataFromFile(String filename) {
        Scanner s = null;
        StringBuilder sb = new StringBuilder();
        try {
            s = new Scanner(new BufferedReader(new FileReader(filename)));
            while (s.hasNext()) {
                sb.append(s.nextLine()).append("\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.printf("Catched in readData - '%s' does not exist: %s\n", filename, ex.getMessage());
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        String filename = "./src/course/spring/io/ScannerDemo.java";
        var demo = new ScannerDemo();
        var data = demo.readDataFromFile(filename);
        System.out.println(data);
    }
}