package donna;

public class Parser {
    public ParsedCommand parse(String input) throws DonnaException {
        String[] inputWords = input.split(" ", 2);
        String command = inputWords[0];
        String arguments = inputWords.length > 1 ? inputWords[1] : "";

        switch (command) {
        case "bye":
            return new ParsedCommand("exit");
        case "list":
            return new ParsedCommand("list");
        case "mark":
            return new ParsedCommand("mark", arguments);
        case "unmark":
            return new ParsedCommand("unmark", arguments);
        case "delete":
            return new ParsedCommand("delete", arguments);
        case "todo":
        case "deadline":
        case "event":
            return new ParsedCommand("add", command, arguments);
        case "find":
            return new ParsedCommand("find", arguments);
        default:
            throw DonnaException.invalidTaskType(command);

        }

    }
}
