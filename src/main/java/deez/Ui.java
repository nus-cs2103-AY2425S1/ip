package deez;

import java.util.ArrayList;

/**
 * The Ui class contains utility methods for user interface.
 */
public class Ui {
    /**
     * Print a separator line to console.
     */
    private static void printSeperator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Say messages with a separator before and after the messages.
     *
     * @param msgs The messages to say. It can be an empty array.
     */
    public static void say(String... msgs) {
        printSeperator();
        for (String msg : msgs) {
            System.out.println("> " + msg);
        }
        printSeperator();
    }

    /**
     * Print the elements of a given list to console.
     *
     * @param arrayList The list to print. If it's empty, print "<No items in list>" instead.
     */
    public static void printList(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            say("<No items in list>");
            return;
        }
        // Print list
        int cnt = 1;
        printSeperator();
        for (Object o : arrayList) {
            System.out.println(cnt + ". " + o.toString());
            cnt += 1;
        }
        printSeperator();
    }
}
