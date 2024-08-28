import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    protected static ArrayList<String> parse(String command) {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] parse_1 = command.split(" ", 2);
        parsedResult.add(parse_1[0]);
        if (parse_1.length > 1) {
            String[] parse_2 = parse_1[1].split(" /", 3);
            parsedResult.addAll(Arrays.stream(parse_2).toList());
        }
        return parsedResult;
    }

    protected static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
