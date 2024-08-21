import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurrfessorDipsy {
    private static final Task[] taskTable = new Task[100];
    private static int taskTableRowCount = 0;
    private static boolean isRunning = true; // to allow for a more graceful exit

    private static final Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");

    private enum Command {
        MARK, UNMARK, TODO, DEADLINE, EVENT, LIST, BYE, UNKNOWN
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();
        while (isRunning) {
            try {
                String userInput = inputScanner.nextLine().trim();
                processCommand(userInput);
            } catch (PurrfessorDipsyException e) {
                printErrorMessage(e.getMessage());
            }
        }
        inputScanner.close();
    }

    private static Command parseCommand(String userInput) {
        if (userInput.startsWith("mark")) return Command.MARK;
        if (userInput.startsWith("unmark")) return Command.UNMARK;
        if (userInput.startsWith("todo")) return Command.TODO;
        if (userInput.startsWith("deadline")) return Command.DEADLINE;
        if (userInput.startsWith("event")) return Command.EVENT;
        if (userInput.equals("list")) return Command.LIST;
        if (userInput.equals("bye")) return Command.BYE;
        return Command.UNKNOWN;
    }

    private static void processCommand(String userInput) throws PurrfessorDipsyException {
        Command command = parseCommand(userInput);
        switch (command) {
            case MARK:
            case UNMARK:
                handleMarkCommand(userInput);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                handleTaskCreation(userInput, command);
                break;
            case LIST:
                printMemory();
                break;
            case BYE:
                exitProgram();
                break;
            case UNKNOWN:
            default:
                throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.UNKNOWN_COMMAND);
        }
    }

    // GENERIC PRINT STATEMENTS (Can be reused)
    private static void printWithTerminalLines(String message) {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
        System.out.println(message);
        System.out.println(TERMINAL_LINE);
    }

    private static void printWelcomeMessage() {
        printWithTerminalLines("Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\nHow can I purrvide assistance? " +
                "Purrhaps I can lend a paw!");
    }

    private static void printExitMessage() {
        printWithTerminalLines("Fur-well friend, stay paw-sitive!");
    }

    private static void printErrorMessage(String message) {
        printWithTerminalLines("Error: " + message);
    }

    // HANDLE COMMANDS
    private static void handleMarkCommand(String userInput) throws PurrfessorDipsyException {
        Matcher markMatcher = MARK_PATTERN.matcher(userInput);
        if (markMatcher.matches()) {
            String action = markMatcher.group(1);
            int index = Integer.parseInt(markMatcher.group(2));
            if (index >= 1 && index < taskTableRowCount) {
                if (action.equals("mark")) {
                    markTaskAsDone(index);
                } else {
                    markTaskAsUndone(index);
                }
            } else {
                throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.INVALID_MARK_INDEX);
            }
        } else {
            throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.INVALID_MARK);
        }
    }

    private static void handleTaskCreation(String userInput, Command command) throws PurrfessorDipsyException {
        Matcher matcher;
        String description, by, start, end;

        switch (command) {
            case TODO:
                matcher = TODO_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    saveToMemory(new ToDo(description));
                } else {
                    throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.INVALID_TODO);
                }
                break;

            case DEADLINE:
                matcher = DEADLINE_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    by = matcher.group(2);
                    saveToMemory(new Deadline(description, by));
                } else {
                    throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.INVALID_DEADLINE);
                }
                break;

            case EVENT:
                matcher = EVENT_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    start = matcher.group(2);
                    end = matcher.group(3);
                    saveToMemory(new Event(description, start, end));
                } else {
                    throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.INVALID_EVENT);
                }
                break;

            default:
                throw new PurrfessorDipsyException(PurrfessorDipsyException.ErrorType.UNKNOWN_COMMAND);
        }
    }

    // Other util methods used for performing commands.
    private static void saveToMemory(Task task) {
        taskTable[taskTableRowCount] = task;
        taskTableRowCount++;
        printWithTerminalLines("Got it! I've added this task:\n" + task +
                "\nYou now have " + taskTableRowCount + " tasks in your list.");
    }

    private static void markTaskAsDone(int index) {
        Task task = taskTable[index - 1]; // table is 0-indexed
        task.markAsDone();
        printWithTerminalLines("Meow! I’ve scratched this task off the list!\n" + task);
    }

    private static void markTaskAsUndone(int index) {
        Task task = taskTable[index - 1]; // table is 0-indexed
        task.markAsUndone();
        printWithTerminalLines("Mrrreow! I’ve batted this task back onto the list.\n" + task);
    }

    private static void printMemory() {
        if (taskTableRowCount == 0) {
            printWithTerminalLines("You have no tasks in your list.");
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskTableRowCount; i++) {
                int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
                result.append(printedIndex).append(".").append(taskTable[i]);
                if (i < taskTableRowCount - 1) { // Don't append a newline after the last task
                    result.append("\n");
                }
            }
            printWithTerminalLines(result.toString());
        }
    }

    private static void exitProgram() {
        printExitMessage();
        isRunning = false; // Set the loop control flag to false to exit the loop gracefully.
    }
}
