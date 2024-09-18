package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the MySutong application. This class provides methods
 * to manage tasks such as adding, removing, and retrieving tasks based on their index.
 * It also supports searching for tasks based on a keyword found in the task's description.
 * Additionally, it now handles commands like add, mark, unmark, and delete.
 */
public class TaskList {
    private final List<Task> tasks; // The list of tasks, stored as a generic List interface.

    /**
     * Constructs a new TaskList using an existing list of tasks.
     * This allows for initializing the TaskList with a predefined list,
     * enabling flexibility in the type of List used.
     *
     * @param tasks A List of {@link Task} objects to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor that initializes the TaskList with an empty ArrayList.
     * This is useful when no initial tasks are provided, and tasks need to be added dynamically.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of {@link Task} objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The {@link Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The {@link Task} object to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.remove(index);
    }

    /**
     * Searches for tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for within the task descriptions.
     * @return A list of {@link Task} objects that contain the keyword in their description.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Handles the execution of commands by delegating appropriate actions for each command.
     *
     * @param command The user command to be processed.
     * @param arguments The arguments provided with the command (if any).
     * @param ui The UI handler for displaying feedback to the user.
     * @param storage The storage handler to save tasks after any changes.
     */
    public void handleCommand(String command, String arguments, Ui ui, Storage storage) throws Exception {
        switch (command) {
        case "bye":
            ui.showGoodbye();
            System.exit(0);
            break;

        case "list":
            ui.showTaskList(this);
            break;

        case "mark":
            markTaskAsDone(arguments, ui, storage);
            break;

        case "unmark":
            unmarkTask(arguments, ui, storage);
            break;

        case "todo":
            addTodoTask(arguments, ui, storage);
            break;

        case "deadline":
            addDeadlineTask(arguments, ui, storage);
            break;

        case "event":
            addEventTask(arguments, ui, storage);
            break;

        case "delete":
            deleteTask(arguments, ui, storage);
            break;

        case "find":
            findTasks(arguments, ui);
            break;

        default:
            throw new UnknownCommandException("I'm sorry, but I don't know what that means.");
        }
    }

    // Helper methods to handle different task operations:

    private void markTaskAsDone(String argument, Ui ui, Storage storage) throws Exception {
        int index = parseTaskIndex(argument);
        Task task = getTask(index);
        task.markAsDone();
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(task.toString());
        ui.showLine();
        storage.save(this);
    }

    private void unmarkTask(String argument, Ui ui, Storage storage) throws Exception {
        int index = parseTaskIndex(argument);
        Task task = getTask(index);
        task.unmark();
        ui.showLine();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(task.toString());
        ui.showLine();
        storage.save(this);
    }

    private void addTodoTask(String argument, Ui ui, Storage storage) throws Exception {
        if (argument.isEmpty()) {
            throw new NoDescriptionException("Description cannot be empty for todo.");
        }
        Task todo = new Todo(argument);
        addTask(todo);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(todo.toString());
        ui.showLine();
        storage.save(this);
    }

    private void addDeadlineTask(String argument, Ui ui, Storage storage) throws Exception {
        if (!argument.contains(" /by ")) {
            throw new NoDescriptionException("Deadline command must include '/by' followed by a date/time.");
        }
        String[] details = argument.split(" /by ", 2);
        Task deadline = new Deadline(details[0].trim(), LocalDateTime.parse(details[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        addTask(deadline);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(deadline.toString());
        ui.showLine();
        storage.save(this);
    }

    private void addEventTask(String argument, Ui ui, Storage storage) throws Exception {
        if (!argument.contains(" /from ") || !argument.contains(" /to ")) {
            throw new NoDescriptionException("Event command must include '/from' and '/to' followed by their respective times.");
        }
        String[] details = argument.split(" /from | /to ", 3);
        Task event = new Event(details[0].trim(), LocalDateTime.parse(details[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm")), LocalDateTime.parse(details[2].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        addTask(event);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(event.toString());
        ui.showLine();
        storage.save(this);
    }

    private void deleteTask(String argument, Ui ui, Storage storage) throws Exception {
        int index = parseTaskIndex(argument);
        Task removedTask = getTask(index);
        removeTask(index);
        ui.showLine();
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(removedTask.toString());
        ui.showLine();
        storage.save(this);
    }

    private void findTasks(String keyword, Ui ui) {
        List<Task> foundTasks = findTasksByKeyword(keyword);
        ui.showSearchResults(foundTasks);
    }

    private int parseTaskIndex(String argument) throws Exception {
        if (argument.isEmpty()) {
            throw new InvalidTaskNumberException("No task index provided.");
        }
        int index = Integer.parseInt(argument) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException("Task index out of range.");
        }
        return index;
    }
}
