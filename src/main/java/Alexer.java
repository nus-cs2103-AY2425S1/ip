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
        System.out.println("Sure thing! Here is your task list:\n");

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
        System.out.format("Got it! I’ve added the task to your list:\n\n\t%s\n", task);
        System.out.format("\nYou have %d tasks now.\n", tasks.size());
        System.out.println(BREAK);
    }

    public void markTaskDone(int index) {
        // assume input here is valid, we will handle exceptions later
        tasks.get(index - 1).markAsDone();

        System.out.println(BREAK);
        System.out.println("Great job completing the task! Keep up the great work!");
        System.out.format("\t%s\n", tasks.get(index - 1));
        System.out.println(BREAK);
    }

    public void unmarkTaskDone(int index) {
        // assume input here is valid, we will handle exceptions later
        tasks.get(index - 1).unmarkDone();

        System.out.println(BREAK);
        System.out.println("Alright, it seems you are not done with that yet.");
        System.out.println("I have unmarked it for you.");
        System.out.format("\t%s\n", tasks.get(index - 1));
        System.out.println(BREAK);
    }

    public void promptLoop() {
        String input = scanner.nextLine();

        List<String> arguments = new ArrayList<>(List.of(input.split(" ")));
        String command = arguments.remove(0);

        switch (command) {
        case "bye":
            printGoodbye();
            break;
        case "list":
            printTasks();
            promptLoop();
            break;
        case "mark":
            int index = Integer.parseInt(arguments.get(0));
            markTaskDone(index);
            promptLoop();
            break;
        case "unmark":
            int taskNum = Integer.parseInt(arguments.get(0));
            unmarkTaskDone(taskNum);
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
