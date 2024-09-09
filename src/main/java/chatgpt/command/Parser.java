package chatgpt.command;

import chatgpt.exception.ChatBotException;

/**
 *  The Parser class handles user inputs and returns the respective command.
 */
public abstract class Parser {
    /** Enum of the possible Command types **/
    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE;
    }

    /**
     *
     * Returns the respective Command subclass with the necessary arguments
     * from the user input.
     *
     * @param fullCommand including the command and arguments of the command
     * @return Command subclass based on the user input
     * @throws ChatBotException when there is an invalid command or missing argument after command
     */
    public static Command parse(String fullCommand) throws ChatBotException {
        try {
            String[] inputs = fullCommand.split(" ", 2);
            return switch (Commands.valueOf(inputs[0].toUpperCase())) {
                case BYE -> new ExitCommand();
                case LIST -> new ListCommand();
                case TODO -> new AddCommand("TODO", inputs[1]);
                case DEADLINE -> new AddCommand("DEADLINE", inputs[1]);
                case EVENT -> new AddCommand("EVENT", inputs[1]);
                case MARK -> new CompleteCommand(Integer.parseInt(inputs[1]), true);
                case UNMARK -> new CompleteCommand(Integer.parseInt(inputs[1]), false);
                case DELETE -> new DeleteCommand(Integer.parseInt(inputs[1]));
            };
        } catch (IllegalArgumentException e) {
            throw new ChatBotException("\t Oops!! I don't understand what that means :((");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("\t Oh no!! Inputs after the command cannot be empty");
        }
    }


}
