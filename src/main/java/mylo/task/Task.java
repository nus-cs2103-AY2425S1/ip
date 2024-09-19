package mylo.task;

import mylo.data.InsufficientInfoException;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Represents a generic task in the task management system.
 * <p></p>
 * <p>The {@code Task} class serves as a base class for different types of tasks,
 * such as TODOs, events, and deadlines. It contains common attributes and methods
 * that apply to all task types, such as a title and completion status.</p>
 * <p></p>
 * <p>Tasks can be created from user input or restored from storage. The class provides
 * methods to mark tasks as done or not done, as well as to format task data for
 * storage and display purposes.</p>
 *
 * @author cweijin
 */
public abstract class Task {
    private final String TITLE;
    private boolean isCompleted;

    /**
     * Constructs a {@code Task} with the specified title and sets the task as incomplete.
     *
     * @param title The title or description of the task.
     */
    public Task(String title) {
        this(title, false);
    }

    /**
     * Constructs a {@code Task} with the specified title and completion status.
     * <p></p>
     * <p>This constructor allows specifying whether the task is done at the time of creation.</p>
     *
     * @param title  The title or description of the task.
     * @param isDone Whether the task is marked as done upon creation.
     */
    public Task(String title, boolean isDone) {
        this.TITLE = title;
        this.isCompleted = isDone;
    }

    /**
     * Creates a task of the specified type from the provided information string.
     * <p></p>
     * <p>This static method takes a string containing task information and a {@code TaskType}
     * to create the appropriate task instance. It checks for necessary details and
     * throws exceptions if insufficient information is provided.</p>
     *
     * @param info The information string containing task details.
     * @param type The type of the task to create.
     * @return A new {@code Task} object of the specified type.
     * @throws InsufficientInfoException If not enough information is provided for task creation.
     * @throws IllegalValueException     If the provided values are invalid.
     */
    public static Task of(String info, TaskType type) throws InsufficientInfoException, IllegalValueException {

        return switch (type) {
        case TODO -> new Todo(info);
        case EVENT -> {
            String[] split = info.split(" /from ");
            if (split.length < 2) {
                throw new InsufficientInfoException(TaskType.EVENT);
            }
            String[] fromTo = split[1].split(" /to ");
            if (fromTo.length < 2) {
                throw new InsufficientInfoException(TaskType.EVENT);
            }

            yield new Event(split[0], fromTo[0], fromTo[1]);
        }
        case DEADLINE -> {
            String[] split = info.split(" /by ");
            if (split.length < 2) {
                throw new InsufficientInfoException(TaskType.EVENT);
            }
            yield new Deadline(split[0], split[1]);
        }
        };
    }

    /**
     * Creates a task from an array of strings representing the task's attributes.
     * <p></p>
     * <p>This static method takes an array of strings and returns a new {@code Task} object
     * based on the provided attributes, including the task type and status.</p>
     *
     * @param info An array of strings containing task attributes.
     * @return A new {@code Task} object created from the provided attributes.
     * @throws IllegalValueException If the provided values are invalid.
     */
    public static Task of(String[] info) throws IllegalValueException {
        return switch (info[0]) {
        case "TODO" -> new Todo(info[2], Boolean.parseBoolean(info[1]));
        case "EVENT" -> new Event(info[2], info[3], info[4], Boolean.parseBoolean(info[1]));
        case "DEADLINE" -> new Deadline(info[2], info[3], Boolean.parseBoolean(info[1]));
        default -> throw new IllegalStateException("Unexpected value: " + info[0]);
        };
    }

    /**
     * Checks if the task title contains the specified keyword.
     *
     * @param keyword The keyword to search for within the task title.
     * @return {@code true} if the task title contains the keyword, {@code false} otherwise.
     */
    public boolean isMatch(String keyword) {
        return getTitle().toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Convert task data into a {@code String} to be stored in storage file.
     *
     * @return {@code String} containing data to the task.
     */
    public abstract String storageFormat();

    public String getTitle() {
        return this.TITLE;
    }

    public String completionStatus() {
        return this.isCompleted ? "True" : "False";
    }

    /**
     * Marks the task as done.
     * <p></p>
     * <p>This method sets the task's status to done and returns a confirmation message.</p>
     *
     * @return A message confirming the task has been marked as done.
     */
    public String markAsDone() {
        this.isCompleted = true;
        return "Nice! I've marked this task as done: \n" + this;
    }

    /**
     * Marks the task as not done.
     * <p></p>
     * <p>This method sets the task's status to not done and returns a confirmation message.</p>
     *
     * @return A message confirming the task has been marked as not done.
     */
    public String markAsUndone() {
        this.isCompleted = false;
        return "OK, I've marked this task as not done yet: \n" + this;
    }

    /**
     * Returns a status indicator string for the task.
     * <p></p>
     * <p>This method returns a string that indicates whether the task is done or not.</p>
     *
     * @return A string indicating the task's status: "[X] " if done, "[ ] " if not done.
     */
    private String getStatusIndicator() {
        return (isCompleted ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * <p></p>
     * <p>This method combines the task's status indicator and title into a single string.</p>
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIndicator() + this.getTitle();
    }
}
