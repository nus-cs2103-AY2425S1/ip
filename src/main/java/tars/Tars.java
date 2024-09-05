package tars;

import java.util.List;

/**
 * The main class for the TARS application, which manages tasks and interacts with the user.
 *
 * <p>The Tars class initializes the user interface, loads tasks from storage, and contains
 * the main application loop that processes user commands. It supports various commands for
 * task management, including adding, marking, unmarking, listing, removing, and finding tasks.
 */
public class Tars {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Tars object, initializing the user interface and loading tasks from storage.
     *
     * <p>The constructor attempts to load tasks from the specified file. If an error occurs
     * during loading, an empty task list is created instead.
     */
    public Tars() {
        ui = new Ui();
        Storage storage = new Storage("./data/tars.txt");
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.loadTasks(), storage);
        } catch (TarsException e) {
            loadedTasks = new TaskList(storage);
        }
        tasks = loadedTasks;
    }

    /**
     * Processes the user's input and returns a response based on the command provided.
     *
     * This method parses the user's input into a command and its arguments, executes the
     * corresponding action, and returns the appropriate response message.
     * The supported commands include:
     *
     * @param input the user's input string.
     * @return a response message based on the command executed.
     */
    public String getResponse(String input) {
        String[] parsedInput = Parser.parseCommandAndArguments(input);
        String command = parsedInput[0];
        String arguments = parsedInput[1];

        try {
            switch (command) {
            case "bye":
                return ui.getGoodbyeMessage();
            case "list":
                return handleList();
            case "mark":
                return handleMark(arguments);
            case "unmark":
                return handleUnmark(arguments);
            case "todo":
                return handleTodo(arguments);
            case "deadline":
                return handleDeadline(arguments);
            case "event":
                return handleEvent(arguments);
            case "remove":
                return handleRemove(arguments);
            case "find":
                return handleFind(arguments);
            default:
                return ui.getResponseMessage("I'm sorry, I can't quite help you with that.");
            }
        } catch (TarsException e) {
            return ui.getResponseMessage(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.getResponseMessage("Invalid number format.");
        } catch (Exception e) {
            return ui.getResponseMessage("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Handles the "list" command by returning a string representation of all tasks in the task list.
     *
     * @return a string containing the list of tasks.
     */
    private String handleList() {
        return ui.getResponseMessage(tasks.listTasks());
    }

    /**
     * Handles the "mark" command by marking a task as done.
     *
     * <p>This method parses the task index from the provided arguments, marks the corresponding task as done,
     * and returns a response message indicating the task has been marked.
     *
     * @param arguments the string containing the task index to be marked.
     * @return a string indicating the task has been marked as done.
     * @throws TarsException if the task index is invalid or there is an issue marking the task.
     */
    private String handleMark(String arguments) throws TarsException {
        int markIndex = Integer.parseInt(arguments.trim()) - 1;
        Task markedTask = tasks.markTaskDone(markIndex);
        return ui.getTaskMarkedResponse(markedTask);
    }

    /**
     * Handles the "unmark" command by marking a task as not done.
     *
     * <p>This method parses the task index from the provided arguments, marks the corresponding task as undone,
     * and returns a response message indicating the task has been unmarked.
     *
     * @param arguments the string containing the task index to be unmarked.
     * @return a string indicating the task has been unmarked as done.
     * @throws TarsException if the task index is invalid or there is an issue unmarking the task.
     */
    private String handleUnmark(String arguments) throws TarsException {
        int unmarkIndex = Integer.parseInt(arguments.trim()) - 1;
        Task unmarkedTask = tasks.markTaskUndone(unmarkIndex);
        return ui.getTaskUnmarkedResponse(unmarkedTask);
    }

    /**
     * Handles the "todo" command by adding a new Todo task to the task list.
     *
     * <p>This method creates a new {@link Todo} task based on the provided arguments (task description),
     * adds it to the task list, and returns a response message indicating the task has been added.
     *
     * @param arguments the string containing the task description for the todo task.
     * @return a string indicating the task has been added.
     * @throws TarsException if there is an issue adding the task.
     */
    private String handleTodo(String arguments) throws TarsException {
        Task todo = new Todo(arguments, false);
        tasks.addTask(todo);
        return ui.getTaskAddedResponse(todo, tasks.getSize());
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task to the task list.
     *
     * <p>This method parses the task description and due date from the provided arguments,
     * creates a new {@link Deadline} task, adds it to the task list, and returns a response message
     * indicating the task has been added.
     *
     * @param arguments the string containing the task description and due date for the deadline task
     *                  (formatted as "description /by dueDate").
     * @return a string indicating the task has been added.
     * @throws TarsException if there is an issue adding the task or parsing the due date.
     */
    private String handleDeadline(String arguments) throws TarsException {
        String[] deadlineParts = arguments.split(" /by ", 2);
        Task deadline = new Deadline(deadlineParts[0], false, deadlineParts[1]);
        tasks.addTask(deadline);
        return ui.getTaskAddedResponse(deadline, tasks.getSize());
    }

    /**
     * Handles the "event" command by adding a new Event task to the task list.
     *
     * <p>This method parses the task description, start time, and end time from the provided arguments,
     * creates a new {@link Event} task, adds it to the task list, and returns a response message
     * indicating the task has been added.
     *
     * @param arguments the string containing the task description, start time, and end time
     *                  (formatted as "description /from startTime /to endTime").
     * @return a string indicating the task has been added.
     * @throws TarsException if there is an issue adding the task or parsing the start/end times.
     */
    private String handleEvent(String arguments) throws TarsException {
        String[] eventParts = arguments.split(" /from | /to ", 3);
        Task event = new Event(eventParts[0], false, eventParts[1], eventParts[2]);
        tasks.addTask(event);
        return ui.getTaskAddedResponse(event, tasks.getSize());
    }

    /**
     * Handles the "remove" command by removing a task from the task list.
     *
     * @param arguments the string containing the task index to be removed.
     * @return a string indicating the task has been removed.
     * @throws TarsException if the task index is invalid or there is an issue removing the task.
     */
    private String handleRemove(String arguments) throws TarsException {
        int removeIndex = Integer.parseInt(arguments.trim()) - 1;
        tasks.removeTask(removeIndex);
        return ui.getTaskRemovedResponse("Task removed", tasks.getSize());
    }

    /**
     * Handles the "find" command by searching for tasks that match the provided keyword.
     *
     * @param arguments the string containing the keyword to search for.
     * @return a string listing the tasks that match the keyword.
     */
    private String handleFind(String arguments) {
        List<Task> foundTasks = tasks.findTasks(arguments.trim());
        return ui.getFoundTasksResponse(foundTasks);
    }
}

