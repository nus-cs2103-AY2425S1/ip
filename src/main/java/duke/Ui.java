package duke;

import java.util.ArrayList;
import java.util.Scanner;
import duke.tasks.Task;

/**
 * Represents the user interface, responsible reading and writing messages.
 */
public class Ui {
    /**
     * Constructor for a user interface.
     */
    public Ui() {
    }

    /**
     * Formats and writes a message to standard output.
     *
     * @param msg The message to format.
     */
    public void write(String msg) {
        System.out.print("___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n");
    }

    /**
     * Reads a message from standard input.
     *
     * @return The message read.
     */
    public String read() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Writes the greeting message.
     */
    public void greet() {
        this.write(" Hello! I'm Bob\n"
                + " What can I do for you?");
    }

    /**
     * Writes the exit message.
     */
    public void exit() {
        this.write(" Bye. Hope to see you again soon!");
    }

    /**
     * Writes the response message for a add command.
     *
     * @param task The added task.
     * @param numTasks The number of tasks after adding the task.
     */
    public void add(Task task, int numTasks) {
        this.write(String.format("""
                 Got it. I've added this task:
                    %s
                 Now you have %d tasks in the list.""",
                task.toString(), numTasks));
    }

    /**
     * Writes the response message for a delete command.
     *
     * @param task The deleted task.
     * @param numTasks The number of tasks after deleting the task.
     */
    public void delete(Task task, int numTasks) {
        this.write(String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.
               """, task.toString(), numTasks));
    }

    /**
     * Writes the response message for a mark command.
     *
     * @param task The task marked.
     * @param isCompleted Whether the task was marked completed or not completed.
     */
    public void mark(Task task, boolean isCompleted) {
        if (isCompleted) {
            this.write(" Nice! I've marked this task as done:\n   " + task);
        } else {
            this.write(" OK, I've marked this task as not done yet:\n   " + task);
        }
    }

    /**
     * Writes response message for a list command.
     */
    public void list(ArrayList<Task> tasks) {
        StringBuilder listMsg = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            listMsg
                    .append("\n ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
        }
        this.write(listMsg.toString());
    }
}