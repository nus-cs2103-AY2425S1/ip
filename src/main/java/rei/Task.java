package rei;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract class for tasks.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    // The use of HashSet is inspired by https://teamtreehouse.com/community/java-data-structures-efficiency-add-tags-to-course
    private Set<String> tags;

    /**
     * Constructs abstract task instance from the task prompt.
     * isDone set to false (default).
     * @param task task prompt to be stored in taskName.
     */
    private Task(String task) {
        this.taskName = task;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Get the task name
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
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
     * Adds a tag to this Task
     * @param tag
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Adds a list of tags to this Task
     * @param tags
     */
    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Checks if the Task contains the tag
     * @param tag
     * @return true if the Task has the tag, false otherwise
     */
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    /**
     * String representation of all the tags
     * @return all the tags
     */
    public String allTagsToString() {
        String[] allTags = new String[tags.size()];
        return String.join(" ", tags.toArray(allTags));
    }



    /**
     * ToDo class that inherits from Task.
     */
    protected static class ToDo extends Task {
        private static final String TODO_PREFIX = "[T]";
        private ToDo(String task) {
            super(task);
        }
        @Override
        public String toString() {
            return String.format("%s%s",TODO_PREFIX, this.printTask());
        }

        @Override
        public String toStoringFormat() {
            return String.format("T | %d | %s | tags:%s", this.isDone() ? 1 : 0, super.taskName, super.allTagsToString());
        }
    }

    /**
     * Deadline class that inherits from Task.
     */
    protected static class Deadline extends Task {
        private static final String DEADLINE_PREFIX = "[D]";
        private LocalDateTime deadline;
        private DateTimeFormatter outputFormat
                = DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss");
        private Deadline(String task, LocalDateTime deadline) {
            super(task);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("%s%s (by: %s)", DEADLINE_PREFIX, this.printTask(), this.deadline.format(outputFormat));
        }

        @Override
        public String toStoringFormat() {
            return String.format("D | %d | %s | %s | tags:%s", this.isDone() ? 1 : 0, super.taskName, this.deadline, super.allTagsToString());
        }
    }

    /**
     * Event class that inherits from Task.
     */
    protected static class Event extends Task {
        private static final String EVENT_PREFIX = "[E]";
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
            return String.format("%s%s (from: %s to: %s)", EVENT_PREFIX, this.printTask(),
                    this.from.format(outputFormat), this.to.format(outputFormat));
        }

        @Override
        public String toStoringFormat() {
            return String.format("E | %d | %s | %s to %s | tags:%s", this.isDone() ? 1 : 0, super.taskName,
                    this.from, this.to, super.allTagsToString());
        }
    }

}
