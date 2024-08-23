import java.util.ArrayList;

public class Message {
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void hello() {
        System.out.println(HELLO);
    }

    public static void goodbye() {
        System.out.println(GOODBYE);
    }

    public static void echo(String input) {
        System.out.println(String.format("Added %s to your list!", input));
    }

    public static void displayList(Task[] list, int counter) {
        String output = "Here's your to-do list:";
        for (int i = 0; i < counter; i++) {
            output += String.format("\n%d. ", i + 1) + list[i];
        }
        System.out.println(output);
    }

    public static void mark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public static void unmark(Task task) {
        System.out.println("No problem! I've marked this task as not done yet:\n" + task);
    }
}
