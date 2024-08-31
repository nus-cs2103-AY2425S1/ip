package weeny;

/**
 * Main class for the Weeny task management application.
 * Manages user commands and handles tasks.
 */
public class Weeny {

    /**
     * Starts the Weeny application.
     * Initializes UI, storage, parser, and task list. Processes user commands.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui(); // UI for user interactions
        Storage storage = new Storage(); // Manages task data storage
        Parser parser = new Parser(); // Parses user input
        TaskList taskList = new TaskList(); // List of tasks

        // Ensure data directory and TaskList.txt file exist
        storage.createFileIfNotExist("./data", "TaskList.txt");

        // Load tasks from file
        taskList.getTasks().addAll(storage.loadTask("./data/TaskList.txt"));

        boolean isFarewell = false;
        ui.showWelcomeMessage();

        while (!isFarewell) {
            String input = ui.readUserInput();
            String command = parser.extractFirstWord(input);

            try {
                switch (command) {
                case "list":
                    ui.showTaskList(taskList.getTasks());
                    break;

                case "bye":
                    isFarewell = true;
                    break;

                case "mark":
                    int markIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(markIndex, taskList.size(), "mark");
                    taskList.markTask(markIndex);
                    ui.showMarkMessage(taskList.getTask(markIndex));
                    break;

                case "unmark":
                    int unmarkIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(unmarkIndex, taskList.size(), "unmark");
                    taskList.unmarkTask(unmarkIndex);
                    ui.showUnmarkMessage(taskList.getTask(unmarkIndex));
                    break;

                case "todo":
                    validateTodoInput(input);
                    printLine();
                    Task todoTask = new Todo(input.substring(5).trim());
                    taskList.addTask(todoTask);
                    ui.printTaskAddedMessage(todoTask, taskList.size());
                    printLine();
                    break;

                case "event":
                    validateEventInput(input);
                    Task eventTask = new Events(parser.extractEventName(input),
                            parser.extractEventTimes(input)[0],
                            parser.extractEventTimes(input)[1]);
                    taskList.addTask(eventTask);
                    ui.printTaskAddedMessage(eventTask, taskList.size());
                    printLine();
                    break;

                case "deadline":
                    validateDeadlineInput(input);
                    Task deadlineTask = new Deadlines(parser.extractDeadlineName(input),
                            parser.extractDeadlineTime(input));
                    taskList.addTask(deadlineTask);
                    ui.printTaskAddedMessage(deadlineTask, taskList.size());
                    printLine();
                    break;

                case "delete":
                    int deleteIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(deleteIndex, taskList.size(), "delete");
                    Task removedTask = taskList.getTask(deleteIndex);
                    taskList.deleteTask(deleteIndex);
                    ui.showTaskDeletedMessage(removedTask, taskList.size());
                    break;

                default:
                    throw new UnsupportedOperationException("Unknown command");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbyeMessage();

        // Save tasks to file
        storage.saveTask("./Data/TaskList.txt", taskList.getTasks());
    }

    /**
     * Prints a separator line.
     */
    public static void printLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Checks if the index is within the task list range.
     *
     * @param index The index to check.
     * @param size The size of the task list.
     * @param action The action being performed (e.g., "mark", "delete").
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    protected static void validateIndex(int index, int size, String action) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index for " + action + " action.");
        }
    }

    /**
     * Validates input for a "todo" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input is too short.
     */
    private static void validateTodoInput(String input) {
        if (input.length() <= 5) {
            throw new IllegalArgumentException("To-Do description is too short.");
        }
    }

    /**
     * Validates input for an "event" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input lacks necessary details.
     */
    private static void validateEventInput(String input) {
        if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new IllegalArgumentException("Event details are incomplete.");
        }
    }

    /**
     * Validates input for a "deadline" command.
     *
     * @param input The command input.
     * @throws IllegalArgumentException if the input lacks necessary details.
     */
    private static void validateDeadlineInput(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new IllegalArgumentException("Deadline details are incomplete.");
        }
    }
}
