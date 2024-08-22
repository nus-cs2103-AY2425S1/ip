import java.util.Scanner;

public class Nixy {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static String[] tasks = new String[100];
    static int taskCount = 0;
    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        String chatbotName = "Nixy";
        PrintUtility.wrapPrintWithHorizontalLines("Hello! I'm " + chatbotName, "What can I do for you?");
        while (true) {
            String input = readInput();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                list();
                continue;
            }
            if (input.startsWith("mark")) {
                final int MARK_PREFIX_LENGTH = 5;
                String taskNumberStr = input.substring(MARK_PREFIX_LENGTH);
                int taskNumber = Integer.parseInt(taskNumberStr);
                taskManager.markTaskAsDone(taskNumber);
                continue;
            }
            if (input.startsWith("unmark")) {
                final int UNMARK_PREFIX_LENGTH = 7;
                String taskNumberStr = input.substring(UNMARK_PREFIX_LENGTH);
                int taskNumber = Integer.parseInt(taskNumberStr);
                taskManager.markTaskAsUndone(taskNumber);
                continue;
            }

            if (input.startsWith("todo")) {
                final int TODO_PREFIX_LENGTH = 5;
                String taskName = input.substring(TODO_PREFIX_LENGTH);
                store(taskName);
                continue;
            }
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

    /** Get command and arguments from input string. */
    private static String[] splitCommand(String input) {
        return input.split(" ");
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
        PrintUtility.wrapPrintWithHorizontalLines(input);
        echo();
    }

    /**
     * Read and store new task in the list of tasks.
     */
    private static void store(String taskName) {
        Task task = new TodoTask(taskName);
        taskManager.addTask(task);
        PrintUtility.wrapPrintWithHorizontalLines("Got it. I've added this task: " + taskName,
            "Now you have " + taskManager.getTaskCount() + " tasks in the list.");
    }

    /**
     * List all tasks in the list of tasks.
     */
    private static void list() {
        PrintUtility.indentPrint(HORIZONTAL_LINE);
        PrintUtility.indentPrint("Here are the tasks in your list:");
        taskManager.listTasks();
        PrintUtility.indentPrint(HORIZONTAL_LINE);
    }

    private static void exit() {
        PrintUtility.wrapPrintWithHorizontalLines("Bye. Hope to see you again soon!");
    }

}
