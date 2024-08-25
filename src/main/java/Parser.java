public class Parser {
    public String parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput[0];
    }

    public String parseDescription(String input) {
        String[] splitInput = input.split(" ", 2);
        return (splitInput.length > 1) ? splitInput[1] : "";
    }

    public int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    public String[] parseDeadline(String input) {
        return input.split(" /by ", 2);
    }

    public String[] parseEvent(String input) {
        return input.split(" /from | /to ");
    }
}
