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
        System.out.println("No...no problem! This is your task list! (/▽＼)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printSeparator();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    private void task(Task task) {
        printSeparator();
        tasks.add(task);
        System.out.println("Only if I can be as diligent as you... (っ °Д °;)っ An..anyway, task added!");
        System.out.println(task);
        printSeparator();
    }

    /**
     * Mark the i-th task (1-indexed) as done.
     *
     * @param index The index of the task to be marked as done.
     */
    private void mark(int index) {
        index--;
        if (index >= tasks.size() || index < 0) {
            printSeparator();
            System.out.println("Sorry but ... erm maybe it is better to double check the index you entered? Cause it seems to be out of bounds. ＞﹏＜");
            printSeparator();
            return;
        }

        Task task = tasks.get(index);
        task.markAsDone();

        printSeparator();
        System.out.println("I have marked the task as done! You are doing such a good job! (*/ω＼*)");
        System.out.println(task);
        printSeparator();
    }

    /**
     * Mark the i-th task (1-indexed) as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    private void unmark(int index) {
        index--;
        if (index >= tasks.size() || index < 0) {
            printSeparator();
            System.out.println("Sorry but ... erm maybe it is better to double check the index you entered? Cause it seems to be out of bounds. ＞﹏＜");
            printSeparator();
            return;
        }

        Task task = tasks.get(index);
        task.markAsUnDone();

        printSeparator();
        System.out.println("I have marked the task as not done. You will do it better next time! (*/ω＼*)");
        System.out.println(task);
        printSeparator();
    }

    /**
     * Starts a conversation.
     * Commands available:
     * - bye: ends the conversation;
     * - list: lists out all tasks;
     * - mark [index]: mark the task in the specified index as done;
     * - unmark [index]: mark the task in the specified index as not done;
     * - todo [description]: adds a new todo with the specified description;
     * - ddl/deadline [description] /by [dueDateTime]: adds a new deadline with the specified description and due date/time;
     * - event [description] /from [fromDateTime] /to [toDateTime]: adds a new event with the specified description,
     * start date/time and end date/time.
     */
    public void start() {
        greet();
        // try-with-resources code optimisation done by IntelliJ
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Command command = readCommand(scanner);
                // optimized from if statements to switch by IntelliJ
                switch (command.getName()) {
                    case "bye" -> {
                        exit();
                        return;
                    }
                    case "list" -> list();
                    case "mark" -> mark(Integer.parseInt(command.getParam()));
                    case "unmark" -> unmark(Integer.parseInt(command.getParam()));
                    case "todo" -> task(new Todo(command.getName()));
                    case "ddl", "deadline" -> task(new Deadline(command.getName(), command.getKeywordParam("by")));
                    case "event" -> task(new Event(
                            command.getName(),
                            command.getKeywordParam("from"),
                            command.getKeywordParam("to")
                    ));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Bocchi().start();
    }
}
