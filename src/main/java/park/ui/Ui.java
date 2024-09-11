package park.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import park.task.Task;

/**
 * Represents an object that deals with user inputs.
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;
    private StringBuilder parkResponse = new StringBuilder();

    /**
     * Constructs a Ui object that takes inputs from the user and prints output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui object.
     *
     * @param in InputStream object.
     * @param out PrintStream object.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the user input.
     *
     * @return User input as a String.
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Prints an output for the user to see.
     *
     * @param message String to be printed.
     */
    public void showToUser(String message) {
        out.println(message);
    }

    /**
     * Prints welcome message upon running the chatbot.
     */
    public void showWelcomeMessage() {
        showToUser("""
                Hello! I'm Park
                What can I do for you?""");;
    }

    public void setMarkResponse(String task) {
        setResponse("OK, I've marked this task as done:" + task);
    }

    public void setUnmarkResponse(String task) {
        setResponse("OK, I've marked this task as not done yet:" + task);
    }

    public void setAddResponse(String task, int taskListSize) {
        setResponse("Got it. I've added this task:" + task + "\n" + "Now you have " +
                taskListSize + " tasks in the list.");
    }

    public void setDeleteResponse(String task, int taskListSize) {
        setResponse("Noted. I've removed this task:" + task + "\n" + "Now you have " +
                taskListSize + " tasks in the list.");
    }

    public void setExitResponse() {
        setResponse("Bye. Hope to see you again soon!");
    }

    public void setFindResponse(StringBuilder matchingTasks) {
        if (matchingTasks.isEmpty()) {
            setResponse("There are no matching tasks.");
        } else {
            setResponse("Here are the matching tasks in your list:" + "\n" +
                    matchingTasks);
        }
    }

    public void setListResponse(StringBuilder taskList) {
        if (taskList.isEmpty()) {
            setResponse("There are no tasks in your list.");
        } else {
            setResponse("Here are the tasks in your list:" + "\n" + taskList);
        }
    }

    private void setResponse(String message) {
        parkResponse.append(message);
    }

    public String getResponse() {
        String response = parkResponse.toString();
        parkResponse.setLength(0);
        return response;
    }

    public void printResponse() {
        out.println(parkResponse);
        parkResponse.setLength(0);
    }
}
