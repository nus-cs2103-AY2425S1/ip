package tars;

import java.util.ArrayList;
import java.util.function.IntUnaryOperator;

/**
 * Processes User inputs and executes commands based on inputs
 * Has method to also list all Tasks added by User
 *
 * @author csk
 * @version 1
 */

public class Parser {
    private static final String LINE = " _____________________________________________";

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
     * Adds task to list based on input given or marks/unmarks task based on action required
     * Calls respective method to add Task based on type identified of task stated in input
     *
     * @param task
     * @param entry
     */
    public String addTask(String[] task, String entry, TaskList taskList) {
        String result = "";
        if (task[0].equals("m") || task[0].equals("um") || task[0].equals("delete")) {
            //used ChatGPT to check what exception class to use for invalid input for index parsing
            try {
                Integer index = Integer.parseInt(task[task.length - 1]);
                assert index > 0 && index <= taskList.getList().size() : "Task index invalid";
                if (task[0].equals("m")) {
                    result = taskList.markTask(index, 1);
                } else if (task[0].equals("um")) {
                    result = taskList.markTask(index, 0);
                } else if (task[0].equals("delete")) {
                    result = taskList.deleteTask(index);
                } else {
                    result = "Please state task index followed by m/um/delete command";
                }
            } catch (NumberFormatException e) {
                result = e.getMessage() + ".Please state task index followed by m/um/delete command";
            }
        } else if (task[0].equals("t")) {
            result = taskList.addToDos(task, entry);
        } else if (task[0].equals("d")) {
            result = taskList.addDeadline(task, entry);
        } else if (task[0].equals("e")) {
            result = taskList.addEvent(task, entry);
        } else {
            result = "OOPS! Unable to add task. Please type 'help'!";
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
            result = "No tasks added to list. Please add events/deadline/todos!";
        } else {
            for (int i = 0; i < tasks.getList().size(); i++) {
                strBuild.append("    " + (i + 1) + ". " + tasks.getList().get(i) + "\n");
            }
            result = LINE + "\n" + "    Here are the tasks in your list:\n" + strBuild.toString() + LINE;
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
