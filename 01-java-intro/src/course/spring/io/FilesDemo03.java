package course.spring.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FilesDemo03 {
    public static void main(String[] args) throws IOException {
        var source = Paths.get("./src/course/spring/io", "FilesDemo01.java")
                .toAbsolutePath()
                .normalize();
        var rootDir = Paths.get(".").toAbsolutePath().normalize();
        var dataDir =  rootDir.resolve(Paths.get("data"));
        var dest = dataDir.resolve("numbered_lines.txt");
        if(!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        System.out.printf("File '%s' exists: %b\n", source, Files.exists(source));
        System.out.printf("File '%s' exists: %b\n", dest, Files.exists(dest));
        var all = Charset.availableCharsets();
        all.forEach((name, charset) ->
                System.out.printf("%s -> %s\n", name, charset.aliases().stream().collect(Collectors.joining(", "))));
        System.out.println("=================================================");
        var charset = Charset.forName("utf8");
        try(
                BufferedReader in = Files.newBufferedReader(source,charset);
                PrintWriter out = new PrintWriter(Files.newBufferedWriter(dest,charset));
        ) {
            String line = null;
            int n = 0;
            while((line = in.readLine()) != null) {
                out.println(++n +  ": " + line);
            }
        }

        // Print result file
        Files.lines(dest).forEach(System.out::println);
    }

}
