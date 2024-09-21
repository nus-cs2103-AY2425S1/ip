package alexer.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a new deadline task
     *
     * @param description The description of the task, inherited from Task class
     * @param deadline The deadline in DateTime of the task deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        type = TaskType.DEADLINE;
    }

    /**
     * Creates a deadline task from a task string, used
     * when loading tasks from the save file.
     *
     * @param taskString the raw task string saved to file
     * @return a new deadline task if the task string is valid, null otherwise
     */
    public static Deadline fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 4) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime;
        try {
             dateTime = LocalDateTime.parse(parts[3], formatter);
        } catch (DateTimeException e) {
            return null;
        }
        Deadline deadline = new Deadline(parts[2], dateTime);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }

        return deadline;
    }

    /** Returns the task type prefix **/
    protected String getTaskType() {
        return "D";
    }

    /**
     * Converts the task into the task string form for serialisation
     * and saving to file. This also ensures a balance between human readability
     * and ease of machine parsing
     *
     * @return the task string of the current task
     */
    @Override
    public String toTaskString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("%s|%d|%s|%s",
                getTaskType(), isDone ? 1 : 0, description,
                formatter.format(deadline));
    }

    /**
     * Converts the task into string form for printing, and
     * primarily focuses on human readability.
     *
     * @return the human-readable string form of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return String.format("[%s] %s (by: %s)", getTaskType(), super.toString(),
                formatter.format(deadline).replace("am", "AM").replace("pm","PM"));
    }
}
