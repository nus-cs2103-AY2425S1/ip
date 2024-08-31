package reminderebot;

import java.util.ArrayList;
import java.util.Scanner;
import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;

/**
 * Ui represents the user interface for Reminderebot.
 */
public class Ui {
    private Scanner input;
    private static final String name = "Reminderebot";
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    private static final String greetingText = topBuffer +
            " Hello! I'm [" + name + "]\n" +
            " What can I do for you?\n" +
            topBuffer;
    private static final String goodbyeText =
            " Bye. Hope to see you again soon!";

    /**
     * Instantiate the User Interface.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prints greeting message.
     */
    public void showWelcome() {
        System.out.println(greetingText);
    }

    /**
     * Prints goodbye message.
     */
    public void goodbye() {
        System.out.println(goodbyeText);
    }

    /**
     * Reads user input as command.
     * @return string containing user commands
     */
    public String readCommand() {
        return this.input.nextLine();
    }

    /**
     * Prints the buffer seperating messages.
     */
    public void showLine() {
        System.out.println(topBuffer);
    }

    /**
     * Prints error messages.
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints mark task as done.
     * @param task
     */
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints mark task as undone.
     * @param task
     */
    public void unmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints deleted task.
     * @param task
     * @param index
     */
    public void deleteTask(Task task, int index) {
        System.out.println("OK, I've removed this task:\n" +
                task.toString() +
                "\nNow you have " + index + " tasks in the list.");
    }

    /**
     * Prints added task.
     * @param task
     * @param index
     */
    public void addTask(Task task, int index) {
        System.out.println("Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " + index + " tasks in the list."
        );
    }

    public void findTask(ArrayList<Task> tasksFound) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        for (int i=0; i<tasksFound.size(); i++) {
            output.append(i+1).append(".").append(tasksFound.get(i)).append("\n");
        }
        System.out.println(output);
    }
}
