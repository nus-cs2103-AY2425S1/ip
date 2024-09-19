package task;

import utility.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The {@code TaskList} class manages a list of {@code Task} objects.
 * It provides functionalities for adding, removing, marking, and searching tasks,
 * as well as listing tasks and handling task creation based on their types.
 */
public class TaskList {

    private final ArrayList<Task> TASKS;
    private final Validator VALIDATE = new Validator();

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Returns the list of tasks as an {@code ArrayList}.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAsList(){
        return TASKS;
    }

    /**
     * Retrieves a task by its ID (1-based index).
     *
     * @param id The ID of the task.
     * @return The {@code Task} object if found, {@code null} otherwise.
     */
    public Task getTaskByID(int id){
        if (id >= 1 && id <= TASKS.size()) {
            return TASKS.get(id - 1);
        }
        return null;
    }

    /**
     * Adds an existing task to the list.
     *
     * @param task The task to be added.
     */
    public void addExisting(Task task){
        TASKS.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The count of tasks.
     */
    public int getTaskCount() {
        return TASKS.size();
    }

    /**
     * Creates a new task based on the specified task type and description.
     * For deadlines and events, additional date arguments are expected.
     *
     * @param type The type of task (ToDo, Deadline, Event).
     * @param desc The description of the task.
     * @param args Optional date arguments for Deadline and Event tasks.
     * @return The created {@code Task} object.
     */
    public Task createTask(TaskType type, String desc, LocalDateTime... args) {
        return switch (type) {
            case TODO -> new ToDo(desc);
            case DEADLINE -> new Deadline(desc, args[0]);
            case EVENT -> new Event(desc, args[0], args[1]);
        };
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The 1-based index of the task to mark as done.
     * @return {@code true} if the task was successfully marked, {@code false} otherwise.
     */
    public Boolean markTask(int index) {
        if (VALIDATE.outOfBound(TASKS.size(), index)) {
            TASKS.get(index - 1).markDone();
            return true;
        }
        return false;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The 1-based index of the task to unmark.
     * @return {@code true} if the task was successfully unmarked, {@code false} otherwise.
     */
    public Boolean unmarkTask(int index) {
        if (VALIDATE.outOfBound(TASKS.size(), index)) {
            TASKS.get(index - 1).markNotDone();
            return true;
        }
        return false;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The 1-based index of the task to delete.
     * @return {@code true} if the task was successfully deleted, {@code false} otherwise.
     */
    public Boolean deleteTask(int index) {
        if (VALIDATE.outOfBound(TASKS.size(), index)) {
            TASKS.remove(index - 1);
            return true;
        }
        return false;
    }

    /**
     * Adds a new task to the list, ensuring no duplicates are added.
     *
     * @param newTask The task to be added.
     * @return {@code true} if the task was successfully added, {@code false} if it's a duplicate.
     */
    public Boolean addTask(Task newTask) {
        if (VALIDATE.detectDuplicates(TASKS, newTask)) {
            return false;
        }
        TASKS.add(newTask);
        return true;
    }

    /**
     * Searches for tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of tasks that match the keyword, or {@code null} if none are found.
     */
    public String findTask(String keyword) {
        String foundTasks = TASKS.stream()
                .filter(task -> task.getDesc().contains(keyword))
                .map(task -> (TASKS.indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));

        if (foundTasks.isEmpty()) {
            return null;
        }
        return foundTasks;
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks in the list, or {@code null} if the list is empty.
     */
    public String listTask() {
        String result = IntStream.range(0, TASKS.size())
                .mapToObj(i -> (i + 1) + ". " + TASKS.get(i))
                .collect(Collectors.joining("\n"));

        if (TASKS.isEmpty()) {
            return null;
        }

        return result;
    }
}
