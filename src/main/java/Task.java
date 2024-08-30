/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status to set.
     */
    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s", mark, this.description);
    }

    /**
     * Creates a Task object from its string representation.
     *
     * @param taskString The string representation of the task.
     * @return The Task object.
     * @throws IllegalArgumentException If the string representation is null or empty.
     */
    public static Task fromString(String taskString) {
        if (taskString == null || taskString.isEmpty()) {
            throw new IllegalArgumentException("String representation cannot be null or empty");
        }

        boolean isDone = taskString.charAt(8) == 'X'; // The status is represented by the second character
        String info = taskString; // The description starts after the brackets

        if (taskString.charAt(4) == 'D') {
            String deadlineDesc = info.substring(11, info.toLowerCase().indexOf(" (by:"));
            String due = info.substring(info.toLowerCase().indexOf(" (by:") + 6, info.length() - 1);
            Deadline deadline = new Deadline(deadlineDesc, due);
            deadline.setIsDone(isDone);
            return deadline;

        } else if (taskString.charAt(4) == 'E') {
            String eventDesc = info.substring(11, info.toLowerCase().indexOf(" (from:"));
            String from = info.substring(info.toLowerCase().indexOf(" (from:") + 8, info.toLowerCase().indexOf("to:"));
            String to = info.substring(info.toLowerCase().indexOf("to:") + 4, info.length() - 1);
            Event event = new Event(eventDesc, from, to);
            event.setIsDone(isDone);
            return event;

        } else {
            ToDo todo = new ToDo(info.substring(11));
            todo.setIsDone(isDone);
            return todo;
        }
    }
}
