import java.util.stream.Stream;

public class FormatPrinter {
    public static void printIndent(String input, String prefix) {
        Stream<String> lines = input.lines();
        lines.forEach(line -> System.out.printf("%s%s%n", prefix, line));
    }
}
