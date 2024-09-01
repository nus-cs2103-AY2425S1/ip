package cow.message;

import java.time.LocalDate;
import java.util.Scanner;

import cow.tasks.Task;
import cow.todolist.TodoList;

/** Class to handle all printing methods. **/
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private String text = "";

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a string in the dialog box.
     * @param text The content of the dialog box.
     */
    public void print(String text) {
        printDialogBox(text);
    }

    /**
     * Prints greeting message
     */
    public String printGreetings() {
        String content = " Hello! I'm COW\n"
                + " What can I do for you?";
        printDialogBox(content);
        return content;
    }

    /**
     * Prints error loading file.
     */
    public void printLoadingError() {
        String content = "Error loading file, proceed with empty task list";
        printDialogBox(content);
    }

    /**
     * Prints the message after adding a task.
     * @param task     The task that was added.
     * @param todoList The todoList the task was added to.
     */
    public void printAddedTask(Task task, TodoList todoList) {
        String content = "Got it. I've added this task:\n"
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Prints the message after marking a task.
     * @param task The task to be marked.
     */
    public void printMarked(Task task) {
        String content = "Nice! I've marked this task as done:\n  " + task;
        printDialogBox(content);
    }

    /**
     * Prints the message after detecting corrupted file and skipping the line.
     */
    public void printCorruptionDetected() {
        String content = "Corrupted command detected! Skipping command.";
        printDialogBox(content);
    }

    /**
     * Prints the message before exiting the program.
     */
    public void printGoodBye() {
        String content = "Bye. Hope to see you again soon!";
        printDialogBox(content);
    }

    /**
     * Prints the deadlines due at the date specified.
     * @param date The specific date to check.
     * @param todoList The list of todos.
     */
    public void printDue(LocalDate date, TodoList todoList) {
        printDialogBox(todoList.getDueAt(date).toString());
    }

    /**
     * Prints the message after unmarking a task.
     * @param task The task to be unmarked.
     */
    public void printUnmarked(Task task) {
        String content = "OK, I've marked this task as not done yet:\n  " + task;
        printDialogBox(content);
    }

    /**
     * Prints the message after deleting a task.
     * @param task The task that was deleted.
     * @param todoList The todoList the task was deleted from.
     */
    public void printDeletedTask(Task task, TodoList todoList) {
        String content = "Noted. I've removed this task:\n"
                + "  "
                + task + "\n"
                + todoList.count();
        printDialogBox(content);
    }

    /**
     * Prints the content and dialog box.
     * @param content The content to be printed in the dialog box.
     */
    private void printDialogBox(String content) {
        String line = "____________________________________________________________";
        System.out.println(line
                + "\n"
                + content + "-Mooooo"
                + "\n"
                + line
                + "\n");
        this.text = content;
    }

    /** Returns the latest command output **/
    public String getCurrentText() {
        return this.text;
    }

    /**
     * Prints todo list in a nice format.
     * @param list TodoList to print.
     */
    public void printList(TodoList list) {
        String content = "Here are the tasks in your list:\n"
                + list;
        printDialogBox(content);
    }

    /**
     * Prints matched todo list in a nice format.
     * @param list TodoList to print.
     */
    public void printFindTask(TodoList list) {
        String content = "Here are the matching tasks in your list:\n"
                + list;
        printDialogBox(content);
    }
}
