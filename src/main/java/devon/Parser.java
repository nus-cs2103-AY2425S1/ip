package devon;

import java.util.Arrays;

public class Parser {
    public Parser() { }

    protected String extractCommand(String msg) {
        String[] parts = msg.split(" ");
        return parts[0];
    }

    protected String extractContent(String input) {
        String[] parts = input.split(" ");
        return parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "Error";
    }

    protected int extractTaskIndex(String input) throws NumberFormatException {
        return Integer.parseInt(extractContent(input));
    }

    protected String extractTodo(String input) {
        return extractContent(input).trim();
    }

    protected String[] extractDeadline(String input) throws DevonInvalidDeadlineException {
        String content = extractContent(input);
        if (!content.contains("/by")) {
            throw new DevonInvalidDeadlineException();
        }
        String[] parts = content.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{ description, by };
    }

    protected String[] extractEvent(String input) throws DevonInvalidEventException {
        String content = extractContent(input);
        if (!(content.contains("/from") && content.contains("/to"))) {
            throw new DevonInvalidEventException();
        }
        String[] partsFrom = content.split("/from", 2);
        String[] partsTo = partsFrom[1].split("/to", 2);
        String description = partsFrom[0].trim();
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();
        return new String[]{ description, from, to };
    }
}
