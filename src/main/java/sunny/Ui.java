package sunny;

import java.util.List;
import java.util.Objects;

/**
 * Handles user inputs and generate appropriate replies
 */
public class Ui {

    // Stock messages
    public static final String logo = "       _____   _    _  _   _  _   _  __     __  \n"
           + "      / ____| | |  | || \\ | || \\ | | \\ \\   / /  \n"
           + "     | (___   | |  | ||  \\| ||  \\| |  \\ \\_/ /   \n"
           + "      \\___ \\  | |  | || . ` || . ` |   \\   /    \n"
           + "      ____) | | |__| || |\\  || |\\  |    | |     \n"
           + "     |_____/   \\____/ |_| \\_||_| \\_|    |_|     \n";
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
     * @return
     */
    public String welcome() {
        ls = store.read();
        return "Hello from\n" + logo + "\n" + WELCOME + "\n" + LINE + "\n";
    }

    /**
     * Takes in user replies, parse them, and generate appropriate replies
     * @param message passed from user through Sunny class
     * @return String to be printed out in Sunny class
     */
    public String getResponse(String message) {
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
            return c.runCommand(ls, p.getSecondHalf());
        } else if (Objects.equals(command, "")) {
            return LINE;
        } else if (Objects.equals(command, "mark")) {
            Command c = new MarkCommand();
            return c.runCommand(ls, p.getSecondHalf());
        } else if (Objects.equals(command, "unmark")) {
            Command c = new UnmarkCommand();
            return c.runCommand(ls, p.getSecondHalf());
        } else if (Objects.equals(command, "delete")) {
            Command c = new DeleteCommand();
            return c.runCommand(ls, p.getSecondHalf());
        } else {
            try {
                Task t = TaskCreator.create(message + "|false");
                ls.add(t);
                store.write(ls);
                return LINE + "\n     "
                        + "Got it! added the task: \n     "
                        + t + "\n     "
                        + String.format("Now you have %h tasks in the list \n", ls.size()) + LINE;
            } catch (Exception e) {
                return LINE + "\n      " + e + LINE;
            }
        }
    }

}
