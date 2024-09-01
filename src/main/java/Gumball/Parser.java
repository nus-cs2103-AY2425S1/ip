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
            int num = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            return new MarkCommand(num);
        } else if (input.startsWith("delete ")) {
            int num = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            return new DeleteCommand(num);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else {
            throw(new InputErrorException("Sorry I don't know how to do that"));
        }
    }
}
