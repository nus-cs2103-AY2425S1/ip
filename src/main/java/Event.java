import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    protected Event(String description, LocalDateTime from, LocalDateTime to) throws TheBotFatherException {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    protected static Event makeEvent(StringTokenizer tokens) throws TheBotFatherException {
        try {
            StringBuilder description = new StringBuilder();
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            String token = tokens.nextToken();

            while (!token.equals("/from")) {
                description.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            while (!token.equals("/to")) {
                from.append(token).append(" ");
                token = tokens.nextToken();
            }

            token = tokens.nextToken();
            to.append(token).append(" ");
            while (tokens.hasMoreTokens()) {
                token = tokens.nextToken();
                to.append(token).append(" ");
            }

            if (Objects.equals(from.toString(), "")) {
                throw new TheBotFatherException("Kid, look at what you have  written.. is that a valid event? *sigh*\n" +
                        "\tIf you have an event, type \"event <description> /from DD-MM-YY HH:MM /to DD-MM-YY HH:MM\"");
            }

            return new Event(description.toString().trim(),
                    LocalDateTime.parse(from.toString().trim(), Task.DATE_STRING_FORMATTER),
                    LocalDateTime.parse(to.toString().trim(), Task.DATE_STRING_FORMATTER));
        } catch (NoSuchElementException | DateTimeException e) {
            throw new TheBotFatherException("Kid, look at what you have  written... is that a valid event?? *sigh*\n" +
                    "\tIf you have an event, type \"event <description> /from DD-MM-YY HH:MM /to DD-MM-YY HH:MM\"");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " (from: " +
                this.from.format(Task.DATE_STRING_FORMATTER_PRINT) +
                " to: " +
                this.to.format(Task.DATE_STRING_FORMATTER_PRINT) +
                ")";
    }

    @Override
    protected String toFile() {
        return super.toFile() + " | " + this.from + " | " + this.to;
    }
}
