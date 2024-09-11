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
        assert !userCmd.isEmpty() : "Command cannot be empty";

        if (userCmd.toLowerCase().equals("bye")) {
            cmds.save();
            return "Bye. Hope to see you again soon!";

        } else if (userCmd.toLowerCase().equals("list")) {
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
                return "Cannot find an empty string.";
            }
            return cmds.find(userCmd.substring(5));

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0, 4).toLowerCase().equals("todo"))) {
            if (userCmd.length() == 4) {
                return "The description of a todo cannot be empty.";
            }
            return cmds.add(new Todo(userCmd.substring(5)));

        } else if ((userCmd.length() >= 8) && (userCmd.substring(0, 8).toLowerCase().equals("deadline"))) {
            if (userCmd.length() == 8) {
                return "The description of a deadline cannot be empty.";
            }
            userCmd = userCmd.substring(9);
            int n = userCmd.indexOf("/");
            if ((n == -1) || (userCmd.substring(n + 4).isEmpty())) {
                return "A deadline needs an end date.";
            }
            return cmds.add(new Deadline(userCmd.substring(0, n), userCmd.substring(n+4)));

        } else if ((userCmd.length() >= 5) && (userCmd.substring(0, 5).toLowerCase().equals("event"))) {
            if (userCmd.length() == 5) {
                return "The description of a event cannot be empty.";
            }
            userCmd = userCmd.substring(6);
            int start = userCmd.indexOf("/");
            int end = userCmd.substring(start + 1).indexOf("/");
            if ((start == -1) || (end == -1)) {
                return "An event needs both a start and end date.";
            }
            return cmds.add(new Event(userCmd.substring(0, start),
                    userCmd.substring(start + 6, start + end),
                    userCmd.substring(start + 1 + end + 4)));

        } else {
            return "I don't recognise that command.";
        }
    }
}
