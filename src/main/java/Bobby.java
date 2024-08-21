import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bobby {
    private static List<Task> storage = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            System.out.println();
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                bye();
                System.out.println();
                break;
            }

            if (input.equals("list")) {
                listOutTasks();
            } else {
                if (input.contains(" ")) {
                    String[] splitString = input.split(" ");
                    if (splitString[0].equals("mark")) {
                        mark(Integer.valueOf(splitString[1]));
                    } else if (splitString[0].equals("unmark")) {
                        unmark(Integer.valueOf(splitString[1]));
                    } else {
                        Task t = new Task(input);
                        addToList(t);
                    }
                } else {
                    Task t = new Task(input);
                    addToList(t);
                }
            }
        }
    }

    // Make a horizontal line of x dashes
    public static void horizontalLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
    }

    public static void greet() {
        horizontalLine(35);
        System.out.println();
        System.out.println("Hello! I'm Bobby");
        System.out.println("What can I do for you?");
        horizontalLine(35);
    }

    public static void bye() {
        horizontalLine(35);
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine(35);
    }

    public static void repeat(String s) {
        horizontalLine(35);
        System.out.println();
        System.out.println(s);
        horizontalLine(35);
    }

    public static void addToList(Task t) {
        Bobby.storage.add(t);
        horizontalLine(35);
        System.out.println();
        System.out.println("added: " + t.getDescription());
        horizontalLine(35);
    }

    public static void listOutTasks() {
        horizontalLine(35);
        int n = Bobby.storage.size();
        System.out.println();
        for (int i = 0; i < n; i++) {
            int number = i + 1;
            System.out.println(number + ". " + Bobby.storage.get(i));
        }
        horizontalLine(35);
    }

    public static void mark(int x) {
        Task t = storage.get(x);
        t.indComplete();
        horizontalLine(35);
        System.out.println();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(t);
        horizontalLine(35);
    }

    public static void unmark(int x) {
        Task t = storage.get(x);
        t.indIncomplete();
        horizontalLine(35);
        System.out.println();
        System.out.println("OK, I have marked this task as not done yet:");
        System.out.println(t);
        horizontalLine(35);
    }
}
