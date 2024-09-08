public class Parser {
    public static String[] parse(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    public static String parseAction(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static String parseDetails(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public static String[] parseDeadlineDetails(String details) {
        return details.split(" /by ");
    }

    public static String[] parseEventDetails(String details) {
        String[] parts = details.split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        return new String[]{parts[0], timeParts[0], timeParts[1]};
    }
}
