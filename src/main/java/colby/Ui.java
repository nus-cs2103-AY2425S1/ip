package colby;

/**
 * Includes the methods used to print messages for the user to see
 */

public class Ui {
    /**
     * Prints welcome message of the chatbot introdui=cing it's name and asking what it can do
     * for you
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Colby :)" + "\n" + "What can I do for you?\n";
    }

    /**
     * Prints goodbye message when the user typed in "bye"
     */
    public String showGoodbyeMessage() {
        return "Bye bye! Hope to see you again soon! :)";
    }

    /**
     * Prints a message to show the user that the task they input has been added to the list
     * Prints the number of tasks in the list
     * @param task task the user inputs
     * @param taskCount number of tasks in the list
     */
    public String showTaskAdded(Task task, int taskCount) {
        if (task.toString().contains("Please")) {
            return task.toString();
        }
        return "Alright, I have added this task to the list:" + task + "\n"
                + "Your list now has " + taskCount + (taskCount == 1 ? " task" : " tasks") + " :)";
    }

    /**
     * Prints a message that the task has been marked as done
     * @param task task that shows it is marked as done
     */
    public String showTaskMarked(Task task) {
        return "Great job! I have now marked this task as done!\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
    }

    /**
     * Prints a message that the task has been marked as undone
     * @param task task that shows it is marked as not done
     */
    public String showTaskUnmarked(Task task) {
        return "Alright, I have marked this task as not done yet.\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
    }

    /**
     * Prints a message that task has been deleted from the list
     * @param task task that is shown to be deleted from list
     */
    public String showTaskDeleted(Task task) {
        return "Okay, I have removed this task from your list:\n" +
                "    " + task.toString();
    }

}
