import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List<Task> items = new ArrayList<>();

    private static void printItems() {
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    private static String[] safeSplit(String input, int noArgs) {
        String[] split = input.split(" ");
        if (split.length < noArgs) {
            throw new IllegalArgumentException(String.format("Expected %d arguments. Found %d.", noArgs, split.length));
        }
        return split;
    }

    public static void main(String[] args) {
        // Hello
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");

        // Main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printItems();
            } else if (input.startsWith("mark")) {
                String[] split = safeSplit(input, 2);
                Task task = items.get(Integer.parseInt(split[1]) - 1);
                task.markDone();
                System.out.printf("Marked this task as done:\n%s\n", task);
            } else if (input.startsWith("unmark")) {
                String[] split = safeSplit(input, 2);
                Task task = items.get(Integer.parseInt(split[1]) - 1);
                task.unmarkDone();
                System.out.printf("Marked this task as not yet done:\n%s\n", task);
            } else {
                System.out.println(input);
                items.add(new Task(input));
            }
        }

        // Goodbye
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
