package barry.util;

import barry.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    private String lastMessage = "";
    /**
     * Constructs a Ui object that initialises the scanner for reading the user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command entered by the user.
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the horizontal line used by the chatbot.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the loading error used by the chatbot.
     */
    public void showLoadingError() {
        lastMessage = "loading error... :(";
        System.out.println(lastMessage);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showBye() {
        lastMessage = "bzzz~ bye! Hope to see you again soon!";
        System.out.println(lastMessage);
    }

    /**
     * Displays the list of all tasks to the user.
     *
     * @param allTasks The TaskList of all tasks of the user.
     */
    public void showList(TaskList allTasks) {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < allTasks.size(); i++) {
            builder.append("\n ").append(i + 1).append(".").append(allTasks.getTask(i));
        }
        lastMessage = builder.toString();
        System.out.println(lastMessage);
    }

    /**
     * Displays a message of the task that was marked as done.
     * @param task The task to be marked as done.
     */
    public void showMark(Task task) {
        lastMessage = "Nice! I've marked this task as done:\n  " + task;
        System.out.println(lastMessage);
    }

    /**
     * Displays a message of the task that was marked as undone.
     *
     * @param task the task to be marked as undone.
     */
    public void showUnmark(Task task) {
        lastMessage = " OK, I've marked this task as not done yet:\n " + task;
        System.out.println(lastMessage);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task to be added to the task list.
     * @param size The current size of the task list.
     */
    public void showAddTask(Task task, int size) {
        lastMessage = "bzzz OK! I've added this task:\n  " + task
                      + "\nNow you have " + size + " tasks in the list. ";
        System.out.println(lastMessage);
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param removed The task that was removed from the task list.
     * @param size The current size of the task list.
     */
    public void showDeleteTask(Task removed, int size) {
        lastMessage = " Noted. I've removed this task:\n   " + removed
                        + "\nNow you have " + size + " tasks in the list. ";
        System.out.println(lastMessage);
    }

    /**
     * Displays an error message.
     *
     * @param msg The error message to be displayed.
     */
    public void showError(String msg) {
        lastMessage = msg;
        System.out.println(lastMessage);
    }

    /**
     * Displays the list of matching tasks.
     *
     * @param matches The list of matching tasks.
     */
    public void showFindResults(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            lastMessage = "Hmm... I can't find any matching tasks bzz... ";
        } else {
            StringBuilder builder = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                builder.append("\n ").append(i + 1).append(".").append(matches.get(i));
            }
            lastMessage = builder.toString();
        }
        System.out.println(lastMessage);
    }
    /**
     * Displays the list of available commands and their descriptions.
     */
    public String showHelp() {
        String helpMessage = "Here are the commands you can use:" +
                "\n  bye - Exits the program" +
                "\n  list - Lists all tasks" +
                "\n  todo <description> - Adds a todo task" +
                "\n  deadline <description> /by <yyyy-mm-dd> - Adds a task with a deadline" +
                "\n  event <description> /from <start date/time> /to <start date/time> - Adds an event " +
                "\n   with a start and end time" +
                "\n  mark <index> - Marks a task as done" +
                "\n  unmark <index> - Marks a task as not done" +
                "\n  delete <index> - Deletes a task" +
                "\n  find <keyword> - Finds tasks by keyword" +
                "\n  help - Shows this help page";
        return helpMessage;
    }
    public String getLastMessage() {
        return lastMessage;
    }
    public void showMessage(String message) {
        lastMessage = message;
        System.out.println(message);
    }

}
