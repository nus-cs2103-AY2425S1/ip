package bigdog;

public class Parser {

    // [command, description, start date, end date]
    private static final String[] commands = {"bye", "list", "mark", "unmark", "delete", "todo", "deadline", "event"};

    public Parser() {

    }

    public static String[] parse(String str) throws BigdogException {
        String[] temp = str.split(" ",2);
        String command = temp[0].toLowerCase();
        for (int i = 0; i < 8; i++) {
            if (command.equals(commands[i])) {
                break;
            }
            if (i == 7) {
                throw new BigdogException("Parse Error: Invalid Argument!");
            }
        }
        temp[0] = command;
        return temp;
    }

}
