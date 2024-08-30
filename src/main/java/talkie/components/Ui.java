package talkie.components;

import talkie.exception.TalkieException;
import talkie.task.Task;
import talkie.task.TaskList;

import java.util.Scanner;

/**
 * Handles user interactions and displays messages to the user.
 * <p>
 * The {@code Ui} class manages the display of various types of messages to the user,
 * such as welcome messages, task addition, deletion, and error handling.
 * </p>
 */
public class Ui {

    /**
     * Enum representing different types of messages used by the {@code Ui} class.
     */
    public enum MessageType {
        HORIZONTAL_LINE("-------------------------------------------------------------------"),
        WELCOME_MESSAGE(HORIZONTAL_LINE.message + "\n"
                + "Hello! I'm talkie.Talkie, your friendly ChatBot.\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE.message + "\n"),
        BYE_MESSAGE(HORIZONTAL_LINE.message + "\n"
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE.message + "\n");

        private final String message;

        MessageType(String message) {
            this.message = message;
        }

        /**
         * Gets the message associated with the {@code MessageType}.
         *
         * @return The message string.
         */
        public String getMessage() {
            return this.message;
        }
    }

    private Scanner input = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return this.input.nextLine();
    }

    /**
     * Closes the {@code Scanner} used for user input.
     */
    public void closeInput() {
        this.input.close();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcomeMessage() {
        System.out.println(MessageType.WELCOME_MESSAGE.getMessage());
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void byeMessage() {
        System.out.println(MessageType.BYE_MESSAGE.getMessage());
    }

    /**
     * Displays a message indicating that the date/time format is incorrect.
     */
    public void wrongDateTimeFormatMessage() {
        System.out.println(MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Please enter the time in the format of <yyyy-MM-dd HHmm>!\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n");
    }

    /**
     * Displays a message confirming that a task has been added to the task list.
     *
     * @param t The task that was added.
     * @param taskListSize The current size of the task list after addition.
     */
    public void addMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String finalMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(finalMessage);
    }

    /**
     * Displays a message confirming that a task has been deleted from the task list.
     *
     * @param t The task that was deleted.
     * @param taskListSize The current size of the task list after deletion.
     */
    public void deleteMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String doneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Noted! I've removed this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(doneMessage);
    }

    /**
     * Displays a list of all tasks in the task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void listTasks(TaskList tasks) {
        String listMessage = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            String description = (i) + ". " + currTask + "\n";
            listMessage += description;
        }

        String finalMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Here are the tasks in your list:\n"
                +  listMessage
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(finalMessage);
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markMessage(Task task) {
        String doneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "Nice! I've marked this task as done:\n"
                + " " + task + "\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(doneMessage);
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void unMarkMessage(Task task) {
        String undoneMessage = MessageType.HORIZONTAL_LINE.getMessage() + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + " " + task + "\n"
                + MessageType.HORIZONTAL_LINE.getMessage() + "\n";
        System.out.println(undoneMessage);
    }

    /**
     * Displays an error message when a {@link TalkieException} is encountered.
     *
     * @param e The exception to be displayed.
     */
    public void showTalkieException(TalkieException e) {
        System.out.println(String.format("%s\n", e));
    }
}
