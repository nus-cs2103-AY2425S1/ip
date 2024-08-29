package kotori.Ui;

import kotori.taskList.TaskList;

/**
 * This class represent some actions of printing
 * */

public class Ui {

    /**
     * prints a line.
     * */

    public static void printLine() {
        System.out.println("    ___________________________________________");
    }

    /**
     * prints a message.
     *
     * @param input the message.
     * */

    public static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    /**
     * prints some messages.
     *
     * @param inputs the messages.
     * */

    public static void printMessages(String... inputs) {
        printLine();
        for (String s : inputs) {
            System.out.println("    " + s);
        }
        printLine();
    }

    /**
     * prints a list.
     *
     * @param list the list.
     * */

    public static void printList(TaskList list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i).toString()));
        }
        printLine();
    }

    /**
     * Prints a list with the given messages.
     *
     * @param list The list to be printed.
     * @param inputs the messages to be included.
     * */

    public static void printListWithMessages(TaskList list, String... inputs) {
        printLine();
        for (String s : inputs){
            System.out.println("    " + s);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i).toString()));
        }
        printLine();
    }

    /**
     * prints a greeting.
     * */

    public static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    /**
     * prints the exit message.
     */

    public static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}
