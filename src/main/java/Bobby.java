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
                    try {
                        mark(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "unmark":
                    try {
                        unmark(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "delete":
                    try {
                        delete(Integer.valueOf(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println("Item cannot be accessed in list.");
                        System.out.println("Check the number of items again.");
                        horizontalLine(35);
                    }
                    break;
                case "todo":
                    try {
                        Todo td = Todo.createTodo(input);
                        addToList(td);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                case "deadline":
                    try {
                        Deadline dl = Deadline.createDeadline(input);
                        addToList(dl);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                case "event":
                    try {
                        Event event = Event.createEvent(input);
                        addToList(event);
                    } catch (EmptyDescriptionException e) {
                        horizontalLine(35);
                        System.out.println();
                        System.out.println(e.getMessage());
                        horizontalLine(35);
                    }
                    break;
                default:
                    horizontalLine(35);
                    System.out.println();
                    System.out.println("Please use a valid command: todo, deadline or event.");
                    horizontalLine(35);
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
        System.out.printf("Now you have %d tasks.%n", storage.size());
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

    public static void delete(int x) {
        horizontalLine(35);
        System.out.println();
        System.out.printf("I have removed this task: %s%n", storage.get(x).toString());
        storage.remove(x);
        System.out.printf("Now you have %d tasks left.%n", storage.size());
        horizontalLine(35);
    }
}
