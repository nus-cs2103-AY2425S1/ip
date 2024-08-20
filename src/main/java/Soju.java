import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Soju {
    private static final List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """
                          _____                   _______                   _____                    _____         \s
                         /\\    \\                 /::\\    \\                 /\\    \\                  /\\    \\        \s
                        /::\\    \\               /::::\\    \\               /::\\    \\                /::\\____\\       \s
                       /::::\\    \\             /::::::\\    \\              \\:::\\    \\              /:::/    /       \s
                      /::::::\\    \\           /::::::::\\    \\              \\:::\\    \\            /:::/    /        \s
                     /:::/\\:::\\    \\         /:::/~~\\:::\\    \\              \\:::\\    \\          /:::/    /         \s
                    /:::/__\\:::\\    \\       /:::/    \\:::\\    \\              \\:::\\    \\        /:::/    /          \s
                    \\:::\\   \\:::\\    \\     /:::/    / \\:::\\    \\             /::::\\    \\      /:::/    /           \s
                  ___\\:::\\   \\:::\\    \\   /:::/____/   \\:::\\____\\   _____   /::::::\\    \\    /:::/    /      _____ \s
                 /\\   \\:::\\   \\:::\\    \\ |:::|    |     |:::|    | /\\    \\ /:::/\\:::\\    \\  /:::/____/      /\\    \\\s
                /::\\   \\:::\\   \\:::\\____\\|:::|____|     |:::|    |/::\\    /:::/  \\:::\\____\\|:::|    /      /::\\____\\
                \\:::\\   \\:::\\   \\::/    / \\:::\\    \\   /:::/    / \\:::\\  /:::/    \\::/    /|:::|____\\     /:::/    /
                 \\:::\\   \\:::\\   \\/____/   \\:::\\    \\ /:::/    /   \\:::\\/:::/    / \\/____/  \\:::\\    \\   /:::/    /\s
                  \\:::\\   \\:::\\    \\        \\:::\\    /:::/    /     \\::::::/    /            \\:::\\    \\ /:::/    / \s
                   \\:::\\   \\:::\\____\\        \\:::\\__/:::/    /       \\::::/    /              \\:::\\    /:::/    /  \s
                    \\:::\\  /:::/    /         \\::::::::/    /         \\::/    /                \\:::\\__/:::/    /   \s
                     \\:::\\/:::/    /           \\::::::/    /           \\/____/                  \\::::::::/    /    \s
                      \\::::::/    /             \\::::/    /                                      \\::::::/    /     \s
                       \\::::/    /               \\::/____/                                        \\::::/    /      \s
                        \\::/    /                 ~~                                               \\::/____/       \s
                         \\/____/                                                                    ~~             \s
                                                                                                                   \s""";
        System.out.println("Hello from\n" + logo);

        runWithHorizontalLine(Soju::greet);
        echo();
    }

    public static void greet() {
        System.out.println("-------------------------------------");
        String greetingMessage = "Hello! I'm Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void runWithHorizontalLine(Runnable function) {
        function.run();
        System.out.println("-------------------------------------");
    }
    public static void runWithHorizontalLine(String message) {
        System.out.println(message);
        System.out.println("-------------------------------------");
    }
    public static void runWithHorizontalLine() {
        System.out.println("-------------------------------------");
    }
    public static void addToList(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + description);
        runWithHorizontalLine();
    }
    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task) // Add index to each task
                .forEach(System.out::println); // Print each task
        runWithHorizontalLine();
    }
    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            runWithHorizontalLine();
            if (userInput.equals("bye")) {
                runWithHorizontalLine(Soju::exit);
                break;
            } else if (userInput.equals("list")) {
                displayList();
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
                runWithHorizontalLine();
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
                runWithHorizontalLine();
            } else {
                addToList(userInput);
            }
        }
        scanner.close();
    }
}
