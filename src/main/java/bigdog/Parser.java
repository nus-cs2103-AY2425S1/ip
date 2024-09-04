package bigdog;

public class Parser {

    // [command, description, start date, end date]
    private static final String[] VALID_COMMANDS = {"bye", "list", "mark", "unmark", "delete", "todo",
            "deadline", "event", "find"};

    public Parser() {

    }

    public static String[] parse(String str) throws BigdogException {
        String[] temp = str.split(" ",2);
        String command = temp[0].toLowerCase();
        for (int i = 0; i < 9; i++) {
            if (command.equals(VALID_COMMANDS[i])) {
                break;
            }
            if (i == 8) {
                throw new BigdogException("Parse Error: Invalid Argument!");
            }
        }
        temp[0] = command;
        return temp;
    }

}