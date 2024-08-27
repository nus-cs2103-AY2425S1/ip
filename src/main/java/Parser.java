public class Parser {

    public static Command getCommand(String input) throws TalkieUnknownCommandException {
        try {
            String inputCommand = input.split(" ", 2)[0];
            CommandType command = CommandType.valueOf(inputCommand.toUpperCase());

            switch (command) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand(input);
                case DELETE:
                    return new DeleteCommand(input);
                case MARK:
                    return new MarkCommand(input);
                case UNMARK:
                    return new UnMarkCommand(input);
                case TODO:
                    return new ToDoCommand(input);
                case DEADLINE:
                    return new DeadlineCommand(input);
                case EVENT:
                    return new EventCommand(input);
                default:
                    throw new TalkieUnknownCommandException(input);
            }
        } catch (IllegalArgumentException e) {
            throw new TalkieUnknownCommandException(input);
        }
    }
}



