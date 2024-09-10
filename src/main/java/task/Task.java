package task;

import exception.DukeException;
import exception.FormatException;
import exception.NoInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task class is used to represent a task in Duke.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Abstract method toFileString is used to convert the task to string
     * for storage in file with Storage class.
     * @return The file string representation of the task.
     */
    public abstract String toFileString();

    /**
     * Static method fromFileString is used to convert a string from file
     * to a task.
     * @param s The file string representation of the task.
     * @return The UI version of the task string.
     */
    public static Task fromFileString(String s) {
        String[] parts = s.split("\\|");
        String type = parts[0];
        String isDone = parts[1];
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new FormatException("Unknown task type: " + type);
        }
        task.setIsDone(isDone.equals("1"));
        return task;
    }

    /**
     * Constructor for Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Static method of is used to create a task from a string
     * for giving task creation instructions.
     * @param s User's instruction for task creation.
     * @return The task object.
     * @throws DukeException If the instruction is of wrong format.
     */

    public static Task of(String s) throws DukeException {
        s = s.trim();
        if (s.isEmpty()) {
            throw new NoInputException();
        }

        String[] parts = s.split(" ", 2);
        String command = parts[0].toLowerCase();

        if (command.equals("todo")) {
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NoInputException();
            }
            return new Todo(parts[1].trim());

        } else if (command.equals("deadline")) {
            Pattern deadlinePattern = Pattern.compile("(.+) /by (.+)");
            Matcher deadlineMatcher = deadlinePattern.matcher(parts[1]);
            if (!deadlineMatcher.matches()) {
                throw new FormatException("deadline");
            }
            String description = deadlineMatcher.group(1).trim();
            String by = deadlineMatcher.group(2).trim();
            return new Deadline(description, by);

        } else if (command.equals("event")) {
            Pattern eventPattern = Pattern.compile("(.+) /from (.+) /to (.+)");
            Matcher eventMatcher = eventPattern.matcher(parts[1]);
            if (!eventMatcher.matches()) {
                throw new FormatException("event");
            }
            String description = eventMatcher.group(1).trim();
            String from = eventMatcher.group(2).trim();
            String to = eventMatcher.group(3).trim();
            return new Event(description, from, to);

        } else {
            throw new NoInputException();
        }
    }

    /**
     * Method getStatusIcon is used to get the status icon of the task.
     * @return The status icon of the task. "X" for done task, and " " for undone task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method setIsDone is used to set the status of the task.
     * @param val The status of the task.
     */
    public void setIsDone(boolean val) {
        isDone = val;
    }

    /**
     * Method isDone is used to get the status of the task completion.
     * @return The status of the task completion.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public static class Todo extends Task {
        /**
         * Constructor for Todo.
         * @param description The description of the todo task.
         */
        public Todo(String description) {
            super(description);
        }

        /**
         * Method toFileString is used to convert the todo task to string
         * for storage in file with Storage class.
         * @return The file string representation of the todo task.
         */
        @Override
        public String toFileString() {
            return String.format("T|%d|%s", isDone ? 1 : 0, description);
        }

        @Override
        public String toString() {
            return String.format("[T][%s] %s",
                    super.getStatusIcon(), super.description);
        }
    }

    public static class Deadline extends Task {
        String by;
        /**
         * Constructor for Deadline.
         * @param description The description of the deadline task.
         * @param by The deadline of the deadline task.
         */
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        /**
         * Method toFileString is used to convert the deadline task to string
         * for storage in file with Storage class.
         * @return The file string representation of the deadline task.
         */
        @Override
        public String toFileString() {
            return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, by);
        }

        @Override
        public String toString() {
            return String.format("[D][%s] %s (by: %s)",
                    super.getStatusIcon(), super.description, by);
        }
    }

    public static class Event extends Task {
        String from;
        String to;
        /**
         * Constructor for Event.
         * @param description The description of the event task.
         * @param from The start time of the event task.
         * @param to The end time of the event task.
         */
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        /**
         * Method toFileString is used to convert the event task to string
         * for storage in file with Storage class.
         * @return The file string representation of the event task.
         */
        @Override
        public String toFileString() {
            return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, from, to);
        }

        @Override
        public String toString() {
            return String.format("[T][%s] %s (from: %s to: %s)",
                    super.getStatusIcon(), super.description, from, to);
        }
    }
}






