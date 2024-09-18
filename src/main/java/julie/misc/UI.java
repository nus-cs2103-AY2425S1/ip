package julie.misc;

import java.util.List;

import julie.task.Task;

/**
 * A class that holds the User Interaction functions of the Chat Bot.
 * @author Colin Hia Qingxuan
 */
public class UI {
    /**
     * Public method that prints a string between two horizontal lines.
     *
     * @param s The string to be wrapped in horizontal lines.
     */
    public static void wrappedLinePrint(String s) {
        String horizontalLine = "_______________________________________";
        System.out.println(horizontalLine + "\n" + s + "\n" + horizontalLine);
    }

    /**
     * Public method that prints a list in the specified format.
     *
     * @param list The list to be printed.
     */
    public static <E> void printList(List<E> list) {
        String output = "Hi!! Here are the tasks you have left!\n";
        for (int i = 0; i < list.size(); i++) {
            output += String.format("%d. %s\n", i + 1, list.get(i));
        }
        output = String.format("%sYou have a total of %d tasks!!", output, list.size());
        wrappedLinePrint(output);
    }

    /**
     * Public method that returns the String representation of the list provided.
     *
     * @param <E> The generic type of the List.
     * @param list The list to be formatted.
     * @return The formatted string representation of the list.
     */
    public static <E> String getListString(List<E> list) {
        String output = "Hi!! Here are the tasks you have left!\n";
        for (int i = 0; i < list.size(); i++) {
            output += String.format("%d. %s\n", i + 1, list.get(i));
        }
        output = String.format("%sYou have a total of %d tasks!!", output, list.size());
        return output;
    }

    /**
     * Public method that returns the prompt when a task is added.
     *
     * @param t    The task that was added.
     * @param list The list of tasks to use.
     * @param <E>  The generic type parameter.
     * @return The prompt that indicates the task is added.
     */
    public static <E> String addedPrompt(Task t, List<E> list) {
        return String.format("Nice! I've added it to the list!\n"
                + "   %s\n %d tasks left!!" , t, list.size());
    }
}
