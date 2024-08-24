public class Parser {

    public static Command parse(String input) throws MoiMoiException {
        String[] commandArgs = input.split(" ", 2);
        String commandString = commandArgs[0];
        Command command;

        try {
            switch (commandString) {
            case "todo":
            case "deadline":
            case "event":
                command = new AddCommand(commandString, Parser.extractArgs(commandArgs));
                break;
            case "delete":
                command = new DeleteCommand(Parser.extractArgs(commandArgs));
                break;
            case "mark":
                command = new MarkCommand(Parser.extractArgs(commandArgs));
                break;
            case "unmark":
                command = new UnmarkCommand(Parser.extractArgs(commandArgs));
                break;
            case "list":
                command = new ListCommand();
                break;
            case "filter":
                command = new FilterCommand(Parser.extractArgs(commandArgs));
                break;
            case "bye":
                command = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        }
        return command;
    }

    private static String extractArgs(String[] commandArgs) {
        return commandArgs[1];
    }

}
