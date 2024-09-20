package zbot.ui;

import java.util.Scanner;

import zbot.task.Task;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Returns the intro message.
     *
     * @return The intro message.
     */
    public String intro() {
        return "Hello! I'm ZBot\n" + "What can I do for you?";
    }

    /**
     * Prints the exit message.
     */
    public void outro() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Returns message when the task is added.
     *
     * @param task
     * @param size
     */
    public String generateAddTaskMsg(Task task, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("  " + task + "\n");
        response.append("Now you have " + size + " tasks in the list.\n");
        return response.toString();
    }

    /**
     * Returns message when the task is deleted.
     *
     * @param task
     * @param size
     */
    public String generateDeleteTaskMsg(Task task, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:\n");
        response.append("  " + task + "\n");
        response.append("Now you have " + size + " tasks in the list.\n");
        return response.toString();
    }

    /**
     * Returns message when the task is marked as done.
     *
     * @param task
     */
    public String generateMarkTaskMsg(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as done:\n");
        response.append("  " + task + "\n");
        return response.toString();
    }

    /**
     * Returns message when the task is marked as not done.
     *
     * @param task
     */
    public String generateUnmarkTaskMsg(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("OK, I've marked this task as not done yet:\n");
        response.append("  " + task + "\n");
        return response.toString();
    }

    /**
     * Prints the error message when the no saved file is found.
     */
    public void printLoadingError() {
        System.out.println("No saved data found. Starting with an empty task list...\n");
    }

    /**
     * Reads the input from the user.
     */
    public String readUserInput() {
        return sc.nextLine();
    }

    /**
     * Returns message when a note is added to a task.
     */
    public String generateAddNoteMsg(Task task) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added a note to this task:\n");
        response.append("  " + task + "\n");
        return response.toString();
    }

}
