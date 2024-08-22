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

            if (input.equalsIgnoreCase("bye")) {
                bye();
                System.out.println();
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                listOutTasks();
                continue;
            }

            String[] splitInput = input.split(" ");
            switch (splitInput[0].toLowerCase()) {
                case "mark":
                    mark(Integer.valueOf(splitInput[1]));
                    break;
                case "unmark":
                    unmark(Integer.valueOf(splitInput[1]));
                    break;
                case "todo":
                    // Split into 2 parts and then get the second part after todo
                    String todoInput = input.split(" ", 2)[1];
                    Todo td = new Todo(todoInput);
                    addToList(td);
                    break;
                case "deadline":
                    String deadlineInput = input.split(" ", 2)[1];
                    String deadlineDescription = deadlineInput.split(" /by ")[0];
                    String deadlineDay = deadlineInput.split(" /by ")[1];
                    Deadline dl = new Deadline(deadlineDescription, deadlineDay);
                    addToList(dl);
                    break;
                case "event":
                    String eventInput = input.split(" ", 2)[1];
                    String eventDescription = eventInput.split(" /", 3)[0];
                    String eventStart = eventInput.split(" /", 3)[1].substring(5);
                    String eventEnd = eventInput.split(" /", 3)[2].substring(3);
                    Event event = new Event(eventDescription, eventStart, eventEnd);
                    addToList(event);
                    break;
                default:
                    Task t = new Task(input);
                    addToList(t);
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
        System.out.println("Plus one more thing to do:");
        System.out.println(t.toString());
        System.out.println(String.format("Now you have %d tasks.", storage.size()));
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
