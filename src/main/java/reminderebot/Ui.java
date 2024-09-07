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
    private static final String name = "Reminderebot";
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    private static final String greetingText = topBuffer
            + " Hello! I'm [" + name + "]\n"
            + " What can I do for you?\n" + topBuffer;
    private static final String goodbyeText =
            " Bye. Hope to see you again soon!";
    private Scanner input;

    /**
     * Instantiate the User Interface.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Returns a string representing the welcome message.
     */
    public String showWelcome() {
        return greetingText;
    }

    /**
     * Returns a string representing the goodbye message and closes Scanner.
     */
    public String goodbye() {
        input.close();
        return goodbyeText;
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
     * Returns a string representing the error message.
     * @param message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a string representing the marked task.
     * @param task
     * @return marked task
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Returns a string representing the unmarked task.
     * @param task
     * @return unmarked task
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a string representing the deleted task.
     * @param task
     * @param index
     * @return deleted task
     */
    public String deleteTask(Task task, int index) {
        return "OK, I've removed this task:\n" + task.toString()
            + "\nNow you have " + index + " tasks in the list.";
    }

    /**
     * Returns a string representing the added task.
     * @param task
     * @param index
     * @return added task
     */
    public String addTask(Task task, int index) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + index + " tasks in the list.";
    }

    /**
     * Returns a string representing the tasks found.
     * @param tasksFound
     * @return tasks found
     */
    public String findTask(ArrayList<Task> tasksFound) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksFound.size(); i++) {
            output.append(i + 1).append(".").append(tasksFound.get(i)).append("\n");
        }
        return output.toString();
    }
}
