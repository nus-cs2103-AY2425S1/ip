import java.util.List;

/**
 * Main class for the Weeny task management application.
 * Handles user input and task operations.
 */
public class Weeny {

    public static void main(String[] args) {
        Ui ui = new Ui(); // Create Ui object
        Storage storage = new Storage(); // Create storage object
        Parser parser = new Parser(); // Create parser object
        TaskList taskList = new TaskList(); // Create tasklist object

        // Check if data directory and TaskList.txt exist, create if not
        storage.createFileIfNotExist("./data", "TaskList.txt");

        // Read TaskList file to resume TaskList
        List<Task> tasks = storage.loadTask("./data/TaskList.txt");
        boolean isFarewell = false;

        ui.showWelcomeMessage();

        while (!isFarewell) {
            String input = ui.readUserInput();
            String command = parser.extractFirstWord(input);

            try {
                switch (command) {
                case "list":
                    ui.showTaskList(tasks);
                    break;

                case "bye":
                    isFarewell = true;
                    break;

                case "mark":
                    int markIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(markIndex, tasks.size(), "mark");
                    tasks.get(markIndex).setMark();
                    ui.showMarkMessage(tasks.get(markIndex));
                    break;

                case "unmark":
                    int unmarkIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(unmarkIndex, tasks.size(), "unmark");
                    tasks.get(unmarkIndex).setUnmark();
                    ui.showUnmarkMessage(tasks.get(unmarkIndex));
                    break;

                case "todo":
                    validateTodoInput(input);
                    printLine();
                    Task todoTask = new Todo(input.substring(5).trim());
                    tasks.add(todoTask);
                    ui.printTaskAddedMessage(todoTask, tasks.size());
                    printLine();
                    break;

                case "event":
                    validateEventInput(input);
                    Task eventTask = new Events(parser.extractEventName(input),
                            parser.extractEventTimes(input)[0],
                            parser.extractEventTimes(input)[1]);
                    tasks.add(eventTask);
                    ui.printTaskAddedMessage(eventTask, tasks.size());
                    printLine();
                    break;

                case "deadline":
                    validateDeadlineInput(input);
                    Task deadlineTask = new Deadlines(parser.extractDeadlineName(input),
                            parser.extractDeadlineTime(input));
                    tasks.add(deadlineTask);
                    ui.printTaskAddedMessage(deadlineTask, tasks.size());
                    printLine();
                    break;

                case "delete":
                    int deleteIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(deleteIndex, tasks.size(), "delete");
                    Task removedTask = tasks.remove(deleteIndex);
                    ui.showTaskDeletedMessage(removedTask, tasks.size());
                    break;

                default:
                    throw new UnsupportedOperationException("Oopsies we don't understand that command");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbyeMessage();
        
        // Write to TaskList.txt
        storage.saveTask("./Data/TaskList.txt", tasks);
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Validates that the index is within the valid range of task list.
     *
     * @param index The index to validate.
     * @param size The size of the task list.
     * @param action The action being performed (e.g., "mark", "delete").
     */
    private static void validateIndex(int index, int size, String action) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Just a reminder. You can't " + action + " tasks that don't exist!");
        }
    }

    /**
     * Validates the input for the "todo" command.
     *
     * @param input The input string.
     */
    private static void validateTodoInput(String input) {
        if (input.length() <= 5) {
            throw new IllegalArgumentException("Are you going to add nothing as your To-Do? >:(");
        }
    }

    /**
     * Validates the input for the "event" command.
     *
     * @param input The input string.
     */
    private static void validateEventInput(String input) {
        if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new IllegalArgumentException("What kind of event has no name or time? >:(");
        }
    }

    /**
     * Validates the input for the "deadline" command.
     *
     * @param input The input string.
     */
    private static void validateDeadlineInput(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new IllegalArgumentException("What is a deadline without a date or a task? >:(");
        }
    }
}
