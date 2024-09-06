package chatgpt.command;

import chatgpt.exception.ChatBotException;

public abstract class Parser {
    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND;
    }

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
                case FIND -> new FindCommand(inputs[1]);
            };
        } catch (IllegalArgumentException e) {
            throw new ChatBotException("\t Oops!! I don't understand what that means :((");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException("\t Oh no!! Inputs after the command cannot be empty");
        }
    }


}
