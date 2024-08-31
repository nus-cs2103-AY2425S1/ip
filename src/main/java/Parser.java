public class Parser {

    enum Command {
        BYE, LIST, MARK, UNMARK, DEADLINE, EVENT, TODO, DELETE, CLEAR, INVALID
    }

    public static Command getCommand(String input) {
        String commandSymbol = input.split(" ", 2)[0].toUpperCase();
        switch (commandSymbol) {
            case "LIST":
                return Command.LIST;
            case "CLEAR":
                return Command.CLEAR;
            case "MARK":
                return Command.MARK;
            case "UNMARK":
                return Command.UNMARK;
            case "DEADLINE":
                return Command.DEADLINE;
            case "EVENT":
                return Command.EVENT;
            case "TODO":
                return Command.TODO;
            case "DELETE":
                return Command.DELETE;
            case "BYE":
                return Command.BYE;
            default:
                return Command.INVALID;
        }
    }

    public static String getRawCommand(String input) {
        return input.split(" ", 2)[0];
    }

    public static String[] getTodoPayload(String input) {
        String[] todoPayload = input.split(" ", 2);
        if (todoPayload.length == 1) {
            return null;
        }
        String todoDescription = input.split(" ", 2)[1];
        if (todoDescription.length() == 0) {
            return null;
        }
        return new String[] { todoDescription };
    }

    public static String[] getDeadlinePayload(String input) {
        String[] deadlinePayload = input.split(" ", 2);
        if (deadlinePayload.length == 1) {
            return null;
        }
        String[] splitInput = deadlinePayload[1].split(" /by ");
        if (splitInput.length != 2) {
            return null;
        }
        return new String[] { splitInput[0], splitInput[1] };
    }

    public static String[] getEventPayload(String input) {
        String[] eventPayload = input.split(" ", 2);
        if (eventPayload.length == 1) {
            return null;
        }
        String[] splitInput = eventPayload[1].split(" /from ");
        if (splitInput.length != 2) {
            return null;
        }
        String[] finalSplit = splitInput[1].split(" /to ");
        if (finalSplit.length != 2) {
            return null;
        }
        return new String[] { splitInput[0], finalSplit[0], finalSplit[1] };
    }

    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1].strip()) - 1;
    }

    public static String[] getCommandStrings() {
        return Command.values().toString().replaceAll("^.|.$", "").split(", ");
    }

}
