package Gumball;

import java.io.IOException;

public class Parser {

    /**
     * Returns a Command which can be executed at a later stage.
     * @param input The user input that determines what command is returned.
     * @return The command based on input.
     * @throws InputErrorException
     * @throws IOException
     */
    public static Command parse(String input) throws InputErrorException, IOException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (isATask(input)) {
            return new AddCommand(input);
        } else if(input.startsWith("find ")) {
            return new FindCommand(input);
        } else if(input.equals("bye")) {
            return new ExitCommand();
        } else {
            throw (new InputErrorException("Sorry I don't know how to do that"));
        }
    }

    private static boolean isATask(String input) {
        return input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event")
                || input.startsWith("fixed");
    }
}
