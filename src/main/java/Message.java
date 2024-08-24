import exceptions.MissingParametersException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Message {
    /**
     * Print a string in the dialog box.
     * @param text The content of the dialog box.
     */
    public static void print(String text) {
        printDialogBox(text);
    }

    /**
     * Print the message after adding a task.
     * @param task The task that was added.
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

    public static void printDue(String date, TodoList todoList) throws MissingParametersException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            printDialogBox(todoList.getDueAt(parsedDate).toString());
        } catch (DateTimeParseException e) {
            throw new MissingParametersException("deadline", "deadline return book /by 2/12/2019 1800");
        }
    }

    /**
     * Print the message after unmarking a task.
     * @param task The task to be unmarked.
     */
    public static void printUnmarked(Task task) {
        String content = "OK, I've marked this task as not done yet:\n  "  + task;
        printDialogBox(content);
    }

    /**
     * Print the message after deleting a task.
     * @param task The task that was deleted.
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
