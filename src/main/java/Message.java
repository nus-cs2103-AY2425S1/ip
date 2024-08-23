import java.util.ArrayList;

public class Message {
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void Hello() {
        System.out.println(HELLO);
    }

    public static void Goodbye() {
        System.out.println(GOODBYE);
    }

    public static void Echo(String input) {
        System.out.println(String.format("Added %s to your list!", input));
    }

    public static void DisplayList(String[] list, int counter) {
        String output = "";
        if (counter > 0) {
            output += "1. " + list[0];
            for (int i = 1; i < counter; i++) {
                output += String.format("\n%d. ", i + 1) + list[i];
            }
        }
        System.out.println(output);
    }
}
