package cstwooneohthree.glados.utils;

import java.util.ArrayList;

import cstwooneohthree.glados.tasks.Task;

/**
 * Ui class to handle printing to command line.
 *
 * @author jayjay19630
 */
public class Ui {

    /* Dividers and logo to display in CLI */
    private static final String HORIZONTAL_LINE = "\n"
            + "-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + " ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";

    /**
     * Prints greeting statement in terminal.
     */
    public static void greet() {
        System.out.println(
                HORIZONTAL_LINE
                + "\nHello, welcome to the Aperture Science computer-aided enrichment center! My name is:\n"
                + LOGO
                + "What task would you like me to perform today?\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints user input in echo command.
     *
     * @param input Input to be echoed.
     */
    public static void echo(String input) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: " + input.trim() + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints result after task has been added.
     *
     * @param task Task description to be added.
     * @param index Number of tasks left.
     */
    public static void add(String task, String index) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: I have added the following task to the list...\n"
                + "\n" + task + "\n"
                + "\nNow you have " + index + (Integer.parseInt(index) == 1 ? " task.\n" : " tasks.\n")
                + HORIZONTAL_LINE);
    }

    /**
     * Prints result after task has been deleted.
     *
     * @param task Task description to be deleted.
     * @param index Number of tasks left.
     */
    public static void delete(String task, String index) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: I have removed the following task from the list...\n"
                + "\n" + task + "\n"
                + "\nNow you have " + index + (Integer.parseInt(index) == 1 ? " task.\n" : " tasks.\n")
                + HORIZONTAL_LINE);
    }

    /**
     * Prints all elements inside the tasklist.
     *
     * @param taskList Task list to be printed.
     */
    public static void list(ArrayList<Task> taskList, boolean shouldFindMatching) {
        System.out.println(HORIZONTAL_LINE);
        String findMatchingString = shouldFindMatching ? " that matches input...\n" : "...\n";
        System.out.println("GLaDOS: Here is the list" + findMatchingString);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints task after it has been marked.
     *
     * @param input Description of task.
     */
    public static void mark(String input) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: I've marked this task as done...\n"
                + "\n" + input + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints task after it has been unmarked.
     *
     * @param input Description of task.
     */
    public static void unmark(String input) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: Oops, looks like this task is no longer done...\n"
                + "\n" + input + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints error caught by Glados.
     *
     * @param e Exception to be printed.
     */
    public static void error(Exception e) {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: " + e.getMessage() + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints appropriate statement during taskList initialization.
     *
     * @param str Statement to be printed.
     */
    public static void initialise(String str) {
        System.out.println("\n" + str);
    }

    /**
     * Prints exit statement after bye command.
     */
    public static void exit() {
        System.out.println(
                HORIZONTAL_LINE
                + "\nGLaDOS: Goodbye, user.\n"
                + HORIZONTAL_LINE);
    }
}
