package popi.ui;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import popi.task.Task;
import popi.task.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner scanner;
    private String response;

    /**
     * Constructor for the Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.response = "";
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        setResponse("Welcome! I'm Popi and I'm here to help you to manage your tasks.\n" +
                "If you need guidance, just type 'help' to see a list of commands!");
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        setResponse("Goodbye! May your day be as bright and joyful as a sunny river!");
    }

    /**
     * Shows the error message.
     *
     * @param message Error message to be shown.
     */
    public void showError(String message) {
        setResponse(message);
    }

    /**
     * Shows the task message.
     *
     * @param action Action to be shown.
     * @param task Task to be shown.
     * @param tasks List of tasks to be calculated.
     */
    public void showTaskMessage(String action, Task task, TaskList tasks) {
        setResponse(String.format("Lookie here! I've %s this task:\n  %s\nThere is now %d task(s) in the list.",
                action, task, tasks.getAllTasks().size()));
    }

    /**
     * Shows the added task and number of tasks in the task list.
     *
     * @param task Task added to be shown.
     * @param tasks List of tasks to be calculated.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        showTaskMessage("added", task, tasks);
    }

    /**
     * Shows the deleted task and number of tasks in the task list.
     *
     * @param task Task deleted to be shown.
     * @param tasks List of tasks to be calculated.
     */
    public void showTaskDeleted(Task task, TaskList tasks) {
        showTaskMessage("removed", task, tasks);
    }

    /**
     * Shows the new marked task.
     *
     * @param task Task to be marked.
     */
    public void showTaskMarked(Task task) {
        setResponse(String.format("Oooh, look at you go! We just marked this task as DONE! " +
                "You're amazing!\n  %s", task));
    }

    /**
     * Shows the new unmarked task.
     *
     * @param task Task to be unmarked.
     */
    public void showTaskUnmarked(Task task) {
        setResponse(String.format("Aww, it's okay, no worries! We'll unmark it for now. " +
                "Just take your time!\n  %s", task));
    }

    /**
     * Shows the list of tasks found.
     *
     * @param tasks List of tasks to be shown.
     */
    public void showMatchingTasks(TaskList tasks) {
        setResponse(buildTaskList("Sniff sniff... Aha! I found it! This task is a perfect match!", tasks));
    }

    /**
     * Sets the response to be shown to the user.
     *
     * @return Response to be shown to the user.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the response to be shown to the user.
     *
     * @param response Response to be shown to the user.
     */
    private void setResponse(String response) {
        this.response = response;
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks List of tasks to be shown.
     */
    public void showTasks(TaskList tasks) {
        setResponse(buildTaskList("Look at all these tasks! I've got them all listed out for you!", tasks));
    }

    private String buildTaskList(String header, TaskList tasks) {
        return IntStream.range(0, tasks.getAllTasks().size())
            .mapToObj(i -> (i + 1) + ". " + tasks.getAllTasks().get(i))
            .collect(Collectors.joining("\n", header + "\n", ""));
    }

    /**
     * Shows the help message.
     */
    public void showHelp() {
        setResponse("Otterly excited to help! Here are the commands you can use:\n\n"
                + "1. todo <description> - Add a todo task!\n"
                + "2. deadline <description> /by <date> <time> - Add a task with a deadline!\n"
                + "3. event <description> /from <date> <time> /to <date> <time> - Add an event with a start and end time!\n"
                + "4. list - I'll show you all your tasks!\n"
                + "5. mark <task number> - Yay! Mark a task as done!\n"
                + "6. unmark <task number> - Oops! Unmark a task if it's not done yet!\n"
                + "7. delete <task number> - Remove a task from your list. Bye-bye task!\n"
                + "8. find <keyword> - Find tasks that match a keyword!\n"
                + "9. help - I'll show this list again whenever you need it!\n"
                + "10. bye - Say goodbye to me, and I'll wave my paw as you leave!");
    }
}
