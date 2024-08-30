package main;

import exception.CommandFoundButInvalidException;
import task.Task;

import java.util.List;

public class Ui {
    private StringBuilder message;
    private String display;
    public Ui() {
        this.message = new StringBuilder();
    }

    public void showWelcome() {
        message.append("Welcome to main.Hyperion!");
        System.out.println(this.message.toString());
    }
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the contents of the {@code List} of {@code Tasks}
     *
     * @param allTasks the {@code List} of {@code Tasks} that stores all the
     *                 undeleted Tasks
     * @throws CommandFoundButInvalidException if the description is not empty
     */
    public void showList(List<Task> allTasks) throws CommandFoundButInvalidException {
        String initialValues = new ListAll("", allTasks).message();
        System.out.println(initialValues);
    }

    /**
     * Returns a message upon successful deletion of a {@code Task}
     *
     * @param t the {@code Task} instance that is being deleted
     * @param size the current size of the {@code List} of {@code Task}
     * @return the message which tells the task that is deleted and the number
     *         of Tasks in the List of Tasks
     */
    public String deleteMessage(Task t, int size) {
        String s1 = "Noted. I've removed this task:";
        String s2 = String.format("Now you have %d tasks in the list", size);
        return s1 + "\n" + " " + t.toString() + "\n" + s2;
    }

    /**
     * Returns a message upon successful addition of a {@code Task}
     *
     * @param t the {@code Task} instance that is being added
     * @param size the current size of the {@code List} of {@code Task}
     * @return the message which tells the task that is added and the number
     *         of Tasks in the List of Tasks
     */
    public String addedMessage(Task t, int size) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", size);
        return str1 + t.toString() + "\n" + str2;
    }

    /**
     * Returns a message upon successful marking of a {@code Task}
     *
     * @param t the {@code Task} instance that is being marked
     * @return the message that tells which {@code Task} is being marked
     */
    public String markedMessage(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Returns a message upon successful unmarking of a {@code Task}
     *
     * @param t the {@code Task} instance that is being unmarked
     * @return the message that tells which {@code Task} is being unmarks
     */
    public String unmarkedMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
