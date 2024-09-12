package rei;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class for tasks.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs abstract task instance from the task prompt.
     * isDone set to false (default).
     * @param task task prompt to be stored in taskName.
     */
    private Task(String task) {
        this.taskName = task;
        this.isDone = false;
    }

    /**
     * Returns the current status of task indicating whether
     * it's done.
     * @return true/false whether the task is done.
     *
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done yet.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns how the task will be displayed to user.
     * @return string representation of the task
     */
    public String printTask() {
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }

    /**
     * Creates a new instance of ToDo
     * @param task task prompt to be stored in taskName.
     * @return a new instance of ToDo with the corresponding taskName.
     */
    public static Task createToDo(String task) {
        return new ToDo(task);
    }

    /**
     * Creates a new instance of Deadline.
     * @param task task prompt to be stored in taskName.
     * @param deadline the deadline of the task.
     * @return a new instance of Deadline with the corresponding
     * taskName and deadline.
     */
    public static Task createDeadline(String task, LocalDateTime deadline) {
        return new Deadline(task, deadline);
    }

    /**
     * Creates a new instance of Event.
     * @param task task prompt to be stored in taskName.
     * @param from the start time of the event.
     * @param to the end time of the event.
     * @return a new instance of Event with the corresponding
     * taskName, start time, and end time.
     */
    public static Task createEvent(String task, LocalDateTime from, LocalDateTime to) {
        return new Event(task, from, to);
    }

    /**
     * Abstract method to return how a task is stored
     * in the database.
     * @return a string to store the task in the database.
     */
    public abstract String toStoringFormat();


    /**
     * ToDo class that inherits from Task.
     */
    private static class ToDo extends Task {
        private ToDo(String task) {
            super(task);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", this.printTask());
        }

        @Override
        public String toStoringFormat() {
            return String.format("T | %d | %s", this.isDone() ? 1 : 0, super.taskName);
        }
    }

    /**
     * Deadline class that inherits from Task.
     */
    private static class Deadline extends Task {
        private LocalDateTime deadline;
        private DateTimeFormatter outputFormat
                = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss");
        private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private Deadline(String task, LocalDateTime deadline) {
            super(task);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("[D]%s (by: %s)", this.printTask(), this.deadline.format(outputFormat));
        }

        @Override
        public String toStoringFormat() {
            return String.format("D | %d | %s| %s", this.isDone() ? 1 : 0, super.taskName, this.deadline);
        }
    }

    /**
     * Event class that inherits from Task.
     */
    private static class Event extends Task {
        private LocalDateTime from;
        private LocalDateTime to;
        private DateTimeFormatter outputFormat
                = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss");
        private Event(String task, LocalDateTime from, LocalDateTime to) {
            super(task);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %s to: %s)", this.printTask(),
                    this.from.format(outputFormat), this.to.format(outputFormat));
        }

        @Override
        public String toStoringFormat() {
            return String.format("E | %d | %s| %s to %s", this.isDone() ? 1 : 0, super.taskName,
                    this.from, this.to);
        }
    }

}
