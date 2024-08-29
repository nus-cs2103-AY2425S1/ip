import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alexer {
    private static final String GOODBYE = "Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!";
    private static final String BREAK = "____________________________________________________________";

    public static final String NAME = "Alexer";

    private final Scanner scanner = new Scanner(System.in);

    private final List<Task> tasks = new ArrayList<>();

    public void printGoodbye() {
        System.out.println(BREAK);
        System.out.println(GOODBYE);
        System.out.println(BREAK);
    }

    public void printTasks() {
        System.out.println(BREAK);

        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("%d: %s\n", i + 1, tasks.get(i));
        }

        System.out.println(BREAK);
    }

    public void greetUser() {
        System.out.println(BREAK);
        System.out.printf("Hello from %s, what can I do for you today?\n", NAME);
        System.out.println(BREAK);
    }

    public void addTask(String taskDescription) {
        // create new task
        Task task = new Task(taskDescription);
        tasks.add(task);

        System.out.println(BREAK);
        System.out.format("Added: %s\n", task);
        System.out.println(BREAK);
    }

    public void promptLoop() {
        String input = scanner.nextLine();
        String command = input.split(" ")[0];

        switch (command) {
        case "bye":
            printGoodbye();
            break;
        case "list":
            printTasks();
            promptLoop();
            break;
        default:
            addTask(input);
            promptLoop();
            break;
        }
    }

    public static void main(String[] args) {
        String logo = """
                     .     .                           \s
                    /|     |     ___  _  .-   ___  .___\s
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   \s""";
        System.out.println(BREAK);
        System.out.println(logo);

        // create an instance of our bot
        Alexer alexer = new Alexer();
        alexer.greetUser();
        alexer.promptLoop();
    }
}
