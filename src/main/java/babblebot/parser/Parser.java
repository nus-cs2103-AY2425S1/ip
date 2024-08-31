package babblebot.parser;

public class Parser {

    public static String parseCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    public static String parseTodoContent(String userInput) {
        return userInput.split("todo ")[1];
    }

    public static String[] parseDeadlineContent(String userInput) {
        String[] result = new String[2];
        result[0] = userInput.split("deadline ")[1].split(" /by")[0]; // content
        result[1] = userInput.split("/by ")[1]; // by
        return result;
    }

    public static String[] parseEventContent(String userInput) {
        String[] result = new String[3];
        result[0] = userInput.split("event ")[1].split(" /from")[0]; // eventContent
        String splitDates = userInput.split("/from ")[1];
        String[] parsedDates = splitDates.split(" /to ");
        result[1] = parsedDates[0]; // from
        result[2] = parsedDates[1]; // to
        return result;
    }

    public static int parseIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }
}
