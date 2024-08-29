import java.util.ArrayList;

public class Parser {
    private static final String[] COMMANDS = new String[]{"list", "mark", "unmark", "event", "todo", "deadline", "delete", "bye"};
    public Parser(){}

    public static Command parse(String input) {
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
        } else if (i == 6){
            return new DeleteCommand(details);
        } else {
            return new ExitCommand();
        }
    }
}


