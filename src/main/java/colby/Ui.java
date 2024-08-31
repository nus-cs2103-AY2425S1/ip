package colby;

/**
 * Includes the methods used to print messages for the user to see
 */
public class Ui {
    /**
     * Prints welcome message of the chatbot introdui=cing it's name and asking what it can do
     * for you
     */
    public void showWelcomeMessage() {
        String logo = "  ____      _ _\n"
                + " / ___|___ | | |__  _   _\n"
                + "| |   / _ \\| | '_ \\| | | |\n"
                + "| |__| (_) | | |_) | |_| |\n"
                + "\\____\\___/|_|_.__/ \\__, /\n"
                + "                   |___/\n";
        System.out.println("Hello! I'm\n" + logo + "\n" + "What can I do for you?\n");
    }

    /**
     * Prints goodbye message when the user typed in "bye"
     */
    public void showGoodbyeMessage() {
        System.out.println("  Bye bye! Hope to see you again soon! :)");
        System.out.println("_________________________________________________");
    }

    /**
     * Prints a message to show the user that the task they input has been added to the list
     * Prints the number of tasks in the list
     * @param task task the user inputs
     * @param taskCount number of tasks in the list
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Alright, I have added this task to the list:\n"
                + "    " + task + "\n"
                + "  Your list now has " + taskCount + (taskCount == 1 ? " task" : " tasks") + " :)");
    }

    /**
     * Prints out all the tasks in the list
     * @param taskList list containing the tasks to br printed
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here's all the tasks you have to do:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + taskList.getTask(i).toString());
        }
    }

    /**
     * Prints a message that the task has been marked as done
     * @param task task that shows it is marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.println("Great job! I have now marked this task as done!\n"
                + "    [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints a message that the task has been marked as undone
     * @param task task that shows it is marked as not done
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Alright, I have marked this task as not done yet.\n"
                + "    [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints a message that task has been deleted from the list
     * @param task task that is shown to be deleted from list
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Okay, I have removed this task from your list:\n" +
                "    " + task.toString());
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    public void showUnknownCommandMessage() {
        System.out.println("Sorry!! I'm not sure how to add that to the list for you, " +
                "try specifying the type of task!");
    }
}
