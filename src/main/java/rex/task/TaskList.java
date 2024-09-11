package rex.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import rex.exception.InvalidInputException;
import rex.util.Parser;
import rex.util.Storage;
/**
 * The {@code TaskList} class manages a list of tasks, including adding, retrieving, updating,
 * and deleting tasks. It also handles the loading and saving of tasks to persistent storage.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with the specified storage.
     * Loads tasks from the storage upon initialization.
     *
     * @param storage The storage instance used for loading and saving tasks.
     * @throws IOException If an I/O error occurs during loading.
     */
    public TaskList(Storage storage) throws IOException {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        loadList();
    }

    /**
     * Adds a new {@code ToDo} task to the list and updates storage.
     *
     * @param argument The description of the ToDo task.
     * @return The newly added {@code ToDo} task.
     * @throws IOException If an I/O error occurs during storage update.
     */
    public ToDo addToDo(String argument) throws IOException {
        ToDo toDo = new ToDo(argument, false);
        tasks.add(toDo);
        updateStorage();
        return toDo;
    }

    /**
     * Adds a new {@code Deadline} task to the list and updates storage.
     * Parses the input argument to extract the description and deadline date/time.
     *
     * @param argument The description and deadline of the task in a single string.
     * @return The newly added {@code Deadline} task.
     * @throws IOException If an I/O error occurs during storage update.
     * @throws InvalidInputException If the input argument is invalid.
     */
    public Deadline addDeadline(String argument) throws IOException, InvalidInputException {
        String[] argumentTokens = Parser.parseDeadline(argument);
        String description = argumentTokens[0];
        LocalDateTime by = Parser.parseDateTime(argumentTokens[1]);

        Deadline deadline = new Deadline(description, false, by);
        tasks.add(deadline);
        updateStorage();
        return deadline;
    }

    /**
     * Adds a new {@code Event} task to the list and updates storage.
     * Parses the input argument to extract the description, start time, and end time.
     *
     * @param argument The description, start time, and end time of the event in a single string.
     * @return The newly added {@code Event} task.
     * @throws IOException If an I/O error occurs during storage update.
     * @throws InvalidInputException If the input argument is invalid.
     */
    public Event addEvent(String argument) throws IOException, InvalidInputException {
        String[] argumentTokens = Parser.parseEvent(argument);
        String description = argumentTokens[0];
        LocalDateTime from = Parser.parseDateTime(argumentTokens[1]);
        LocalDateTime to = Parser.parseDateTime(argumentTokens[2]);

        Event event = new Event(description, false, from, to);
        tasks.add(event);
        updateStorage();
        return event;
    }

    /**
     * Loads a {@code ToDo} task into the list without updating storage.
     * This is typically used when loading tasks from a file.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public void loadTask(String description, boolean isDone) {
        tasks.add(new ToDo(description, isDone));
    }

    /**
     * Loads a {@code Deadline} task into the list without updating storage.
     * This is typically used when loading tasks from a file.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param by          The deadline of the task.
     */
    public void loadTask(String description, boolean isDone, LocalDateTime by) {
        tasks.add(new Deadline(description, isDone, by));
    }

    /**
     * Loads an {@code Event} task into the list without updating storage.
     * This is typically used when loading tasks from a file.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public void loadTask(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        tasks.add(new Event(description, isDone, from, to));
    }

    /**
     * Retrieves a task by its index in the list.
     *
     * @param argument The index of the task as a string.
     * @return The task at the specified index.
     */
    public Task getTask(String argument) {
        return getTask(Integer.parseInt(argument));
    }

    /**
     * Retrieves a task by its index in the list.
     *
     * @param index The index of the task as an integer.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns a string representation of the entire task list.
     * If the list is empty, a message indicating the list is empty is returned.
     *
     * @return A string representation of the task list.
     */
    public String getListDisplay() {
        if (isEmpty()) {
            return "The list is empty! rawr\n";
        }

        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(getTask(i)).append("\n");
        }

        return output.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Finds and returns tasks from the task list that contain the specified keyword in their description.
     *
     * <p>The method iterates through all the tasks in the list and checks if the description of each
     * task contains the specified keyword. If a task matches, it is included in the output string,
     * which displays the task number and the task details.</p>
     *
     * @param keyword The keyword to search for within the task descriptions.
     * @return A formatted string listing all tasks that contain the keyword, including their task number.
     *         If no tasks match the keyword, an empty result string is returned
     *         with a message indicating the search results.
     */
    public String findTasks(String keyword) {
        int taskNumber = 1;
        String output = "Here are the matching tasks in your list:\n";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = getTask(i);
            if (task.getDescription().contains(keyword)) {
                output += taskNumber + "." + task + "\n";
                taskNumber++;
            }
        }

        return output;
    }

    /**
     * Marks a task as done and updates storage.
     *
     * @param argument The index of the task as a string.
     * @return The task that was marked as done.
     * @throws IOException If an I/O error occurs during storage update.
     */
    public Task markTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        actionTask.markAsDone();
        updateStorage();
        return actionTask;
    }

    /**
     * Unmarks a task as done and updates storage.
     *
     * @param argument The index of the task as a string.
     * @return The task that was unmarked.
     * @throws IOException If an I/O error occurs during storage update.
     */
    public Task unmarkTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        actionTask.unMarkDone();
        updateStorage();
        return actionTask;
    }

    /**
     * Deletes a task from the list and updates storage.
     *
     * @param argument The index of the task as a string.
     * @return The task that was deleted.
     * @throws IOException If an I/O error occurs during storage update.
     */
    public Task deleteTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        tasks.remove(actionTask);
        Task.removeTask();
        updateStorage();
        return actionTask;
    }

    /**
     * Loads the tasks from storage into the list.
     *
     * @throws IOException If an I/O error occurs during loading.
     */
    private void loadList() throws IOException {
        storage.loadFile(this);
    }

    /**
     * Updates the storage with the current task list.
     *
     * @throws IOException If an I/O error occurs during storage update.
     */
    private void updateStorage() throws IOException {
        storage.updateFile(this);
    }
}
