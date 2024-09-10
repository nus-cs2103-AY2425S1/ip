package cstwooneohthree.glados.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cstwooneohthree.glados.enums.UiType;
import cstwooneohthree.glados.tasks.Task;

/**
 * Ui class to handle printing to command line or returning strings in GUI.
 *
 * @author jayjay19630
 */
public class Ui {

    /* Dividers and logo to display in CLI */
    private static final String HORIZONTAL_LINE = "\n"
            + "-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + "\n ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";
    private UiType uiType;

    public Ui(UiType uiType) {

        assert uiType == UiType.COMMAND_LINE_INTERFACE || uiType == UiType.GRAPHICAL_USER_INTERFACE;

        this.uiType = uiType;
    }

    /**
     * Prints or returns greeting statement.
     */
    public String greet() {
        String message = "\nHello, welcome to the Aperture Science computer-aided enrichment center! My name is: " 
                         + LOGO + "What task would you like me to perform today?" + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "Hello, welcome to the Aperture Science computer-aided enrichment center!"
                    + "My name is GLaDOS. What task would you like me to perform today?";
        }
    }

    /**
     * Prints or returns user input in echo command.
     *
     * @param input Input to be echoed.
     */
    public String echo(String input) {
        String message = "\nGLaDOS: " + input.trim() + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return input.trim();
        }
    }

    /**
     * Prints or returns result after task has been added.
     *
     * @param task Task description to be added.
     * @param index Number of tasks left.
     */
    public String add(String task, String index) {
        String message = "\nGLaDOS: I have added the following task to the list...\n" 
                        + task + "\n" 
                        + "Now you have " + index + (Integer.parseInt(index) == 1 ? " task." : " tasks.")
                        + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "I have added the following task to the list: " 
                   + task + " Now you have " + index + (Integer.parseInt(index) == 1 ? " task." : " tasks.");
        }
    }

    /**
     * Prints or returns result after task has been deleted.
     *
     * @param task Task description to be deleted.
     * @param index Number of tasks left.
     */
    public String delete(String task, String index) {
        String message = "\nGLaDOS: I have removed the following task from the list...\n"
                        + task + "\n"
                        + "Now you have " + index + (Integer.parseInt(index) == 1 ? " task." : " tasks.")
                        + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "I have removed the following task from the list: " 
                   + task + " Now you have " + index + (Integer.parseInt(index) == 1 ? " task." : " tasks.");
        }
    }

    /**
     * Prints or returns all elements inside the tasklist.
     *
     * @param taskList Task list to be printed.
     * @param isFilteredList Whether or not list has been filtered after find command.
     */
    public String list(ArrayList<Task> taskList, boolean isFilteredList) {
        String findMatchingString = isFilteredList ? " that matches input..." : "...";
        StringBuilder message = new StringBuilder("\nGLaDOS: Here is the list" + findMatchingString + "\n");

        message.append(
                IntStream.range(0, taskList.size())
                        .mapToObj(i -> (i + 1) + ". " + taskList.get(i).toString() + "\n")
                        .collect(Collectors.joining())
        );

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message.toString() + HORIZONTAL_LINE);
            return null;
        } else {
            return message.toString()
                    .replace("\n", " ")
                    .replace("GLaDOS: ", "");
        }
    }

    /**
     * Prints or returns task after it has been marked.
     *
     * @param input Description of task.
     */
    public String mark(String input) {
        String message = "\nGLaDOS: I've marked this task as done...\n" + input + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "I've marked this task as done: " + input;
        }
    }

    /**
     * Prints or returns task after it has been unmarked.
     *
     * @param input Description of task.
     */
    public String unmark(String input) {
        String message = "\nGLaDOS: Oops, looks like this task is no longer done...\n" + input + "\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "Oops, looks like this task is no longer done: " + input;
        }
    }

    /**
     * Prints error caught by Glados.
     *
     * @param e Exception to be printed.
     */
    public String printError(Exception e) {
        String message = "\nGLaDOS: " + e.getMessage() + "\n";
        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return message.replace("\n", "").replace("GLaDOS: ", "");
        }
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
     * Prints or returns exit statement after bye command.
     */
    public String exit() {
        String message = "\nGLaDOS: Goodbye, user.\n";

        if (uiType == UiType.COMMAND_LINE_INTERFACE) {
            System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
            return null;
        } else {
            return "Goodbye, user.";
        }
    }
}
