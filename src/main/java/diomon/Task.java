package diomon;

import diomon.parser.Parser;

import java.time.LocalDate;

/**
 * The {@code Task} class represents a generic task with a description and completion status.
 * It serves as a base class for more specific tasks like {@link Todo}, {@link Event}, and {@link Deadline}.
 */
public class Task {
    String description;
    public static final String COMPLETEICON = "X";
    public static final String INCOMPLETEICON = " ";
    public static final String TYPEICON = " ";
    boolean completed;

    /**
     * Enum representing the types of tasks supported.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE,
    }

    /**
     * Constructs a {@code Task} with the given description. By default, the task is not completed.
     *
     * @param task The description of the task.
     */
    public Task(String task){
        this.description = task;
        this.completed = false;
    }

    /**
     * Constructs a {@code Task} with the given completion status and description.
     *
     * @param complete     Whether the task is completed or not.
     * @param description  The description of the task.
     */
    public Task(boolean complete, String description) {
        this.completed = complete;
        this.description = description;

    }

    /**
     * Creates a new task based on the provided type and description.
     * Parses the input for more complex task types like {@link Deadline} and {@link Event}.
     *
     * @param task The description of the task.
     * @param type The type of task to create (TODO, DEADLINE, or EVENT).
     * @return A new {@code Task}, {@code Todo}, {@code Event}, or {@code Deadline} object depending on the type.
     * @throws RuntimeException If the task format is invalid for {@link Event} or {@link Deadline}.
     */
    public static Task of(String task, TaskType type) {
        switch (type) {
            case DEADLINE:
                String[] taskArray = task.split("/", 2);
                if (taskArray.length == 2){
                    String[] by = taskArray[1].split(" ", 2);
                    if (by[0].equalsIgnoreCase("by") && by.length == 2) {
                        return new Deadline(taskArray[0], LocalDate.parse(by[1], Parser.DATEFORMATTER));
                    }
                }
                throw new RuntimeException();

            case EVENT:
                String[] taskArr = task.split("/", 3);
                if (taskArr.length == 3){
                    String[] from = taskArr[1].split(" ", 2);
                    String[] to = taskArr[2].split(" ", 2);
                    if (from[0].equalsIgnoreCase("from") && from.length == 2 && to[0].equalsIgnoreCase("to") && to.length == 2) {
                        return new Event(taskArr[0],
                                LocalDate.parse(from[1].replaceAll(" ",""), Parser.DATEFORMATTER),
                                LocalDate.parse(to[1].replaceAll(" ",""),Parser.DATEFORMATTER));
                    }
                }
                throw new RuntimeException();
            case TODO:
                return new Todo(task);
            default:
                return new Task(task);
        }

    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Gets the status icon of the task, which is "X" if completed and a blank space otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return this.completed ? COMPLETEICON : INCOMPLETEICON;
    }

    /**
     * Gets the type icon of the task. This can be overridden by subclasses to provide specific task types.
     *
     * @return The type icon of the task (defaults to a blank space).
     */
    public String getTypeIcon() {
        return TYPEICON;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Converts the task into a format suitable for storage in a file.
     *
     * @return A string formatted for storage, representing the type, status, and description of the task.
     */
    public String toStorageString(){
        return String.format("%s|%s|%s", TYPEICON, getStatusIcon(),this.description);
    }

    /**
     * Compares the current task with another object for equality. Two tasks are considered equal if
     * their descriptions and completion statuses are the same.
     *
     * @param other The object to compare with.
     * @return {@code true} if the tasks are equal, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Task temp) {
            return temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}
