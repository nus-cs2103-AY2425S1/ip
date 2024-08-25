package jag;

public class Parser {

    public static Command parse(String fullCommand) throws AExceptions {
        // Breaking down command input
        String[] splitWords = fullCommand.split(" ");
        String com = splitWords[0].toUpperCase();
        Commands command;
        Command cmd = new ExitCommand();


        try {
            command = Commands.valueOf(com);

            switch (command) {
                case LIST:
                    cmd =  new ListCommand();
                    break;

                case MARK:
                    cmd =  new MarkCommand(true);
                    break;

                case UNMARK:
                    cmd =  new MarkCommand(false);
                    break;

                case TODO:
                    cmd =  new AddCommand('T');
                    break;
                case DEADLINE:
                    cmd =  new AddCommand('D');
                    break;
                case EVENT:
                    cmd =  new AddCommand('E');
                    break;

                case DELETE:
                    cmd =  new DeleteCommand();
                    break;

                case BYE:
                    cmd =  new ExitCommand();
                    break;

                default:
                    throw new AExceptions("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new AExceptions("I'm sorry, but I don't know what that means :-(");
        }

        return cmd;
    }
}
