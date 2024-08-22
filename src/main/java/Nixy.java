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
                final int MARK_PREFIX_LENGTH = 4;
                String taskNumberStr = input.substring(MARK_PREFIX_LENGTH).trim();
                if (taskNumberStr.isEmpty()) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The task number to mark as done cannot be empty.");
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(taskNumberStr);
                    taskManager.markTaskAsDone(taskNumber);
                } catch (NumberFormatException e) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The task number to mark as done must be an integer.");
                } catch (TaskNotExistException e) {
                    PrintUtility.wrapPrintWithHorizontalLines(e.getMessage());
                }
                continue;
            }
            if (input.startsWith("unmark")) {
                final int UNMARK_PREFIX_LENGTH = 6;
                String taskNumberStr = input.substring(UNMARK_PREFIX_LENGTH).trim();
                if (taskNumberStr.isEmpty()) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The task number to mark as undone cannot be empty.");
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(taskNumberStr);
                    taskManager.markTaskAsUndone(taskNumber);
                } catch (NumberFormatException e) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The task number to mark as undone must be an integer.");
                } catch (TaskNotExistException e) {
                    PrintUtility.wrapPrintWithHorizontalLines(e.getMessage());
                }
                continue;
            }
            if (input.startsWith("todo")) {
                final int TODO_PREFIX_LENGTH = 4;
                String taskName = input.substring(TODO_PREFIX_LENGTH).trim();
                if (taskName.isEmpty()) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The description of a todo cannot be empty.");
                    continue;
                }
                store(new TodoTask(taskName));
                continue;
            }
            if (input.startsWith("deadline")) {
                final int DEADLINE_PREFIX_LENGTH = 8;
                String taskMeta = input.substring(DEADLINE_PREFIX_LENGTH).trim();

                // check if taskMeta is empty or does not contain /by
                if (taskMeta.isEmpty()) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The description of a deadline cannot be empty.");
                    continue;
                }

                // index 0 is task name, index 1 is deadline
                try {
                    String[] taskParts = taskMeta.split(" /by ");
                    store(new DeadlineTask(taskParts[0], taskParts[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The deadline of a deadline task must be specified.");
                }

                continue;
            }
            if (input.startsWith("event")) {
                final int EVENT_PREFIX_LENGTH = 5;
                String taskMeta = input.substring(EVENT_PREFIX_LENGTH).trim();

                // check if taskMeta is empty or does not contain /from and /to
                if (taskMeta.isEmpty()) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The description of an event cannot be empty.");
                    continue;
                }

                // index 0 is task name, index 1 is start time with end time
                try {
                    String[] taskParts = taskMeta.split(" /from ");
                    String taskName = taskParts[0];
                    // index 0 is start time, index 1 is end time
                    taskParts = taskParts[1].split(" /to ");
                    store(new EventTask(taskName, taskParts[0], taskParts[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    PrintUtility.wrapPrintWithHorizontalLines("BLAHH!!! The start and end time of an event must be specified.");
                }
                continue;
            }

            PrintUtility.wrapPrintWithHorizontalLines("HEY YOU ARE TYPING WEIRD THINGS! I don't understand.");
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
     * Store new task in the list of tasks.
     */
    private static void store(Task task) {
        taskManager.addTask(task);
        PrintUtility.wrapPrintWithHorizontalLines("Got it. I've added this task: \n      " + task,
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
