import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bocchi {
    /**
     * The name of this bot.
     */
    static private final String name = "Bocchi";

    /**
     * The list to store all input items.
     */
    private final List<String> items = new ArrayList<>();

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
        System.out.println("Oh no you are leaving.. It was a great time talking to you ::>_<::");
    }

    /**
     * Greets the user.
     */
    private void greet() {
        System.out.println("Hi! I'm " + name + "! Nice to see you!");
        System.out.println("Wha..what can I do for you today? o(*//▽//*)q");
    }

    /**
     * Reads a command.
     *
     * @param scanner The scanner to use.
     * @return The command.
     */
    private String readCommand(Scanner scanner) {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    /**
     * Prints all items in the item list.
     */
    private void printList() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    /**
     * Adds an item to the list.
     * @param item the item to be added.
     */
    private void addItem(String item) {
        items.add(item);
        System.out.println("added: " + item);
    }


    /**
     * Starts a conversation.
     * Commands available:
     * - list: list out all items stored;
     * - bye: end the conversation;
     * - other inputs: add the input to the item list.
     */
    public void start() {
        printSeparator();
        greet();
        printSeparator();
        // try-with-resources code optimisation done by Intellij
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = readCommand(scanner);
                if (command.equals("bye")) {
                    printSeparator();
                    exit();
                    printSeparator();
                    return;
                } else if (command.equals("list")) {
                    printSeparator();
                    printList();
                    printSeparator();
                } else {
                    printSeparator();
                    addItem(command);
                    printSeparator();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Bocchi().start();
    }
}
