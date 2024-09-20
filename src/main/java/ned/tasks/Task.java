package ned.tasks;

import java.util.regex.Pattern;

/**
 * The {@code Task} class is an abstract representation of a task in the application.
 * It encapsulates common attributes and behaviors shared by all task types, such as
 * the task description, completion status, and task type indicator.
 *
 * <p>Subclasses of {@code Task} are expected to implement the {@link #toTextForm()} method,
 * which converts the task into a string suitable for file storage or serialization.
 *
 * <p>Key functionalities include:
 * <ul>
 *   <li>Maintaining the task's description and whether it is completed.</li>
 *   <li>Methods to mark the task as done or not done via {@link #markAsDone()} and {@link #markAsNotDone()}.</li>
 *   <li>Providing a string representation of the task for display purposes through {@link #toString()}.</li>
 *   <li>Checking if the task description matches a given search pattern using
 *   {@link #isMatchingPattern(String)}.</li>
 *   <li>Utility method {@link #checkSizeOfInput(String[])} to count non-blank strings in an array.</li>
 * </ul>
 *
 * <p>This class cannot be instantiated directly and should be extended by specific task types
 * such as {@code ToDo}, {@code Deadline}, or {@code Event}, which provide additional properties
 * and behaviors specific to those task types.
 */
public abstract class Task {

    protected boolean isDone;
    protected String taskDescription;
    protected String taskType;

    protected Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    };

    /**
     * Marks the task as completed by setting {@code isDone} to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    };

    /**
     * Marks the task as not completed by setting {@code isDone} to {@code false}.
     */
    public void markAsNotDone() {
        this.isDone = false;
    };

    /**
     * Counts the number of non-blank strings in the provided array.
     *
     * @param strArr The array of strings to be checked.
     * @return The count of non-blank strings.
     */
    public static int checkSizeOfInput(String[] strArr) {
        int count = 0;
        for (String s : strArr) {
            if (!s.isBlank()) {
                count += 1;
            }
        }
        return count;
    }
    @Override
    public String toString() {
        String marker = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s", this.taskType, marker, this.taskDescription);
    }

    /**
     * Checks if the task description matches the given search pattern.
     *
     * @param searchPattern The regex pattern to match against the task description.
     * @return {@code true} if the description matches the pattern; {@code false} otherwise.
     */
    public boolean isMatchingPattern(String searchPattern) {
        return Pattern.matches(searchPattern, this.taskDescription);
    }
    /**
     * Converts the task into a text form suitable for file storage or loading.
     *
     * @return A string representation of the task for storage purposes.
     */
    public abstract String toTextForm();

}
