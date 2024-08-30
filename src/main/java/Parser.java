public class Parser {
    //parse the user commands

    public static Command parse(String rawInput) {
        if (rawInput.isEmpty()) {
            throw new JoeException("\tOOPS! You did not enter anything");
        }
        String[] inputArr = rawInput.split(" ");
        String strCommand = inputArr[0].toUpperCase();
        Commands command = null;
        try {
            command = Commands.valueOf(strCommand);
        } catch (IllegalArgumentException e) {
            throw new JoeException("Unknown command");
        }
        switch (command) {
            case BYE -> {
                return new ExitCommand();
            }
            case LIST -> {
                return new ListCommand();
            }
            case TODO, DEADLINE, EVENT -> {
                // TODO
                return new AddCommand(command, inputArr);
            }
            case DELETE -> {
                return new DeleteCommand(inputArr);
            }
            case MARK,UNMARK -> {
                return new ToggleCommand(command, inputArr);
            }
        }
        return new UnknownCommand();
    }
}
