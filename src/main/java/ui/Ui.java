package ui;

import task.Task;

/**
 * The {@code Ui} class handles all interactions with the user. It provides methods to display
 * various messages related to tasks and application events.
 */
public class Ui {

    /**
     * Sends a message to the user by printing it to the console with a "[Bob]" prefix.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        System.out.println("[Bob] " + message);
    }

    /**
     * Displays the introductory messages when the application starts.
     */
    public void introBobUi() {
        sendMessage("Hello! I'm Bob!");
        sendMessage("What can I do for you?");
    }

    /**
     * Displays the exit message when the application is about to close.
     */
    public void exitBobUi() {
        sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param t The task that was added.
     * @param size The current size of the task list after adding the task.
     */
    public void addTaskUi(Task t, int size) {
        sendMessage("Got it. I've added this task:");
        sendMessage(t.toString());
        sendMessage("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that was marked as done.
     */
    public void markTaskUi(Task t) {
        sendMessage("Nice! I've marked this task as done:");
        sendMessage(t.toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     */
    public void unmarkTaskUi(Task t) {
        sendMessage("OK, I've marked this task as not done yet:");
        sendMessage(t.toString());
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param t The task that was removed.
     * @param size The current size of the task list after removing the task.
     */
    public void deleteTaskUi(Task t, int size) {
        sendMessage("Noted. I've removed this task:");
        sendMessage(t.toString());
        sendMessage("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating that an error occurred while trying to save tasks.
     */
    public void savingErrorUi() {
        sendMessage("An error has occurred when trying to save.");
    }

    /**
     * Displays an error message based on the provided exception.
     *
     * @param e The exception whose message will be displayed.
     */
    public void errorMessageUi(Exception e) {
        sendMessage(e.getMessage());
    }
}
