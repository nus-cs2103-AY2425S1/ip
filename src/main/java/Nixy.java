import java.util.Scanner;

public class Nixy {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static String[] tasks = new String[100];
    static int taskCount = 0;
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        String chatbotName = "Nixy";
        wrapPrintWithHorizontalLines("Hello! I'm " + chatbotName, "What can I do for you?");
        while (true) {
            String input = readInput();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                list();
                continue;
            }

            store(input);
        }
        exit();
    }

    /**
     * Read input from the user.
     */
    private static String readInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * Deprecated method to read input and echo it back.
     */
    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            exit();
            return;
        }
        wrapPrintWithHorizontalLines(input);
        echo();
    }

    /**
     * Read and store new task in the list of tasks.
     */
    private static void store(String taskName) {
        Task task = new Task(taskName);
        taskManager.addTask(task);
        wrapPrintWithHorizontalLines("added: " + taskName);
    }

    /**
     * List all tasks in the list of tasks.
     */
    private static void list() {
        indentPrint(HORIZONTAL_LINE);
        indentPrint("Here are the tasks in your list:");
        taskManager.listTasks();
        indentPrint(HORIZONTAL_LINE);
    }

    private static void exit() {
        wrapPrintWithHorizontalLines("Bye. Hope to see you again soon!");
    }

    private static void wrapPrintWithHorizontalLines(String... lines) {
        indentPrint(HORIZONTAL_LINE);
        indentPrint(lines);
        indentPrint(HORIZONTAL_LINE);
    }

    private static void indentPrint(String... lines) {
        for (String line : lines) {
            System.out.println("    " + line);
        }
    }

    private static void normalPrint(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
