package sunny;

import java.util.List;
import java.util.Objects;

/**
 * Handles user inputs and generate appropriate replies
 */
public class Ui {

    // Stock messages
    public static final String LINE = "     ────────────────────";
    public static final String WELCOME = "     HELLO! I am Sunny:)\n     How can I help you?";
    public static final String GOODBYE = "     You are leaving? Ok bye:( come back soon";

    // List for tasks
    private List<Task> ls;

    // File path for external storage
    private String filePath = "/Users/jerryyou/ip/taskslist.txt";
    private Storage store = new Storage(filePath);

    /**
     * Generates welcome message, load in previous files
     * @return Welcome message
     */
    public String welcome() {
        ls = store.read();
        return WELCOME + "\n" + LINE + "\n";
    }

    /**
     * Takes in user replies, parse them, and generate appropriate replies
     * @param message passed from user through Sunny class
     * @return String to be printed out in Sunny class
     */
    public String getResponse(String message) {
        assert message != null : "Message should not be null";
        Parser p = new Parser(message);
        String command = p.getFirstHalf();

        if (Objects.equals(command, "bye")) {
            store.write(ls);
            return LINE + "\n" + GOODBYE;
        } else if (Objects.equals(command, "list")) {
            Command c = new ListCommand();
            return c.runCommand(ls, "");
        } else if (Objects.equals(command, "find")) {
            Command c = new FindCommand();
            String s = c.runCommand(ls, p.getSecondHalf());
            return s;
        } else if (Objects.equals(command, "")) {
            return LINE;
        } else if (Objects.equals(command, "mark")) {
            Command c = new MarkCommand();
            String s = c.runCommand(ls, p.getSecondHalf());
            store.write(ls);
            return s;
        } else if (Objects.equals(command, "unmark")) {
            Command c = new UnmarkCommand();
            String s = c.runCommand(ls, p.getSecondHalf());
            store.write(ls);
            return s;
        } else if (Objects.equals(command, "delete")) {
            Command c = new DeleteCommand();
            String s = c.runCommand(ls, p.getSecondHalf());
            store.write(ls);
            return s;
        } else if (Objects.equals(command, "snooze")) {
            Command c = new SnoozeCommand();
            String s = c.runCommand(ls, p.getSecondHalf());
            store.write(ls);
            return s;
        } else {
            try {
                Command c = new AddTaskCommand();
                String s = c.runCommand(ls, message);
                store.write(ls);
                return s;
            } catch (Exception e) {
                return LINE + "\n      " + e + LINE;
            }

        }
    }

}
