package talkie.components;

import java.util.Scanner;

import talkie.exception.TalkieException;
import talkie.task.Task;
import talkie.task.TaskList;

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
        WELCOME_MESSAGE("Hello! I'm Talkie, your friendly ChatBot.\n"
                + "What can I do for you?\n"),

        BYE_MESSAGE("Bye. Hope to see you again soon!\n");

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

    // Scanner for input
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
    public String welcomeMessage() {
        return MessageType.WELCOME_MESSAGE.getMessage();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String byeMessage() {
        return MessageType.BYE_MESSAGE.getMessage();
    }

    /**
     * Displays a message indicating that the date/time format is incorrect.
     */
    public String wrongDateTimeFormatMessage() {
        return "Please enter the time in the format of <yyyy-MM-dd HHmm>!\n";
    }

    /**
     * Displays a message confirming that a task has been added to the task list.
     *
     * @param t The task that was added.
     * @param taskListSize The current size of the task list after addition.
     */
    public String addMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String finalMessage = "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n";

        return finalMessage;
    }

    /**
     * Displays a message confirming that a task has been deleted from the task list.
     *
     * @param t The task that was deleted.
     * @param taskListSize The current size of the task list after deletion.
     */
    public String deleteMessage(Task t, int taskListSize) {
        String taskWord = (taskListSize > 1) ? "tasks" : "task";
        String doneMessage = "Noted! I've removed this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskListSize + " " + taskWord + " in the list.\n";

        return doneMessage;
    }

    /**
     * Displays a list of all tasks in the task list.
     *
     * @param tasks The list of tasks to display.
     */
    public String listTasks(TaskList tasks) {
        String listMessage = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            String description = (i) + ". " + currTask + "\n";
            listMessage += description;
        }

        String finalMessage = "Here are the tasks in your list:\n" + listMessage;

        return finalMessage;
    }

    /**
     * Displays a list of all tasks in the task list after sorting.
     *
     * @param tasks The list of sorted tasks to display.
     */
    public String listSortedTasks(TaskList tasks) {
        String listMessage = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            String description = (i) + ". " + currTask + "\n";
            listMessage += description;
        }

        String finalMessage = "Your task list after sorting:\n" + listMessage;

        return finalMessage;
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String markMessage(Task task) {
        String doneMessage = "Nice! I've marked this task as done:\n"
                + " " + task + "\n";

        return doneMessage;
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String unMarkMessage(Task task) {
        String undoneMessage = "OK, I've marked this task as not done yet:\n"
                + " " + task + "\n";

        return undoneMessage;
    }

    /**
     * Displays an error message when a {@link TalkieException} is encountered.
     *
     * @param e The exception to be displayed.
     */
    public void showTalkieException(TalkieException e) {
        System.out.println(String.format("%s\n", e));
    }

    /**
     * Searches for tasks containing the specified keyword and displays the matching tasks.
     * <p>
     * This method iterates through the provided {@code TaskList}, checks each task for the presence of the keyword,
     * and collects the tasks that match. It then displays the matching tasks or appropriate messages if no tasks
     * are found or if the task list is empty.
     * </p>
     *
     * @param tasks The {@code TaskList} containing all tasks to search through.
     * @param keyword The keyword to search for in the task descriptions.
     */
    public String findTasks(TaskList tasks, String keyword) {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list! \n";
        }

        TaskList searchedList = new TaskList();
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.containsWord(keyword)) {
                searchedList.addTask(currTask);
            }
        }

        if (searchedList.isEmpty()) {
            return "There are no tasks found in your list! \n";
        }

        StringBuilder searchedListMessage = new StringBuilder();
        for (int i = 1; i <= searchedList.size(); i++) {
            Task searchedTask = searchedList.getTask(i);
            String description = (i) + ". " + searchedTask + "\n";
            searchedListMessage.append(description);
        }

        String finalMessage = "Here are the matching tasks in your list:\n"
                + searchedListMessage;

        return finalMessage;
    }

}
