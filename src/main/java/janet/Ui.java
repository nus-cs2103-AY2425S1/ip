package janet;

/**
 * Represents the UI of Janet.
 */
public class Ui {
    // handles the interaction with the user
    // receives inputs (readline) and printing outputs to the command line

    private static final String horizontalLine = "____________________________________________________________";

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println(horizontalLine + "\nHello! I'm Janet\n" + "What can I do for you?\n" + horizontalLine);
    }


    /**
     * Prints the exit message.
     */
    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + horizontalLine);
    }


    /**
     * Prints message if janet.txt is not found in the user's directory.
     */
    public void showLoadingError() {
        System.out.println("janet.txt not found! Creating janet.txt for you...");
    }


    /**
     * Prints message if unable to write list of tasks into .txt file.
     */
    public void showSavingError() {
        System.out.println("WHOOPS! Having problems with saving your tasks...");
    }


    /**
     * Prints message when task is successfully added into the list of tasks.
     *
     * @param task The task that the user specified.
     * @param numberOfTasks The number of tasks in the list.
     */
    public void showSuccessfulTaskAddition(Task task, int numberOfTasks) {
        // when janet.TaskList.addTaskToList is called (addition of task into listOfTasks)
        // task = newly added janet.Task object
        System.out.println(horizontalLine + "\nGot it. I've added this task:\n" +
                "  " + task + "\n" + String.format("Now you have %d tasks in the list\n", numberOfTasks) + horizontalLine);
    }


    /**
     * Prints the tasks into the terminal.
     *
     * @param taskList The list of tasks.
     */
    public void showTasks(TaskList taskList) {
        // show all tasks inside the list (when user types in 'list')
        String currentList = horizontalLine + "\nHere are the tasks in your list:\n";
        if (taskList.isEmpty()) {
            // empty listOfTasks
            System.out.println(currentList + "*** Current list is empty ***\n" + horizontalLine);
        } else {
            currentList += displayTasksInList(taskList) + "\n" + horizontalLine;
            System.out.println(currentList);
        }
    }


    /**
     * Prints message when task is marked.
     *
     * @param markResult Whether the task has already been marked or not.
     * @param task The task that is specified by the user.
     */
    public void showMarkedMessage(String markResult, Task task) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be marked as done
        // task = newly marked janet.Task object
        if (markResult.equals("already marked")) {
            // the desired task is already marked as done
            System.out.println(horizontalLine + "\nThis task is already done!\n" + horizontalLine);
        } else {
            System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n" +
                    String.format("  %s", task + "\n" + horizontalLine));
        }
    }


    /**
     * Prints message when task is unmarked.
     *
     * @param markResult Whether the task has already been unmarked or not.
     * @param task The task that is specified by the user.
     */
    public void showUnmarkedMessage(String markResult, Task task) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be unmarked
        // task = newly unmarked janet.Task object
        if (markResult.equals("already unmarked")) {
            // the desired task is already marked as done
            System.out.println(horizontalLine + "\nThis task is not already done!\n" + horizontalLine);
        } else {
            System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n" +
                    String.format("  %s", task + "\n" + horizontalLine));
        }
    }


    /**
     * Prints message to show tasks that matched keyword.
     *
     * @param taskList The list of tasks.
     */
    public void showFindMessage(TaskList taskList) {
        // taskList is a subset of the current total taskList
        if (taskList.isEmpty()) {
            System.out.println(horizontalLine + "\nHere are the matching tasks in your list:\n"
                    + "OOPS! No tasks match what you were looking for!" + "\n" + horizontalLine);
        } else {
            System.out.println(horizontalLine + "\nHere are the matching tasks in your list:\n"
                    + displayTasksInList(taskList) + "\n" + horizontalLine);
        }
    }


    /**
     * Returns a String, containing the list of tasks in numbered order.
     *
     * @param taskList The list of tasks.
     * @return A string message showing the list of tasks.
     */
    public static String displayTasksInList(TaskList taskList) {
        String tasks = "";
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            if (i == taskList.getNumberOfTasks() - 1) {
                // reached last element in the list
                tasks += (i+1) + ". " + taskList.getTask(i);
                break;
            }
            tasks += (i+1) + ". " + taskList.getTask(i) + "\n";
        }
        return tasks;
    }


    /**
     * Prints the message when task is deleted from the list.
     *
     * @param deletedTask The task that the user specified.
     * @param numberOfTasks The number of tasks in the list.
     */
    public void showDeleteTaskMessage(Task deletedTask, int numberOfTasks) {
        System.out.println(horizontalLine + "\nNoted. I've removed this task:\n" +
                String.format("    %s\nNow you have %d tasks in your list\n",
                        deletedTask, numberOfTasks) + horizontalLine);
    }
}
