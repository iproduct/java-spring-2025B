package course.spring.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

class IntHolder {
    public int i = 0;
}

public class FilesDemo01 {
    public static void main(String[] args) throws IOException {
        var source = Paths.get("./src/course/spring/io", "FilesDemo01.java")
                .toAbsolutePath()
                .normalize();
        System.out.printf("File '%s' exists: %b\n", source, Files.exists(source));
        var charset = Charset.forName("utf8");
        var lines = Files.lines(source, charset);
        IntHolder n = new IntHolder();
        lines.map(line -> ++n.i +  ": " + line).forEach(System.out::println);
        IntStream.range(1, 10).forEach(System.out::println);
    }

}
