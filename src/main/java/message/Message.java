package message;

import TodoList.TodoList;
import exceptions.MissingParametersException;
import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Message {
    private static Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Print a string in the dialog box.
     *
     * @param text The content of the dialog box.
     */
    public static void print(String text) {
        printDialogBox(text);
    }

    /**
     * Print greeting message
     */
    public static void printGreetings() {
        String content = " Hello! I'm COW\n"
                + " What can I do for you?";
        printDialogBox(content);
    }

    /**
     * Print error loading file
     */
    public static void printLoadingError() {
        String content = "Error loading file, proceed with empty task list";
        printDialogBox(content);
    }

    /**
     * Print the message after adding a task.
     *
     * @param task     The task that was added.
     * @param todoList The todoList the task was added to.
     */
    public static void printAddedTask(Task task, TodoList todoList) {
        String content = "Got it. I've added this task:\n"
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Print the message after marking a task.
     *
     * @param task The task to be marked.
     */
    public static void printMarked(Task task) {
        String content = "Nice! I've marked this task as done:\n  " + task;
        printDialogBox(content);
    }

    /**
     * Print the message after detecting corrupted file and skipping the line.
     */
    public static void printCorruptionDetected() {
        String content = "Corrupted command detected! Skipping command.";
        printDialogBox(content);
    }

    /**
     * Print the message before exiting the program.
     */
    public static void printGoodBye() {
        String content = "Bye. Hope to see you again soon!";
        printDialogBox(content);
    }

    /**
     * Prints the deadlines due at the date specified
     *
     * @param date     The specific date to check
     * @param todoList The list of todos
     */
    public static void printDue(LocalDate date, TodoList todoList) {
        printDialogBox(todoList.getDueAt(date).toString());
    }

    /**
     * Print the message after unmarking a task.
     *
     * @param task The task to be unmarked.
     */
    public static void printUnmarked(Task task) {
        String content = "OK, I've marked this task as not done yet:\n  " + task;
        printDialogBox(content);
    }

    /**
     * Print the message after deleting a task.
     *
     * @param task     The task that was deleted.
     * @param todoList The todoList the task was deleted from.
     */
    public static void printDeletedTask(Task task, TodoList todoList) {
        String content = "Noted. I've removed this task:\n"
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Print the content and dialog box.
     *
     * @param content The content to be printed in the dialog box.
     */
    private static void printDialogBox(String content) {
        String line = "____________________________________________________________";
        System.out.println(line
                + "\n"
                + content + "-Mooooo"
                + "\n"
                + line
                + "\n");
    }
}
