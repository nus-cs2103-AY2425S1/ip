package tars;

import java.util.ArrayList;
/**
 * Processes User inputs and executes commands based on inputs
 * Has method to also list all Tasks added by User
 *
 * @author csk
 * @version 1
 */

public class Parser {
    private static final String LINE = "_____________________________________________";

    public Parser() {
    }
    /**
     * Checks user inputs after being taken as string and string[] variables
     * Chooses appropriate methods to call based on input given or throws error message
     * depending on incorrect input received
     *
     * @param entryParts
     * @param entry
     * @param tasks
     */
    public String checkEntry(String[] entryParts, String entry, TaskList tasks) {
        String result = "";

        assert tasks != null : "Task list is empty!";
        assert entryParts.length > 0 : "Invalid input of task!";

        try {
            if (entry.equals("t") || entry.equals("d") || entry.equals("e")) {
                throw new TarsException(LINE + "\n" + "    OOPS! Describe the t/e/d or list"
                        + "\n" + LINE);
            } else if (entryParts[0].equals("list")) {
                result = this.listPrint(tasks);
            } else {
                throw new TarsException(LINE + "\n" + "    OOPS! Only accept a t/e/d as input"
                        + "\n" + LINE);
            }
        } catch (TarsException e) {
            result = e.getMessage();
        }
        return result;
    }

    /**
     * Prints all the Tasks in the list at the current state of storage
     *
     * @param tasks
     */
    public String listPrint(TaskList tasks) {
        String result = "";
        StringBuilder strBuild = new StringBuilder();

        assert tasks != null : "Task list is empty!";

        if (tasks.getList().size() == 0) {
            result = "No tasks added to list. Please add3 events/deadLINE/todos!";
        } else {
            for (int i = 0; i < tasks.getList().size(); i++) {
                strBuild.append("    " + (i + 1) + ". " + tasks.getList().get(i) + "\n");
            }
            result = LINE + "\n" + "    Here are the tasks in your list:\n" + strBuild.toString() + "\n" + LINE;
        }
        return result;
    }

    /**
     * Finds Task based on keyword stated by User, takes in entry of users and tasks in list
     * Goes through all the tasks in the list that match keyword stated by User
     *
     * @param entryParts
     * @param tasks
     */
    public String findTask(String[] entryParts, TaskList tasks) {
        String result = "";

        assert tasks != null : "Task list is empty!";
        assert entryParts.length > 0 : "Invalid input of task!";

        StringBuilder strBuild = new StringBuilder();
        ArrayList<Task> resultList = new ArrayList<>();
        String taskPrefix = entryParts[1];

        for (int i = 0; i < tasks.getList().size(); i++) {
            String[] j = tasks.getList().get(i).toString().split(" ");

            for (int k = 3; k < j.length; k++) {
                if (j[k].equals(taskPrefix)) {
                    resultList.add(tasks.getList().get(i));
                }
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            strBuild.append("    " + (i + 1) + ". " + resultList.get(i) + "\n");
        }
        result = (LINE + "\n" + " Here are the matching tasks in your list" + "\n" + strBuild.toString() + "\n"
                + LINE);

        return result;
    }
}
