public enum Command {
    TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, RAWR, BYE, HELP;

    private static Command lastCommand;

    public static Command inputToCommand(String input) throws InvalidCommandException {
        return switch (input) {
            case "help" -> HELP;
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            case "list" -> LIST;
            case "mark" -> MARK;
            case "unmark" -> UNMARK;
            case "delete" -> DELETE;
            case "rawr" -> RAWR;
            case "bye" -> BYE;
            default -> throw new InvalidCommandException();
        };
    }

    public static String usageMessage(Command command) {
        return switch (command) {
            case HELP -> "help";
            case TODO -> "todo <description>";
            case DEADLINE -> "deadline <description> /by <date: DD-MM-YY> <time: HHMM>";
            case EVENT -> "event <description> /from <date: DD-MM-YY> <time: HHMM> /to <date: DD-MM-YY> <time: HHMM>";
            case LIST -> "list";
            case MARK -> "mark <task number>";
            case UNMARK -> "unmark <task number>";
            case DELETE -> "delete <task number>";
            case RAWR -> "rawr";
            case BYE -> "bye";
        };
    }

    public static String getCommandList() {
        String spaces = "  ";
        String output = "";
        output += "Here are a list of valid commands and how to use them:\n";
        output += "\n";

        output += spaces + "Adding tasks:\n";
        output += spaces + "* " + usageMessage(TODO) + "\n";
        output += spaces + "* " + usageMessage(DEADLINE) + "\n";
        output += spaces + "* " + usageMessage(EVENT) + "\n";
        output += "\n";

        output += spaces + "List all tasks:\n";
        output += spaces + "* " + usageMessage(LIST) + "\n";
        output += "\n";

        output += spaces + "Mark/Unmark/Delete tasks:\n";
        output += spaces + "* " + usageMessage(MARK) + "\n";
        output += spaces + "* " + usageMessage(UNMARK) + "\n";
        output += spaces + "* " + usageMessage(DELETE) + "\n";
        output += "\n";

        output += spaces + "rawr:\n";
        output += spaces + "* " + usageMessage(RAWR) + "\n";
        output += "\n";

        output += spaces + "Exit:\n";
        output += spaces + "* " + usageMessage(BYE) + "\n";

        return output;
    }
}
