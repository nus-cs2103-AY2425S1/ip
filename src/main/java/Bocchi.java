import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bocchi {
    /**
     * The name of this bot.
     */
    static private final String name = "Bocchi";

    /**
     * The list to store all tasks.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Constructor.
     */
    public Bocchi() {

    }

    /**
     * Prints a greeting with an ASCII art.
     */
    private void printLogo() {
        String logo = """
                 ____                 _     _\s
                |  _ \\               | |   (_)
                | |_) | ___   ___ ___| |__  _\s
                |  _ < / _ \\ / __/ __| '_ \\| |
                | |_) | (_) | (_| (__| | | | |
                |____/ \\___/ \\___\\___|_| |_|_|
                                             \s
                                             \s""";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a horizontal line.
     */
    private void printSeparator() {
        System.out.println("_____________________________________________________________");
    }

    /**
     * Ends the conversation.
     */
    private void exit() {
        printSeparator();
        System.out.println("Oh no you are leaving.. It was a great time talking to you ::>_<::");
        printSeparator();
    }

    /**
     * Greets the user.
     */
    private void greet() {
        printSeparator();
        System.out.println("Hi! I'm " + name + "! Nice to see you!");
        System.out.println("Wha..what can I do for you today? o(*//▽//*)q");
        printSeparator();
    }

    /**
     * Reads a command.
     *
     * @param scanner The scanner to use.
     * @return The command.
     */
    private Command readCommand(Scanner scanner) {
        System.out.print(">> ");
        return new Command(scanner.nextLine());
    }

    /**
     * Prints all items in the item list.
     */
    private void list() {
        printSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added.
     */
    private void todo(Task task) {
        printSeparator();
        tasks.add(task);
        System.out.println("added: " + task);
        printSeparator();
    }


    /**
     * Starts a conversation.
     * Commands available:
     * - list: lists out all tasks;
     * - bye: ends the conversation;
     * - mark [index]: mark the task in the specified index as done;
     * - unmark [index]: mark the task in the specified index as not done;
     * - other inputs: adds a new task with the specified description.
     */
    public void start() {
        greet();
        // try-with-resources code optimisation done by Intellij
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Command command = readCommand(scanner);
                if (command.getName().equals("bye")) {
                    exit();
                    return;
                } else if (command.getName().equals("list")) {
                    list();
                } else {
                    todo(new Task(command.getParamString()));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Bocchi().start();
    }
}
