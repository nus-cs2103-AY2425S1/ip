package janet;

public class Ui {
    // handles the interaction with the user
    // receives inputs (readline) and printing outputs to the command line

    private static final String horizontalLine = "____________________________________________________________";

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showWelcome() {
        System.out.println(horizontalLine + "\nHello! I'm Janet\n" + "What can I do for you?\n" + horizontalLine);
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + horizontalLine);
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
        System.out.println(horizontalLine + "\nGot it. I've added this task:\n" +
                "  " + task + "\n" + String.format("Now you have %d tasks in the list\n", numberOfTasks) + horizontalLine);
    }

    public void showTasks(TaskList taskList) {
        // show all tasks inside the list (when user types in 'list')
        String currentList = horizontalLine + "\nHere are the tasks in your list:\n";
        if (taskList.isEmpty()) {
            // empty listOfTasks
            System.out.println(currentList + "*** Current list is empty ***\n" + horizontalLine);
        } else {
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                if (i == taskList.getNumberOfTasks() - 1) {
                    currentList += (i+1) + ". " + taskList.getListOfTasks().get(i) + "\n" + horizontalLine;
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
            System.out.println(horizontalLine + "\nThis task is already done!\n" + horizontalLine);
        } else {
            System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n" +
                    String.format("  %s", task + "\n" + horizontalLine));
        }
    }

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

    public void showFindMessage(TaskList taskList) {
        // taskList is a subset of the current total taskList
        System.out.println(horizontalLine + "\nHere are the matching tasks in your list:\n"
                + displayTasksInList(taskList) + "\n" + horizontalLine);
    }

    public String displayTasksInList(TaskList taskList) {
        String tasks = "";
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            tasks += (i + 1) + ". " + taskList.getTask(i) + "\n";
        }
        return tasks;
    }

    public void showDeleteTaskMessage(Task deletedTask, int numberOfTasks) {
        System.out.println(horizontalLine + "\nNoted. I've removed this task:\n" +
                String.format("    %s\nNow you have %d tasks in your list\n",
                        deletedTask, numberOfTasks) + horizontalLine);
    }
}
