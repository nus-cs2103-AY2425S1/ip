package mylo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mylo.data.DuplicatedTaskException;
import mylo.data.InsufficientInfoException;
import mylo.storage.Storage;
import mylo.storage.StorageOperationException;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Represents a list of tasks in the task management system.
 * <p></p>
 * <p>The {@code TaskList} class manages a collection of tasks, allowing for
 * operations such as adding, deleting, marking tasks as done or not done,
 * and retrieving tasks based on specific criteria. It utilizes a storage
 * system to persist task data.</p>
 * <p></p>
 * <p>Tasks in the list can be of different types, including TODOs, events,
 * and deadlines. The class also provides functionality for decoding task
 * data from storage and displaying the current task list.</p>
 *
 * @author cweijin
 */

public class TaskList {
    private final List<Task> list;
    private final Storage storage = new Storage();

    /**
     * Constructs an empty {@code TaskList}.
     * <p></p>
     * <p>This constructor initializes a new task list with no tasks.</p>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with a given list of tasks.
     *
     * @param taskList An {@code ArrayList} of {@code Task} objects to initialize the task list.
     */
    public TaskList(List<Task> taskList) {
        this.list = taskList;
    }

    /**
     * Adds a task to the list.
     * <p>This method creates a new task from the provided information and
     * adds it to the task list. It also persists the task to storage.</p>
     * <p>If the task already exists in the list, a {@link DuplicatedTaskException} will be thrown.</p>
     *
     * @param info The information string for the task to be added.
     * @param type The type of the task to be created.
     * @return A message confirming that the task has been added, along with the current task count.
     * @throws InsufficientInfoException If the information provided is insufficient to create a task.
     * @throws StorageOperationException If an error occurs while saving the task to storage.
     * @throws IllegalValueException If the provided values are invalid.
     * @throws DuplicatedTaskException If the task already exists in the task list.
     */
    public String addTask(String info, TaskType type) throws InsufficientInfoException,
            StorageOperationException, IllegalValueException, DuplicatedTaskException {
        if (info.isBlank()) {
            throw new InsufficientInfoException(type);
        } else {
            Task task = Task.of(info.substring(1), type);

            if (list.contains(task)) {
                throw new DuplicatedTaskException("Task already exists.");
            }

            list.add(task);
            storage.save(task);
            return String.format("Got it. I've added this task:\n %s\nNow you have %s tasks in the list.",
                    task, list.size());
        }
    }

    /**
     * Deletes a task from the list based on its index.
     * <p></p>
     * <p>This method removes a task from the task list and updates the storage.</p>
     *
     * @param index The index of the task to be deleted (1-based index).
     * @throws StorageOperationException If an error occurs while rewriting the storage.
     * @throws IndexOutOfBoundsException If the provided index is out of range.
     */
    public String deleteTask(int index) throws StorageOperationException, IndexOutOfBoundsException {
        if (index > list.size()) {
            throw new IndexOutOfBoundsException(String.format(
                    "There is only %s tasks in the list.", list.size()));
        }
        Task removed = list.remove(index - 1);
        storage.rewrite(list);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %s tasks in the list.",
                removed, list.size());
    }

    /**
     * Marks a task as done based on its index.
     * <p></p>
     * <p>This method marks the specified task as done and updates the storage.</p>
     *
     * @param index The index of the task to be marked as done (1-based index).
     * @return A message confirming that the task has been marked as done.
     * @throws StorageOperationException If an error occurs while rewriting the storage.
     */
    public String markTaskAsDone(int index) throws StorageOperationException {
        String message = list.get(index - 1).markAsDone();
        storage.rewrite(this.list);
        return message;
    }

    /**
     * Marks a task as not done based on its index.
     * <p></p>
     * <p>This method marks the specified task as not done and updates the storage.</p>
     *
     * @param index The index of the task to be marked as not done (1-based index).
     * @return A message confirming that the task has been marked as not done.
     * @throws StorageOperationException If an error occurs while rewriting the storage.
     */
    public String markTaskAsUndone(int index) throws StorageOperationException {
        String message = list.get(index - 1).markAsUndone();
        storage.rewrite(list);
        return message;
    }

    /**
     * Decodes lines of strings representing tasks and returns a {@code TaskList}.
     * <p></p>
     * <p>This static method takes a list of strings where each string represents a task and
     * creates a {@code TaskList} containing the corresponding task objects.</p>
     *
     * @param taskStrings List of {@code String} where each represents a task.
     * @return {@code TaskList} containing tasks decoded from the {@code taskStrings}.
     * @throws IllegalValueException If any of the task strings contain invalid values.
     */
    public static TaskList decodeTxt(List<String> taskStrings) throws IllegalValueException {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String taskString : taskStrings) {
            if (!taskString.isEmpty()) {
                String[] keys = taskString.split(" \\| ");
                taskList.add(Task.of(keys));
            }
        }

        return new TaskList(taskList);
    }

    /**
     * Retrieves tasks due on a specified date.
     * <p></p>
     * <p>This method returns a new {@code TaskList} containing tasks that are due on the given date.</p>
     *
     * @param date The date to filter tasks by.
     * @return A {@code TaskList} containing tasks that are ongoing or due on the specified date.
     */
    public TaskList tasksOnDate(LocalDateTime date) {
        List<Task> result = list.stream().filter((Task task)
                -> (task instanceof Deadline && ((Deadline) task).isDueOnDate(date))
                || (task instanceof Event && ((Event) task).isOngoing(date))).toList();

        return new TaskList(result);
    }

    /**
     * Retrieves tasks that contain the keyword in their description.
     * <p></p>
     * <p>This method returns a new {@code TaskList} containing tasks that have description
     * matching the keyword.</p>
     *
     * @param keyword The keyword to filter tasks by.
     * @return A {@code TaskList} containing tasks with description matching the keyword.
     */
    public TaskList tasksWithKeyword(String keyword) {
        List<Task> result = list.stream().filter((Task task) -> task.isMatch(keyword)).toList();

        return new TaskList(result);
    }

    /**
     * Returns a string representation of the task list.
     * <p></p>
     * <p>This method provides a formatted string that displays all tasks in the list,
     * including their indices, statuses, and titles. If the list is empty, a message
     * indicating that no tasks are present is returned.</p>
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        if (list.isEmpty()) {
            string.append("Your task list is empty. Try adding tasks: \n1. todo <Task Title> "
                    + "\n2. event <Task Title> /from <Start Date> /to <End Date>"
                    + " \n3. deadline <Task Title> /by <Due Date>");
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}
