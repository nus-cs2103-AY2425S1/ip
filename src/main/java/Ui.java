import java.util.ArrayList;

public class Ui {
    static String line = "____________________________________________________________";

    public static void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void greet() {
        String message = "Hello! I'm Fred\n" +
                "What can I do for you?";
        say(message);
    }

    public static void sayFarewell() {
        String message = "Bye. Hope to see you again soon!";
        say(message);
    }

    static void printTaskList(ArrayList<Task> taskList) {
        int index = 1;
        System.out.println(line);
        for (Task task : taskList) {
            System.out.println(String.format("%s.%s", index++, task));
        }
        System.out.println(line);
    }
}
