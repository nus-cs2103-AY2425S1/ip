package janet;

public class Ui {
    // handles the interaction with the user
    // receives inputs (readline) and printing outputs to the command line

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE + "\nHello! I'm Janet\n" + "What can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }

    public void showLoadingError() {
        System.out.println("janet.txt not found! Creating janet.txt for you...");
    }

    public void showSavingError() {
        System.out.println("WHOOPS! Having problems with saving your tasks...");
    }

    public void showSuccessfulTaskAddition(Task task, int numberOfTasks) {
        // when janet.TaskList.addTaskToList is called (addition of task into listOfTasks)
        // task = newly added janet.Task object
        System.out.println(HORIZONTAL_LINE + "\nGot it. I've added this task:\n"
                + "  " + task + "\n" + String.format("Now you have %d tasks in the list\n", numberOfTasks)
                + HORIZONTAL_LINE);
    }

    public void showTasks(TaskList taskList) {
        // show all tasks inside the list (when user types in 'list')
        String currentList = HORIZONTAL_LINE + "\nHere are the tasks in your list:\n";
        if (taskList.isEmpty()) {
            // empty listOfTasks
            System.out.println(currentList + "*** Current list is empty ***\n" + HORIZONTAL_LINE);
        } else {
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                if (i == taskList.getNumberOfTasks() - 1) {
                    currentList += (i+1) + ". " + taskList.getListOfTasks().get(i) + "\n" + HORIZONTAL_LINE;
                    break;
                }
                currentList += (i+1) + ". " + taskList.getListOfTasks().get(i) + "\n";
            }
            System.out.println(currentList);
        }
    }

    public void showMarkedMessage(String markResult, Task task) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be marked as done
        // task = newly marked janet.Task object
        if (markResult.equals("already marked")) {
            // the desired task is already marked as done
            System.out.println(HORIZONTAL_LINE + "\nThis task is already done!\n" + HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE + "\nNice! I've marked this task as done:\n"
                    + String.format("  %s", task + "\n" + HORIZONTAL_LINE));
        }
    }

    public void showUnmarkedMessage(String markResult, Task task) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be unmarked
        // task = newly unmarked janet.Task object
        if (markResult.equals("already unmarked")) {
            // the desired task is already marked as done
            System.out.println(HORIZONTAL_LINE + "\nThis task is not already done!\n" + HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE + "\nOK, I've marked this task as not done yet:\n"
                    + String.format("  %s", task + "\n" + HORIZONTAL_LINE));
        }
    }

    public void showDeleteTaskMessage(Task deletedTask, int numberOfTasks) {
        System.out.println(HORIZONTAL_LINE + "\nNoted. I've removed this task:\n"
                + String.format("    %s\nNow you have %d tasks in your list\n",
                        deletedTask, numberOfTasks) + HORIZONTAL_LINE);
    }
}
