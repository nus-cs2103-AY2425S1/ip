import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.ToDo;
import Exception.InvalidCommandException;
import Exception.UnknownCommandException;
import Utilities.DateTimeParser;

public class PurrfessorDipsy {
    private static ArrayList<Task> taskTable = new ArrayList<>();
    private static boolean isRunning = true; // to allow for a more graceful exit

    private static final Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.+) /by (.+)$");
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d+)");

    private enum Command {
        MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, LIST, BYE, UNKNOWN,
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        taskTable = Storage.loadTasksFromFile();
        printWelcomeMessage();
        while (isRunning) {
            try {
                String userInput = inputScanner.nextLine().trim();
                processCommand(userInput);
            } catch (InvalidCommandException | UnknownCommandException e) {
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
        if (userInput.startsWith("delete")) return Command.DELETE;
        if (userInput.startsWith("list")) return Command.LIST;
        if (userInput.equals("bye")) return Command.BYE;
        return Command.UNKNOWN;
    }

    private static void processCommand(String userInput) throws UnknownCommandException, InvalidCommandException {
        Command command = parseCommand(userInput);
        switch (command) {
            case MARK, UNMARK -> handleMarkCommand(userInput);
            case TODO, DEADLINE, EVENT -> handleTaskCreation(userInput, command);
            case DELETE -> handleDeleteCommand(userInput);
            case LIST -> handleListCommand(userInput); // More specific naming
            case BYE -> exitProgram();
            default -> throw new UnknownCommandException();
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
        String introduction = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                              "and Purrtector of the Realm of Naps.\n" +
                              "How can I purrvide assistance? Purrhaps I could lend a paw!";
        printWithTerminalLines(introduction);
    }

    private static void printExitMessage() {
        printWithTerminalLines("Fur-well friend, stay paw-sitive!");
    }

    private static void printErrorMessage(String message) {
        printWithTerminalLines("Error: " + message);
    }

    // HANDLE COMMANDS
    private static void handleMarkCommand(String userInput) throws InvalidCommandException {
        Matcher markMatcher = MARK_PATTERN.matcher(userInput);
        if (markMatcher.matches()) {
            String action = markMatcher.group(1);
            int index = Integer.parseInt(markMatcher.group(2));
            if (index >= 1 && index < taskTable.size()) {
                if (action.equals("mark")) {
                    markTaskAsDone(index);
                } else {
                    markTaskAsUndone(index);
                }
            } else {
                throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_INDEX);
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_COMMAND);
        }
    }

    private static void handleTaskCreation(String userInput, Command command) throws InvalidCommandException, UnknownCommandException {
        Matcher matcher;
        String description, by, start, end;

        switch (command) {
            case TODO:
                matcher = TODO_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    saveToMemory(new ToDo(description));
                } else {
                    throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_TODO);
                }
                break;

            case DEADLINE:
                matcher = DEADLINE_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    by = matcher.group(2);
                    LocalDate parsedBy;
                    try {
                        parsedBy = DateTimeParser.parseDate(by);
                        saveToMemory(new Deadline(description, parsedBy));
                    } catch (DateTimeParseException e) {
                        printDateParseErrorMessage(by);
                    }
                } else {
                    throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DEADLINE);
                }
                break;

            case EVENT:
                matcher = EVENT_PATTERN.matcher(userInput);
                if (matcher.matches()) {
                    description = matcher.group(1);
                    start = matcher.group(2);
                    end = matcher.group(3);
                    LocalDate parsedStart, parsedEnd;
                    try {
                        parsedStart = DateTimeParser.parseDate(start);
                        try {
                            parsedEnd = DateTimeParser.parseDate(end);
                            saveToMemory(new Event(description, parsedStart, parsedEnd));
                        } catch (DateTimeParseException e) {
                            printDateParseErrorMessage(end);
                        }
                    } catch (DateTimeParseException e) {
                        printDateParseErrorMessage(start);
                    }
                } else {
                    throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_EVENT);
                }
                break;

            default:
                throw new UnknownCommandException();
        }
    }

    private static void handleListCommand(String userInput) {
        String[] parts = userInput.trim().split("\\s+");

        if (parts.length == 1) {
            // Case where input is 'list'
            printTasks(taskTable);
        } else if (parts.length == 2) {
            // Case where input is 'list <date>'
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                printTasks(filterTasksByDate(date));
            } catch (DateTimeParseException e) {
                printDateParseErrorMessage(parts[1]);
            }
        }
    }

    private static void handleDeleteCommand(String userInput) throws InvalidCommandException {
        Matcher matcher = DELETE_PATTERN.matcher(userInput);

        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            deleteTaskAtIndex(index);  // Delegate deletion logic
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_COMMAND);
        }
    }

    private static void deleteTaskAtIndex(int index) throws InvalidCommandException {
        if (index >= 1 && index <= taskTable.size()) {
            Task removedTask = taskTable.remove(index - 1); // Adjust for zero-based index
            printWithTerminalLines("Purrr, I've swatted this task away:\n" + removedTask +
                    "\nYou now have " + taskTable.size() + " tasks in your list.");
            Storage.saveTasksToLocalDisk(taskTable);
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_INDEX);
        }
    }

    // Other util methods used for performing commands.
    private static void saveToMemory(Task task) {
        taskTable.add(task);
        printWithTerminalLines("Got it! I've added this task:\n" + task +
                "\nYou now have " + taskTable.size() + " tasks in your list.");
        Storage.saveTasksToLocalDisk(taskTable);
    }

    private static void printTasks(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            printWithTerminalLines("Your task list is as empty as a well-sunned nap spot.");
        } else {
            StringBuilder result = new StringBuilder("Time to stretch those paws and tackle your tasks!\n");
            for (int i = 0; i < taskCount; i++) {
                int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
                result.append(printedIndex).append(".").append(tasks.get(i));
                if (i < taskCount - 1) { // Don't append a newline after the last task
                    result.append("\n");
                }
            }
            printWithTerminalLines(result.toString());
        }
    }

    private static void markTaskAsDone(int index) {
        Task task = taskTable.get(index - 1);
        task.markAsDone();
        printWithTerminalLines("Meow! I’ve scratched this task off the list!\n" + task);
        Storage.saveTasksToLocalDisk(taskTable);
    }

    private static void markTaskAsUndone(int index) {
        Task task = taskTable.get(index - 1);
        task.markAsUndone();
        printWithTerminalLines("Mrrreow! I’ve batted this task back onto the list.\n" + task);
        Storage.saveTasksToLocalDisk(taskTable);
    }

    private static void exitProgram() {
        printExitMessage();
        isRunning = false; // Set the loop control flag to false to exit the loop gracefully.
    }

    private static void printDateParseErrorMessage(String date) {
        System.out.println("Invalid date format: " + date +
                           " Please enter the date in the format yyyy-MM-dd (e.g., 2024-08-25).");
    }

    public static ArrayList<Task> filterTasksByDate(LocalDate date) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: taskTable) {
            if (t instanceof Deadline deadline) {
                if (deadline.getBy().equals(date)) {
                    res.add(t);
                }
            } else if (t instanceof Event event) {
                if (event.getStart().equals(date)) {
                    res.add(t);
                }
            }
        }
        return res;
    }
}
