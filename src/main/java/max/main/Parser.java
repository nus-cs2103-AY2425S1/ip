package max.main;

import max.exception.MaxException;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;
import max.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It processes the input from the user, interacts with the TaskList, and handles
 * task-related operations like adding, deleting, and marking tasks.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser object with the given TaskList, Ui, and Storage components.
     *
     * @param taskList The list of tasks to be managed.
     * @param ui The UI component used for interacting with the user.
     * @param storage The storage component for saving and loading tasks.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Default constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * Parses user input and processes commands.
     * It executes commands like "bye", "hi", "hello", "list", "mark", "unmark", "deadline",
     * "todo", "event", "delete", and "find", handling each accordingly.
     * If the command is invalid, it throws a MaxException.
     *
     * @param text The user input text to be parsed.
     * @return A boolean indicating whether the application should exit (true if "bye" command is issued).
     * @throws MaxException If an invalid command is given or an error occurs during execution.
     */
    public boolean parseText(String text) throws MaxException {

            //String text = scanner.nextLine().trim();
        try {
            if (text.equals("bye")) {
                ui.printBye();
                return true;
            } else if (text.equals("hi") || text.equals("hello")) {
                ui.printHello();
            } else if (text.equals("list")) {
                handleList();
            } else if (text.startsWith("mark")) {
                int index = Integer.parseInt(text.replace("mark ", "")) - 1;
                handleMark(index);
            } else if (text.startsWith("unmark")) {
                int index = Integer.parseInt(text.replace("unmark ", "")) - 1;
                handleUnmark(index);
            } else if (text.startsWith("deadline")) {
                handleDeadline(text);
            } else if (text.startsWith("todo")) {
                handleTodo(text);
            } else if (text.startsWith("event")) {
                handleEvent(text);
            } else if (text.startsWith("delete")) {
                int index = Integer.parseInt(text.replace("delete ", "")) - 1;
                handleDelete(index);
            } else if (text.startsWith("find")) {
                String toFind = text.replaceFirst("find", "").trim();
                handleFind(toFind);
            } else {
                throw new MaxException("What does that mean?:( Begin with todo, event, or deadline.");
            }
            this.storage.saveTasks(taskList.getTasks());
        } catch (MaxException e) {
            ui.printLine();
            ui.printMessage(e.getMessage());
        }

        return false;

    }

    /**
     * Handles the "list" command to display all tasks.
     * <p>
     * Calls the Ui component to print the entire task list, showing all current tasks.
     * </p>
     */
    private void handleList() {
        ui.printList(false);
        ui.list(taskList.getTasks());
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     * <p>
     * Retrieves the task from the task list based on the provided index,
     * marks the task as not done, and prints the updated task status using the Ui component.
     * </p>
     *
     * @param index The index of the task in the task list to be marked as not done.
     */
    private void handleUnmark(int index) {
        Task task = taskList.getTask(index);
        task.markNotDone();
        ui.printMarkNotDone(task);
    }

    /**
     * Handles the "mark" command to mark a task as done.
     * <p>
     * Retrieves the task from the task list based on the provided index,
     * marks the task as done, and prints the updated task status using the Ui component.
     * </p>
     *
     * @param index The index of the task in the task list to be marked as done.
     */
    private void handleMark(int index) {
        Task task = taskList.getTask(index);
        task.markDone();
        ui.printMarkDone(task);
    }

    /**
     * Handles the "delete" command to remove a task from the task list.
     * <p>
     * Retrieves the task from the task list based on the provided index, removes it from the list,
     * and prints a message using the Ui component to confirm the deletion and display the remaining task count.
     * </p>
     *
     * @param index The index of the task in the task list to be deleted.
     * @throws MaxException If there is an issue deleting the task.
     */
    private void handleDelete(int index) throws MaxException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.printDeleteTask(task, taskList.getSize());
    }

    /**
     * Handles the "find" command to search for tasks containing the specified keyword.
     * <p>
     * Filters the tasks in the list based on the provided keyword and displays the matching tasks.
     * If the provided keyword is empty, a {@link MaxException} is thrown.
     * </p>
     *
     * @param text The keyword used to search for tasks. The search is case-sensitive and matches tasks containing this keyword.
     * @throws MaxException If the {@code text} is empty, indicating that no search keyword was provided.
     */
    private void handleFind(String text) throws MaxException {
        if (text.isEmpty()) {
            throw new MaxException("The provided filter for find cannot be empty.");
        }
        ArrayList<Task> filteredTasks = taskList.find(text);
        ui.printList(true);
        ui.list(filteredTasks);
    }

    /**
     * Handles the creation of a new Deadline task.
     *
     * @param text The user input text containing the task description and due date.
     * @throws MaxException If the task description or date format is invalid.
     */
    private void handleDeadline(String text) throws MaxException {
        String[] temp = text.replace("deadline ", "").split(" /by ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        LocalDateTime LDT = parseDate(temp[1]);
        Deadline deadline = (LDT != null) ? new Deadline(temp[0], LDT) : new Deadline(temp[0], temp[1]);
        taskList.addTask(deadline);
        ui.printTaskTypeAdded(deadline, taskList.getSize());
    }

    /**
     * Handles the creation of a new Event task.
     *
     * @param text The user input text containing the task description and event period.
     * @throws MaxException If the task description or event period is invalid.
     */
    private void handleEvent(String text) throws MaxException {
        String[] temp = text.replace("event ", "").split(" /from ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        Event event = new Event(temp[0], temp[1]);
        taskList.addTask(event);
        ui.printTaskTypeAdded(event, taskList.getSize());
    }

    /**
     * Handles the creation of a new Todo task.
     *
     * @param text The user input text containing the task description.
     * @throws MaxException If the task description is empty.
     */
    private void handleTodo(String text) throws MaxException {
        String temp = text.replace("todo", "").trim();
        checkTask(temp);

        Todo todo = new Todo(temp);
        taskList.addTask(todo);
        ui.printTaskTypeAdded(todo, taskList.getSize());
    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     * @throws MaxException If the date format is invalid.
     */
    public LocalDateTime parseDate(String date) throws MaxException {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, converter);
        } catch (DateTimeParseException e) {
            throw new MaxException("Invalid date format! Please use d/M/yyyy HHmm. "
                    + "For example, '2/12/2024 1800'");
        }
    }

    /**
     * Checks if the given task description is empty.
     *
     * @param todo The task description to be checked.
     * @throws MaxException If the task description is empty.
     */
    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
}
