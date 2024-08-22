public class Parser {
    public static Query parse(String raw) throws CloudException {
        String[] split = raw.split(" ", 2);
        String command = split[0].strip();

        // Checking for errors in command
        // TODO: handle errors for remaining commands
        switch (command) {
            case "todo":
                if (split.length < 2) {
                    throw new InputException("The description of a todo cannot be empty");
                }
                break;
            case "deadline":
            case "event":
            case "list":
            case "bye":
            case "mark":
            case "unmark":
                break;
            default:
                throw new UnrecognisedException("Unrecognised command, please try again.");
        }
        if (split.length > 1) {
            String details = split[1];
            return new Query(command, details);
        }
        // for standalone commands (list, etc)
        return new Query(command);
    }
}
