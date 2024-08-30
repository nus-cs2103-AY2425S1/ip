package twilight;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class Parser {
    private static final String[] COMMANDS = new String[]{"list", "mark", "unmark", "todo", "event", "deadline", "delete", "find", "bye"};
    public Parser(){}

    public static Command parse(String input) throws InvalidInputException {
        try {
            String[] split = input.split(" ", 2);
            String command = split[0];
            String details = split[1];
            int i = 0;
            while (!COMMANDS[i].equals(command)) {
                i++;
            }
            if (i == 0) {
                return new ListCommand();
            } else if (i < 3) {
                return new MarkingCommand(i, details);
            } else if (i <= 5) {
                return new AddCommand(i, details);
            } else if (i < 7){
                return new DeleteCommand(details);
            } else {
                return new SearchCommand(details);
            }
        } catch (IndexOutOfBoundsException e) {
            if (input.equals("list")) {
                return new ListCommand();
            } else if (input.equals("bye")) {
                return new ExitCommand();
            } else {
                throw new InvalidInputException("Not a valid input!");
            }
        }
    }
}


