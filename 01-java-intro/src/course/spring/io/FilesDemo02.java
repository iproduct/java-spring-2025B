package course.spring.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class FilesDemo02 {
    public static void main(String[] args) throws IOException {
        var source = Paths.get("./src/course/spring/io", "FilesDemo01.java")
                .toAbsolutePath()
                .normalize();
        System.out.printf("File '%s' exists: %b\n", source, Files.exists(source));
        var charset = Charset.forName("utf8");
        try(BufferedReader in = Files.newBufferedReader(source,charset)) {
            String line = null;
            int n = 0;
            while((line = in.readLine()) != null) {
                System.out.println(++n +  ": " + line);
            }
        }
    }

}
