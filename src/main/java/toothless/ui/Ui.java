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
                + "What adventure shall we embark on today?\n\n";

    }

    /**
     * Prints the greeting message.
     */
    public static String greeting() {
        return "Hello! I'm Toothless\nHow can I help today dragon rider?\n\n";
    }

    /**
     * Prints the goodbye message.
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
     */
    public String addTaskMessage(Task task, int size) {
        return "Your task\n\t\t"
                + task + "\nadded to the quest board!\n\n"
                + "Now there is " + size + " quests in your quest board.\n\n";
    }

    /**
     * Prints the message when an unknown command is entered.
     */
    public String unknownCommand() {
        return "I'm sorry, I do not understand what you mean.\n"
                + "Please enter a valid command.\n\n";
    }

    /**
     * Prints the message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     */
    public String markDoneMessage(Task task) {
        return "Good job! You had completed this quest!\n"
                + task + "\n\n";
    }

    /**
     * Prints the message when a task is marked as undone.
     *
     * @param task The task that is marked as undone.
     */
    public String markUndoneMessage(Task task) {
        return "Oops! Quest is back in play!\n"
                + task + "\n\n";
    }
}
