package knight2103.command;

public class Parser {
    /**
     * Interprets user input and creates a relevant command. Returns Optional<Command>.
     * If the input is not interpretable, an empty Optional is returned to
     * indicate that a relevant command cannot be created.
     *
     * @param fullCommand The input command given which must be of String type.
     * @return an Optional containing the Command if command is valid or
     * an empty optional if the Command is invalid.
     * @throws InvalidCommandException If the first word of user input does not match
     * with expected command verbs.
     * @throws ArrayIndexOutOfBoundsException If the user input does not match
     * the expected basic format which for certain commands, requires some
     * description apart from the first word (command verb).
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] commandArray = fullCommand.split(" ", 2);
        try {
            switch (commandArray[0]) {
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand(CommandVerb.TODO, commandArray[1]);
            case "deadline":
                return new AddCommand(CommandVerb.DEADLINE, commandArray[1]);
            case "event":
                return new AddCommand(CommandVerb.EVENT, commandArray[1]);
            case "mark":
                return new ModifyCommand(CommandVerb.MARK, commandArray[1]);
            case "unmark":
                return new ModifyCommand(CommandVerb.UNMARK, commandArray[1]);
            case "delete":
                return new ModifyCommand(CommandVerb.DELETE, commandArray[1]);
            case "find":
                return new FindCommand(CommandVerb.FIND, commandArray[1]);
            case "bye":
                return new ByeCommand();
            default:
                throw new InvalidCommandException("Invalid Instruction. Only valid Instructions are "
                        + "list, todo, deadline, event, mark, unmark, delete, find");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("There's an issue in the instruction format. "
                    + "Please check that it is <knight2103.command.CommandVerb> <description> format");
        }
    }
}
