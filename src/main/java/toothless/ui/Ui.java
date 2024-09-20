package toothless.ui;

import java.util.Scanner;

import toothless.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public static String welcome() {
        return "Welcome to the dragon's den!\n"
                + "Greetings, Dragon Rider!\n\n"
                + "I'm Toothless, your friendly dragon companion.\n"
                + "What adventure shall we embark on today?\n\n"
                + "Enter 'help' to see the list of commands.\n\n";

    }

    /**
     * Prints the greeting message.
     *
     * @return The greeting message.
     */
    public static String greeting() {
        return "Hello! I'm Toothless\nHow can I help today dragon rider?\n\n";
    }

    /**
     * Prints the goodbye message.
     *
     * @return The goodbye message.
     */
    public static String bye() {
        return "Until next time, dragon rider!\n"
                + "Toothless the Night Fury, signing off.\n"
                + "See you soon!\n\n"
                + "(The input is disabled, restart to chat with Toothless again!)\n\n";
    }

    /**
     * Prints the message when a task is added.
     *
     * @param task The task that is added.
     * @param size The size of the task list.
     * @return The message when a task is added.
     */
    public String addTaskMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        assert size >= 0 : "Size should not be negative";
        return "Your task\n\t\t"
                + task + "\nadded to the quest board!\n\n"
                + "Now there is " + size + " quests in your quest board.\n\n";
    }

    /**
     * Prints the message when a task is deleted.
     *
     * @return The message when an unknown command is entered.
     */
    public String unknownCommand() {
        return "I'm sorry, I do not understand what you mean.\n"
                + "Please enter a valid command.\n\n"
                + "Enter 'help' to see the list of commands.\n\n";
    }

    /**
     * Prints the message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     * @return The message when a task is marked as done.
     */
    public String markDoneMessage(Task task) {
        assert task != null : "Task should not be null";
        return "Good job! You had completed this quest!\n"
                + task + "\n\n";
    }

    /**
     * Prints the message when a task is marked as undone.
     *
     * @param task The task that is marked as undone.
     * @return The message when a task is marked as undone.
     */
    public String markUndoneMessage(Task task) {
        assert task != null : "Task should not be null";
        return "Oops! Quest is back in play!\n"
                + task + "\n\n";
    }

    /**
     * Print the message that guide user to enter the correct format of the command.
     *
     * @return The help message.
     */
    public String helpMessage() {
        return "Here are the list of commands you can use:\n"
                + "Category A: Adding tasks\n"
                + "1. Todo task: todo <description>\n"
                + "2. Deadline task: deadline <description> /by <deadline>\n"
                + "3. Event Task: event <description> /from <start time> /to <end time>\n\n"
                + "Category B: Managing tasks\n"
                + "4. List all tasks: list\n"
                + "5. Mark a task as done: mark <index>e\n"
                + "6. Marks a task as undone: unmark <index>\n"
                + "7. Delete a task based on index: delete <index>\n"
                + "8. Find the task with the keyword: find <keyword>\n\n"
                + "Category C: Other commands\n"
                + "9. Shows the list of commands: help\n"
                + "10. Exit the program: bye\n\n";
    }
}
