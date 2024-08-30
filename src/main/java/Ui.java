import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    String line = "____________________________________________________________";

    public Ui() {
        scanner = new Scanner(System.in);
    }
    void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    void greet() {
        String message = "Hello! I'm Fred\n" +
                "What can I do for you?";
        say(message);
    }

    void sayFarewell() {
        String message = "Bye. Hope to see you again soon!";
        say(message);
    }

    void printTaskList(ArrayList<Task> taskList) {
        int index = 1;
        System.out.println(line);
        for (Task task : taskList) {
            System.out.println(String.format("%s.%s", index++, task));
        }
        System.out.println(line);
    }

    String getInput() {
        return scanner.nextLine();
    }
}
