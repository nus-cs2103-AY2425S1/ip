package ned.tasks;

import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.NedException;

/**
 * The {@code ToDo} class represents a basic task without any specific date or time associated with it.
 * It extends the {@code Task} class and specifies the task type as "T" for ToDo.
 *
 * <p>This class provides functionalities to:
 * <ul>
 *   <li>Create a new ToDo task with a description.</li>
 *   <li>Convert the task to a text form suitable for file storage via {@link #toTextForm()}.</li>
 *   <li>Check for equality between {@code ToDo} tasks using {@link #equals(Object)}.</li>
 * </ul>
 *
 * <p><strong>Usage Example:</strong>
 * <pre>{@code
 * try {
 *     ToDo todoTask = ToDo.createToDo("Read a book", false);
 *     System.out.println(todoTask);
 * } catch (NedException e) {
 *     System.out.println(e.getMessage());
 * }
 * }</pre>
 *
 * @see Task
 * @see NedException
 */
public class ToDo extends Task {

    private static final String TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE = "M'lord, this saved todo task has no "
            + "task description!";

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     * This constructor is protected and is intended to be called internally or by subclasses.
     *
     * @param taskDescription The description of the ToDo task.
     * @param isDone {@code true} if the task is completed; {@code false} otherwise.
     */
    protected ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.taskType = "T";
    }

    /**
     * Creates a new {@code ToDo} task with the specified description and completion status.
     * Validates that the task description is not blank before creating the task.
     *
     * @param taskDescription The description of the ToDo task.
     * @param taskStatus {@code true} if the task is completed; {@code false} otherwise.
     * @return A new {@code ToDo} task instance.
     * @throws NedException If the task description is blank.
     */
    public static ToDo createToDo(String taskDescription, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException(TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE);
        }
        return new ToDo(taskDescription, taskStatus);
    }

    /**
     * Converts the ToDo task into a text form suitable for file storage or serialization.
     *
     * @return A string representation of the task for storage purposes.
     */
    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("todo|%d|%s", status, this.taskDescription);
    }

    /**
     * Checks whether this {@code ToDo} task is equal to another object.
     * Two ToDo tasks are considered equal if they have the same description and completion status.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the tasks are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo typeCastedObj = (ToDo) obj;
        return typeCastedObj.taskDescription.equalsIgnoreCase(this.taskDescription);
    }

    private boolean isStatusEqual(ToDo typeCastedObj) {
        return typeCastedObj.isDone == this.isDone;
    }

    private boolean isTaskDescriptionEqual(ToDo typeCastedObj) {
        return typeCastedObj.taskDescription.equals(this.taskDescription);
    }
}
