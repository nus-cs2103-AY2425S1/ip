import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Deadline extends Task{



    protected LocalDateTime by;

    protected Deadline(String description, LocalDateTime by) throws TheBotFatherException {
        super(description, "D");
        this.by = by;

    }

    protected static Deadline makeDeadline(StringTokenizer tokens) throws TheBotFatherException {
        try {
            StringBuilder description = new StringBuilder();
            StringBuilder by = new StringBuilder();

            String token = tokens.nextToken();
            while (!token.equals("/by")) {
                description.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            by.append(token).append(" ");
            while (tokens.hasMoreTokens()) {

                token = tokens.nextToken();
                by.append(token).append(" ");
            }

            return new Deadline(description.toString().trim(), LocalDateTime.parse(by.toString().trim(), Task.DATE_STRING_FORMATTER));
        } catch (NoSuchElementException | DateTimeException e) {
            throw new TheBotFatherException("If you have a deadline, type : \"deadline <description> /by DD-MM-YY HH:MM\"");
        }
    }

    @Override
    public String toString() {
        return super.toString()+ " (by: " + this.by.format(Task.DATE_STRING_FORMATTER_PRINT) + ")";
    }

    @Override
    protected String toFile() {
        return super.toFile() + " | " + this.by;
    }
}
