package duke;

public class Parser {

    /**
     * Parses user commands and executes corresponding methods.
     *
     * @param cmds List of all existing tasks.
     * @param userCmd Current user command to be parsed.
     * @return Boolean value to indicate when user finished giving all input.
     * @throws DuckException if user command given is invalid
     * */
    public static boolean parseCmd(TaskList cmds, String userCmd) throws DuckException {
        if (userCmd.toLowerCase().equals("bye")) {
            Ui.printText("Bye. Hope to see you again soon!");
            cmds.save();
            return false;

        } else if (userCmd.toLowerCase().equals("list")) {
            cmds.printTasks();

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0,4).toLowerCase().equals("mark"))) {
            int num = Integer.valueOf(userCmd.substring(5));
            cmds.mark(num);

        } else if ((userCmd.length() >= 6) && (userCmd.substring(0,6).toLowerCase().equals("unmark"))) {
            int num = Integer.valueOf(userCmd.substring(7));
            cmds.unmark(num);

            //return "unmark";
        } else if ((userCmd.length() >= 6) && (userCmd.substring(0,6).toLowerCase().equals("delete"))) {
            int num = Integer.valueOf(userCmd.substring(7));
            cmds.delete(num);

        } else if ((userCmd.length() >= 4) && (userCmd.substring(0,4).toLowerCase().equals("todo"))) {
            if (userCmd.length() == 4) {
                throw new DuckException("The description of a todo cannot be empty.");
            }
            cmds.add(new Todo(userCmd.substring(5)));

        } else if ((userCmd.length() >= 8) && (userCmd.substring(0,8).toLowerCase().equals("deadline"))) {
            if (userCmd.length() == 8) {
                throw new DuckException("The description of a deadline cannot be empty.");
            }
            userCmd = userCmd.substring(9);
            int n = userCmd.indexOf("/");
            if ((n==-1) || (userCmd.substring(n + 4).isEmpty())) {
                throw new DuckException("A deadline needs an end date.");
            }
            cmds.add(new Deadline(userCmd.substring(0, n), userCmd.substring(n+4)));

        } else if ((userCmd.length() >= 5) && (userCmd.substring(0,5).toLowerCase().equals("event"))) {
            if (userCmd.length() == 5) {
                throw new DuckException("The description of a event cannot be empty.");
            }
            userCmd = userCmd.substring(6);
            int start = userCmd.indexOf("/");
            int end = userCmd.substring(start+1).indexOf("/");
            if ((start == -1) || (end == -1)) {
                throw new DuckException("An event needs both a start and end date or time.");
            }
            cmds.add(new Event(userCmd.substring(0, start),
                    userCmd.substring(start+6, start+end),
                    userCmd.substring(start+1+end+4)));

        } else {
            throw new DuckException("Given command is not a valid user input.");
        }
        return true;
    }
}
