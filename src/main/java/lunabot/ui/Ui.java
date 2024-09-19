package lunabot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import lunabot.task.Task;

/**
 * Handles user interface operations for LunaBot.
 * The Ui class is responsible for managing the user interface interactions for LunaBot,
 * including reading user input and displaying messages to the console. It provides methods
 * for printing various types of messages such as welcome messages, error messages, task lists,
 * and updates on task status.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Welcome message to user when starting the app.
     * @return Welcome message.
     */
    public String printWelcome() {
        return """
                Hello! I'm LunaBot.\s
                \s
                 I can manage your tasks for you!\s
                 \s
                Type '?' for a list of available commands hehehe...""";
    }

    /**
     * Goodbye message to the user when closing the app.
     * @return Goodbye message.
     */
    public String printGoodbye() {
        return "Bye! Hope to see you again soon....";
    }

    /**
     * A message for when an error occurs loading tasks from the source file.
     * @return Error message.
     */
    public String printLoadingError() {
        return "Unable to load tasks from file.";
    }

    /**
     * A generic error message appended with the provided message.
     *
     * @param message The error message to display.
     * @return Error message.
     */
    public String printError(String message) {
        String error = "Error: ";
        return error + message;
    }

    /**
     * Displays the current list of tasks, if the taskList is empty, the user is informed.
     *
     * @param taskList The list of tasks to display.
     * @return List of tasks.
     */
    public String printTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks in your task list.";
        } else {
            String header = "Here are the tasks in your list: \n";
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                list.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            return header + list;
        }
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return Message to show that the specified task is marked as done.
     */
    public String printTaskMarked(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Informs the user that a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done.
     * @return Message to show that the specified task has been marked as not done.
     */
    public String printTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Informs the user that a task has been deleted and shows the updated number of tasks.
     *
     * @param task The task that has been deleted.
     * @param size The updated number of tasks in the list.
     * @return A message to show that the specified task has been deleted.
     */
    public String printTaskDeleted(Task task, int size) {
        String header = "Noted. I've removed this task:";
        String footer = "Now you have " + size + " tasks in the list.";
        return header + "\n" + task + "\n" + footer;
    }

    /**
     * Informs the user that a task has been added and shows the updated number of tasks.
     *
     * @param task The task that has been added.
     * @param size The updated number of tasks in the list.
     * @return A message to show that the task has been added.
     */
    public String printTaskAdded(Task task, int size) {
        String header = "Got it! I've added this task:";
        String footer = "Now you have " + size + " tasks in the list";
        return header + "\n" + task + "\n" + footer;
    }

    /**
     * Prints the list of matching tasks from the taskList.
     * @param matchingTasks ArrayList of matching tasks.
     * @return A list of matching tasks from the taskList.
     */
    public String printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            String header = " Here are the matching tasks in your list: \n";
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < matchingTasks.size(); i++) {
                list.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return header + list;
        }
    }

    /**
     * Prints a list of possible commands to the user.
     * @return List of commands
     */
    public String printHelpMessage() {
        String helpMessage = """
                Here is a list of available commands: \s
                \s
                Commands to add task: \s
                    t [description] : add a ToDo task with a description \s
                    d [description] /by [yyyy-MM-dd HH:mm] : add a Deadline task with a due date \s
                    e [description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]
                     : add an event task with start and end time \s
                \s
                Other commands: \s
                    ls : list out all tasks in your list \s
                    mark [number] : marks a task in your list as done \s
                    unmark [number] : marks a task in your list as not done \s
                    del [number] : delete a task from your list \s
                    find [keyword] : find tasks in your list with the matching keyword \s
                    ? : help command to list all possible commands \s
                    bye : exit the programme \s
                """;
        return helpMessage;
    }

}
