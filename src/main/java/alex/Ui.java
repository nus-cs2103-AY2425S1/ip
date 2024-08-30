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
                    ____________________________________________________________
                     Hello! I'm Alex, your personal assistant
                     What can I do for you today?
                    ____________________________________________________________""";

    //Farewell message
    private String farewell =
            """
                    ____________________________________________________________
                    Bye. Hope to see you again soon!
                    ____________________________________________________________""";

    //Create separation line
    private String line = "____________________________________________________________";

    private Scanner inputScanner = new Scanner(System.in);

    /**
     * Displays welcome greeting message when the chatbot is booted up.
     */
    public void showWelcome() {
        System.out.println(this.greeting);
    }

    /**
     * Displays goodbye message when user is done interacting with chatbot.
     */
    public void showGoodbye() {
        System.out.println(this.farewell);
    }

    /**
     * Displays error message to user when an Exception is raised.
     *
     * @param e Exception that occurred during execution of chatbot operations.
     */
    public void showError(Exception e) {
        System.out.println(line + "\n" + e.getMessage() + "\n" + line);
    }

    /**
     * Allows user to type in a line of input or command.
     *
     * @return a String based on what the user has typed in.
     */
    public String readCommand() {
        //create new scanner for the line of user input
        return inputScanner.nextLine();
    }

    /**
     * Displays the list of Tasks to users.
     *
     * @param tasks TaskList that holds the list of Tasks to be displayed.
     */
    public void showTasks(TaskList tasks, String message) {
        tasks.showTasks(this.line, message);
    }

    /**
     * Displays a message when user marks a Task as done.
     * @param task Task that was marked.
     */
    public void showMark(Task task) {
            System.out.println(line + "\nNice! I've marked this task as done: \n" + task + "\n" + line);
    }

    /**
     * Displays a message when user marks a Task as not done.
     * @param task Task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println(line + "\nOK, I've marked this task as not done yet: \n" + task + "\n" + line);
    }

    /**
     * Displays a message to the user based on user command and the action taken by chatbot.
     *
     * @param str Description of the action taken by chatbot.
     * @param task Task object that was involved in the action taken by the chatbot.
     * @param size Number of tasks in the Tasklist.
     */

    public void showMessage(String str, Task task, int size) {
        System.out.println(this.line);
        System.out.println(str);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(this.line);
    }
}
