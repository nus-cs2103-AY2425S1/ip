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

    public static void echo(Task task, int total) {
        System.out.println(String.format("Got it. I've added this task:\n%s\nto your list!"
                        + "\nYou now have " + total + " tasks.", task));
    }

    public static void displayList(ArrayList<Task> list, int counter) {
        String output = "Here's your to-do list:";
        for (int i = 0; i < counter; i++) {
            output += String.format("\n%d. ", i + 1) + list.get(i);
        }
        System.out.println(output);
    }

    public static void mark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public static void unmark(Task task) {
        System.out.println("No problem! I've marked this task as not done yet:\n" + task);
    }

    public static void delete(Task task, int total) {
        System.out.println("Got it! I've removed this task:\n" + task +
                "\nYou now have " + total + " tasks in total!");
    }

    public static void error(TalkaBotException e) {
        System.out.println(e.getMessage());
    }

}
