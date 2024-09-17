package kotori.ui;

import kotori.tasklist.TaskList;

/**
 * This class represent some actions of printing
 * */

public class Ui {

    /**
     * prints some messages.
     *
     * @param inputs the messages.
     * */

    public static String printMessages(String... inputs) {
        String result = "";
        for (String s : inputs) {
            result += s + "\n";
        }
        return result;
    }

    /**
     * prints a list.
     *
     * @param list the list.
     * */

    public static String printList(TaskList list) {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("%s. %s\n", i + 1, list.get(i).toString());
        }
        return result;
    }

    /**
     * Prints a list with the given messages.
     *
     * @param list The list to be printed infront.
     * @param inputs the messages to be included.
     * */

    public static String printListWithMessages(TaskList list, String... inputs) {
        return printMessages(inputs) + printList(list);
    }

    /**
     * prints a greeting.
     * */

    public static String printGreeting() {

        return "Hello! I'm Kotori.\nWhat can I do for you?\n";
    }

    /**
     * prints the exit message.
     */

    public static String printExit() {

        return "Bye! Hope to see you again soon.";
    }
}
