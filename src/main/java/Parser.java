import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, INPUT_DATE_FORMAT);
    }

    public Matcher matchCommand(String userInput, CommandType commandType) {
        return commandType.matcher(userInput);
    }

    public boolean isValidTaskDescription(String description) {
        return !description.trim().isEmpty();
    }

}

