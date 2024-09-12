package jag;

/**
 * Parser Class is responsible for taking in a user input and making sense of it
 * by checking what the user has entered and returning the right type of command
 * that the user has requested
 */
public class Parser {

    /**
     * Responsible for parsing a user input to read it's command
     * and return the appropriate instance of the right command
     *
     * @param fullCommand String representation of the full user input
     * @return An instance of a type of Command, from the list of Commands
     *              that has been extended by the abstract class Command
     * @throws AExceptions Instance thrown in the event user input does not include
     *              any of the right commands available
     */
    public static Command parse(String fullCommand) throws AExceptions {
        assert fullCommand.length() > 1 : "fullCommand length is not more than 1";
        // Breaking down command input
        String[] splitWords = fullCommand.split(" ");
        String com = splitWords[0].toUpperCase();
        Commands command;
        Command cmd = new ExitCommand();


        try {
            command = Commands.valueOf(com);

            switch (command) {
            case LIST:
                cmd = new ListCommand();
                break;

            case MARK:
                cmd = new MarkCommand(true);
                break;

            case UNMARK:
                cmd = new MarkCommand(false);
                break;

            case TODO:
                cmd = new AddCommand('T');
                break;
            case DEADLINE:
                cmd = new AddCommand('D');
                break;
            case EVENT:
                cmd = new AddCommand('E');
                break;

            case DELETE:
                cmd = new DeleteCommand();
                break;

            case BYE:
                cmd = new ExitCommand();
                break;

            case FIND:
                cmd = new FindCommand();
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
