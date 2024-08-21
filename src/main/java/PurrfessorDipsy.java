import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurrfessorDipsy {
    private static final Task[] taskTable = new Task[100];
    private static int taskTableRowCount = 0;
    private static boolean isRunning = true; // to allow for a more graceful exit

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();
        while (isRunning) {
            String userInput = inputScanner.nextLine();
            doAction(userInput);
        }
        inputScanner.close();
    }

    private static void printTerminalLine() {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
    }

    private static void printWelcomeMessage() {
        printTerminalLine();
        System.out.println("Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\nHow can I purrvide assistance? " +
                "Purrhaps I can lend a paw!");
        printTerminalLine();
    }

    private static void printExitMessage() {
        System.out.println("Fur-well friend, stay paw-sitive!");
        printTerminalLine();
    }

    private static void doAction(String userInput) {
        String trimmedInput = userInput.trim();

        // Patterns can be moved to class field to reduce overhead of initialization.
        Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");
        Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");
        Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");
        Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
        Matcher markMatcher = MARK_PATTERN.matcher(trimmedInput);
        Matcher todoMatcher = TODO_PATTERN.matcher(trimmedInput);
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(trimmedInput);
        Matcher eventMatcher = EVENT_PATTERN.matcher(trimmedInput);

        if (trimmedInput.startsWith("mark") || trimmedInput.startsWith("unmark")) {
            if (markMatcher.matches()) {
                String command = markMatcher.group(1);
                int index = Integer.parseInt(markMatcher.group(2));

                if (index >= taskTableRowCount || index < 1) {
                    System.out.println("Error: index given lies outside of the table's valid range.");
                }
                if (command.equals("mark")) {
                    markTaskAsDone(index);
                } else if (command.equals("unmark")) {
                    markTaskAsUndone(index);
                }
            } else {
                printTerminalLine();
                System.out.println("Error: 'mark' command requires an index to be given.");
                System.out.println("Usage: mark <index> or unmark <index>");
                printTerminalLine();
            }
        } else if (trimmedInput.startsWith("todo")){
            if (todoMatcher.matches()) {
                String description = todoMatcher.group(1);
                saveToMemory(new ToDo(description));
            } else {
                printTerminalLine();
                System.out.println("Error: 'todo' command requires a description.");
                System.out.println("Usage: todo <description>");
                printTerminalLine();
            }
        } else if (trimmedInput.startsWith("deadline")) {
            if (deadlineMatcher.matches()) {
                String description = deadlineMatcher.group(1);
                String by = deadlineMatcher.group(2);
                saveToMemory(new Deadline(description, by));
            } else {
                printTerminalLine();
                System.out.println("Error: 'deadline' command requires a 'by' date.");
                System.out.println("Usage: deadline <description> /by <day/date/time>");
                printTerminalLine();
            }
        } else if (trimmedInput.startsWith("event")) {
            if (eventMatcher.matches()) {
                String description = eventMatcher.group(1);
                String start = eventMatcher.group(2);
                String end = eventMatcher.group(3);
                saveToMemory(new Event(description, start, end));
            } else {
                printTerminalLine();
                System.out.println("Error: 'event' command requires a description, a '/from' time, and a '/to' time.");
                System.out.println("Usage: event <description> /from <day/date/time> /to <day/date/time>");
                printTerminalLine();
            }
        } else {
            switch (trimmedInput) {
                case "":
                    // do nothing
                    break;
                case "bye":
                    exitProgram();
                    System.exit(0);
                    break;
                case "list":
                    printMemory();
                    break;
                default:
                    echoUserInput(userInput);
                    saveToMemory(new Task(userInput));
                    break;
            }
        }
    }

    private static void echoUserInput(String userInput) {
        printTerminalLine();
        System.out.println(userInput);
        printTerminalLine();
    }

    private static void saveToMemory(Task t) {
        taskTable[taskTableRowCount] = t;
        taskTableRowCount++;
        printTerminalLine();
        System.out.println("Got it! I've added this task:");
        System.out.println(t.toString());
        System.out.println("You now have " + taskTableRowCount + " tasks in your list.");
        printTerminalLine();
    }

    private static void markTaskAsDone(int index) {
        Task currTask = taskTable[index - 1]; // table is 0-indexed
        currTask.markAsDone();

        printTerminalLine();
        System.out.println("Meow! I’ve scratched this task off the list!");
        System.out.println(currTask.toString());
        printTerminalLine();
    }

    private static void markTaskAsUndone(int index) {
        Task currTask = taskTable[index - 1]; // table is 0-indexed
        currTask.markAsUndone();

        printTerminalLine();
        System.out.println("Mrrreow! I’ve batted this task back onto the list.");
        System.out.println(currTask.toString());
        printTerminalLine();
    }

    private static void printMemory() {
        printTerminalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskTableRowCount; i++) {
            int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
            System.out.println(printedIndex + "." + taskTable[i].toString());
        }
        printTerminalLine();
    }

    private static void exitProgram() {
        printExitMessage();
        isRunning = false; // Set the loop control flag to false to exit the loop gracefully.
    }
}
