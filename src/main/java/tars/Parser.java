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
    private static final String LINE = "    _____________________________________________";

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
    public void checkEntry(String[] entryParts, String entry, TaskList tasks) {
        try {
            if (entry.equals("todo") || entry.equals("deadLINE") || entry.equals("event")) {
                throw new TarsException(LINE + "\n" + "    OOPS! Describe the task/event/deadLINE/todo or list"
                        + "\n" + LINE);
            } else if (entryParts[0].equals("list")) {
                this.listPrint(tasks);
            } else {
                throw new TarsException(LINE + "\n" + "    OOPS! Only accept a task/event/deadLINE/todo as input"
                        + "\n" + LINE);
            }
        } catch (TarsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the Tasks in the list at the current state of storage
     *
     * @param tasks
     */
    public void listPrint(TaskList tasks) {
        if (tasks.getList().size() == 0) {
            System.out.println("No tasks added to list. Please add events/deadLINE/todos!");
        } else {
            System.out.println(LINE + "\n" + "    Here are the tasks in your list:");
            for (int i = 0; i < tasks.getList().size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.getList().get(i));
            }
            System.out.println(LINE);
        }
    }

    /**
     * Finds Task based on keyword stated by User, takes in entry of users and tasks in list
     * Goes through all the tasks in the list that match keyword stated by User
     *
     * @param entryParts
     * @param tasks
     */
    public void findTask(String[] entryParts, TaskList tasks) {
        ArrayList<Task> resultList = new ArrayList<>();
        String taskPrefix = entryParts[1];

        for (int i = 0; i < tasks.getList().size(); i++) {
            String[] j = tasks.getList().get(i).toString().split(" ");
            System.out.println(j[3]);

            for (int k = 3; k < j.length; k++) {
                if (j[k].equals(taskPrefix)) {
                    System.out.println(taskPrefix);
                    System.out.println(j[k]);
                    resultList.add(tasks.getList().get(i));
                }
            }
        }

        System.out.println(LINE + "\n" + "    Here are the matching tasks in your list");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + resultList.get(i));
        }
        System.out.println(LINE);
    }
}
