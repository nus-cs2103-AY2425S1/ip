public class Parser {

    public static Command parse(String input) throws MoiMoiException {

        try {
            String[] commandArgs = input.split(" ", 2);
            CommandEnum commandEnum = CommandEnum.valueOf(commandArgs[0].toUpperCase());
            Command command;

            switch (commandEnum) {
            case TODO:
            case DEADLINE:
            case EVENT:
                command = new AddCommand(commandEnum, Parser.extractArgs(commandArgs));
                break;
            case DELETE:
                command = new DeleteCommand(Parser.extractArgs(commandArgs));
                break;
            case MARK:
                command = new MarkCommand(Parser.extractArgs(commandArgs));
                break;
            case UNMARK:
                command = new UnmarkCommand(Parser.extractArgs(commandArgs));
                break;
            case LIST:
                command = new ListCommand();
                break;
            case FILTER:
                command = new FilterCommand(Parser.extractArgs(commandArgs));
                break;
            case BYE:
                command = new ExitCommand();
                break;
            default:
                throw new InvalidCommandException();
            }

            return command;
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingArgumentException();
        }

    }

    private static String extractArgs(String[] commandArgs) {
        return commandArgs[1];
    }

}
