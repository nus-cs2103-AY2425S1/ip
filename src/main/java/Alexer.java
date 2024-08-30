import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            System.out.format("\t%d: %s\n", i + 1, tasks.get(i));
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

    public void deleteTask(List<String> arguments) {
        int index = Integer.parseInt(arguments.get(0)) - 1;
        Task task = tasks.get(index);
        tasks.remove(index);

        System.out.println(BREAK);
        System.out.println("Don't want to see that task anymore? I got you!");
        System.out.format("\t%s\n", task);
        System.out.format("\nYou have %d tasks remaining.\n", tasks.size());
        System.out.println(BREAK);
    }

    public void addTodo(List<String> arguments) {
        String description = String.join(" ", arguments);
        if (description.isEmpty()) {
            System.out.println(BREAK);
            System.out.println("Oh-no! You forgot to include a description for your task!");
            System.out.println(BREAK);
            return;
        }

        Todo todo = new Todo(description);
        tasks.add(todo);

        System.out.println(BREAK);
        System.out.format("Sure! I’ve added the todo to your list:\n\n\t%s\n", todo);
        System.out.format("\nYou have %d tasks now.\n", tasks.size());
        System.out.println(BREAK);
    }

    public void addDeadline(List<String> arguments) {
        int keywordIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("/by")) keywordIndex = i;
        }

        String description = arguments.stream().limit(keywordIndex)
                .collect(Collectors.joining(" "));
        String by = arguments.stream().skip(keywordIndex + 1).collect(Collectors.joining(" "));

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        System.out.println(BREAK);
        System.out.format("No problems! I’ve added the task to your list:\n\n\t%s\n", deadline);
        System.out.format("\nYou have %d tasks now.\n", tasks.size());
        System.out.println(BREAK);
    }

    public void addEvent(List<String> arguments) {
        int fromIndex = 0;
        int toIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("/from")) fromIndex = i;
            if (arguments.get(i).equals("/to")) toIndex = i;
        }

        String description = arguments.stream().limit(fromIndex)
                .collect(Collectors.joining(" "));
        String from = arguments.stream().limit(toIndex).skip(fromIndex + 1)
                .collect(Collectors.joining(" "));
        String to = arguments.stream().skip(toIndex + 1).collect(Collectors.joining(" "));

        Event event = new Event(description, from, to);
        tasks.add(event);

        System.out.println(BREAK);
        System.out.format("Noted! I’ve added a new event to your tasks:\n\n\t%s\n", event);
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
        case "todo":
            addTodo(arguments);
            promptLoop();
            break;
        case "deadline":
            addDeadline(arguments);
            promptLoop();
            break;
        case "event":
            addEvent(arguments);
            promptLoop();
            break;
        case "delete":
            deleteTask(arguments);
            promptLoop();
            break;
        default:
            System.out.println(BREAK);
            System.out.println("Uh-oh, I did not understand what you are trying to do.");
            System.out.println(BREAK);
            promptLoop();
            break;
        }
    }

    public static void main(String[] args) {
        String logo = """
                     .     .                           
                    /|     |     ___  _  .-   ___  .___
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   """;
        System.out.println(BREAK);
        System.out.println(logo);

        // create an instance of our bot
        Alexer alexer = new Alexer();
        alexer.greetUser();
        alexer.promptLoop();
    }
}
