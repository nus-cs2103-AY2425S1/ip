import java.util.List;

/**
 * A class that holds commonly used functions within the Chat Bot
 * @author Colin Hia Qingxuan
 */
public class CommonFunctions {
    /**
     * Public method that prints a string between two horizontal lines.
     * @param s The string to be wrapped in horizontal lines.
     */
    public static void wrappedLinePrint(String s) {
        String HORIZONTAL_LINE = "_______________________________________";
        System.out.println(HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Public method that prints a list in the specified format.
     * @param list The list to be printed.
     */
    public static <E> void printList(List<E> list) {
        String output = "Hi!! Here are the tasks you have left!\n";
        for (int i = 0; i < list.size(); i++) {
            output += String.format("%d. %s\n", i + 1, list.get(i));
        }
        wrappedLinePrint(output);
    }

    /**
     * Public method that returns the prompt when a task is added
     * @param t The task that was added.
     * @param list The list of tasks to use.
     * @param <E> The generic type parameter.
     */
    public static <E> void addedPrompt(Task t, List<E> list) {
        CommonFunctions.wrappedLinePrint(String.format("Nice! I've added it to the list!\n" +
                "   %s\n %d tasks left!!" , t, list.size()));
    }

}
