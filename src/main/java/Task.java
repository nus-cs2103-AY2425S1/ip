import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task; true if the task is done, false otherwise.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and a space (" ") indicates that it is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public static Task parseStringToTask(String string) {
        String regex = "\\d+\\. \\[(?<taskType>[A-Z])\\]\\[(?<status>[ X])\\] (?<name>[^\\(]+)(?:\\(by: (?<by>[^\\)]+)\\))?(?:\\(from: (?<from>[^\\)]+) to: (?<to>[^\\)]+)\\))?";

        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(string);

        if (m.find()) {
            String taskType = m.group("taskType");      // Task type (e.g., "T")
            boolean isDone = m.group("status").trim().equals("X"); // Task completion status (e.g., "X" or " ")
            String taskName = m.group("name").trim(); // Task name
            String fromTime = m.group("from");      // From timeframe (if present)
            String toTime = m.group("to");        // To timeframe (if present)
            String byTime = m.group("by");        // By timeframe (if present)

            switch (taskType) {
            case "T":
                Todo newTodo = new Todo(taskName);
                if (isDone) {
                    newTodo.markAsDone();
                }
                return newTodo;
            case "D":
                Deadline newDeadline = new Deadline(taskName, byTime);
                if (isDone) {
                    newDeadline.markAsDone();
                }
                return newDeadline;
            case "E":
                Event newEvent = new Event(taskName, fromTime, toTime);
                if (isDone) {
                    newEvent.markAsDone();
                }
                return newEvent;
            }

        }

        return null;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}