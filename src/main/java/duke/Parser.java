package duke;
/**
 * Helper to parse through a given command.
 */
public class Parser {
    /**
     * Interprets a given command, and returns interpreted reply from Duck.
     *
     * @param cmds TaskList of existing commands.
     * @param userCmd String command to be interpreted
     * @return String reply from Duck.
     * @throws DuckException if file is corrupted.
     * */
    public static String parseCommand(TaskList cmds, String userCmd) throws DuckException {
        if (userCmd.toLowerCase().equals("bye")) {
            cmds.save();
            //Ui.printText("Bye. Hope to see you again soon!");
            return "Bye. Hope to see you again soon!";
            //return false;

        } else if (userCmd.toLowerCase().equals("list")) {
            //cmds.printTasks();
            return cmds.getAllTasks();

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0, 4).toLowerCase().equals("mark"))) {
            int num = Integer.valueOf(userCmd.substring(5));
            return cmds.mark(num);

        } else if ((userCmd.length() >= 6) && (userCmd.substring(0, 6).toLowerCase().equals("unmark"))) {
            int num = Integer.valueOf(userCmd.substring(7));
            return cmds.unmark(num);

        } else if ((userCmd.length() >= 6) && (userCmd.substring(0, 6).toLowerCase().equals("delete"))) {
            int num = Integer.valueOf(userCmd.substring(7));
            return cmds.delete(num);

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0, 4).toLowerCase().equals("find"))) {
            if (userCmd.length() == 4) {
                //throw new DuckException("Cannot find an empty string.");
                return "Cannot find an empty string.";
            }
            return cmds.find(userCmd.substring(5));

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0, 4).toLowerCase().equals("todo"))) {
            if (userCmd.length() == 4) {
                //throw new DuckException("The description of a todo cannot be empty.");
                return "The description of a todo cannot be empty.";
            }
            return cmds.add(new Todo(userCmd.substring(5)));

        } else if ((userCmd.length() >= 8) && (userCmd.substring(0, 8).toLowerCase().equals("deadline"))) {
            if (userCmd.length() == 8) {
                //throw new DuckException("The description of a deadline cannot be empty.");
                return "The description of a deadline cannot be empty.";
            }
            userCmd = userCmd.substring(9);
            int n = userCmd.indexOf("/");
            if ((n == -1) || (userCmd.substring(n + 4).isEmpty())) {
                //throw new DuckException("A deadline needs an end date.");
                return "A deadline needs an end date.";
            }
            return cmds.add(new Deadline(userCmd.substring(0, n), userCmd.substring(n+4)));

        } else if ((userCmd.length() >= 5) && (userCmd.substring(0, 5).toLowerCase().equals("event"))) {
            if (userCmd.length() == 5) {
                //throw new DuckException("The description of a event cannot be empty.");
                return "The description of a event cannot be empty.";
            }
            userCmd = userCmd.substring(6);
            int start = userCmd.indexOf("/");
            int end = userCmd.substring(start + 1).indexOf("/");
            if ((start == -1) || (end == -1)) {
                //throw new DuckException("An event needs both a start and end date or time.");
                return "An event needs both a start and end date or time.";
            }
            return cmds.add(new Event(userCmd.substring(0, start),
                    userCmd.substring(start + 6, start + end),
                    userCmd.substring(start + 1 + end + 4)));

        } else {
            //throw new DuckException("Given command is not a valid user input.");
            return "I don't recognise that command.";
        }
        //return "";
    }
}
