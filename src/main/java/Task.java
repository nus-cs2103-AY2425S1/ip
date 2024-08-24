/**
 * The Task class represents a general task with a name and a completion status.
 * It provides methods to mark the task as completed or incomplete, and factory methods
 * to create specific types of tasks such as DeadlineTask, EventTask, and ToDoTask.
 */
public class Task {
    private String name;
    private Boolean isCompleted;


    /**
     * Constructs a Task object with the specified name.
     * The task is initially marked as incomplete.
     * @param name the name or description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Creates a new DeadlineTask with the specified name and deadline.
     * @param name the name or description of the task
     * @param deadline the deadline for the task
     * @return a new DeadlineTask object
     */
    public static Task deadlineTask(String name, String deadline) {
        return new DeadlineTask(name, deadline);
    }

    /**
     * Creates a new EventTask with the specified name, start time, and end time.
     * @param name the name or description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return a new EventTask object
     */
    public static Task eventTask(String name, String startTime, String endTime) {
        return new EventTask(name, startTime, endTime);
    }

    /**
     * Creates a new ToDoTask with the specified name.
     * @param name the name or description of the task
     * @return a new ToDoTask object
     */
    public static Task toDoTask(String name) {
        return new ToDoTask(name);
    }

    /**
     * Returns a string representation of the task, including its completion status and name.
     * The format is "[ ] name" if the task is incomplete, or "[X] name" if the task is completed.
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return name;
    }
}
