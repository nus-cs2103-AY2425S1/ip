package alex;

import java.util.Scanner;

import alex.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    //Greet user
    private String greeting =
            """
                     Hello! I'm Alex, your personal assistant
                     What can I do for you today?
                                                                """;

    //Farewell message
    private String farewell =
            """
                    Bye. Hope to see you again soon!
                                                                """;

//    //Create separation line
//    private String line = "____________________________________________________________";

    //private Scanner inputScanner = new Scanner(System.in);

    /**
     * Displays welcome greeting message when the chatbot is booted up.
     */
    public String showWelcome() {
        return this.greeting;
    }

    /**
     * Displays goodbye message when user is done interacting with chatbot.
     */
    public String showGoodbye() {
        return this.farewell;
    }

    /**
     * Displays error message to user when an Exception is raised.
     *
     * @param e Exception that occurred during execution of chatbot operations.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Displays the list of Tasks to users.
     *
     * @param tasks TaskList that holds the list of Tasks to be displayed.
     */
    public String showTasks(TaskList tasks, String message) {
        return tasks.showTasks(message);
    }

    /**
     * Displays a message when user marks a Task as done.
     * @param task Task that was marked.
     */
    public String showMark(Task task) {
        return "Nice! I've marked this task as done: \n    " + task;
    }

    /**
     * Displays a message when user marks a Task as not done.
     * @param task Task that was unmarked.
     */
    public String showUnmark(Task task) {
        return "OK, I've marked this task as not done yet: \n    " + task;
    }

    /**
     * Displays a message to the user based on user command and the action taken by chatbot.
     *
     * @param str Description of the action taken by chatbot.
     * @param task Task object that was involved in the action taken by the chatbot.
     * @param size Number of tasks in the Tasklist.
     */

    public String showMessage(String str, Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n    ");
        sb.append(task);
        sb.append("\n");
        sb.append("Now you have " + size + " tasks in the list");

        return sb.toString();
    }
}
