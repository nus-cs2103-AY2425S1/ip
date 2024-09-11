package task;

import exception.DukeException;
import exception.FormatException;
import exception.NoInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task class is used to represent a task in Duke.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    private static final int TODO_PRIORITY = 1;
    private static final int DEADLINE_PRIORITY = 2;
    private static final int EVENT_PRIORITY = 3;


    /**
     * Abstract method toFileString is used to convert the task to string
     * for storage in file with Storage class.
     * @return The file string representation of the task.
     */
    public abstract String toFileString();

    @Override
    public abstract int compareTo(Task other); 

    public abstract int getPriority();
        

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
        if (parts.length < 2) {
            throw new NoInputException();
        }
    
        String command = parts[0].toLowerCase();
        String content = parts[1].trim();
    
        switch (command) {
            case "todo":
                return createTodo(content);
            case "deadline":
                return createDeadline(content);
            case "event":
                return createEvent(content);
            default:
                throw new FormatException("Unknown task type: " + command);
        }
    }
    
    private static Todo createTodo(String content) throws NoInputException {
        if (content.isEmpty()) {
            throw new NoInputException();
        }
        return new Todo(content);
    }
    
    private static Deadline createDeadline(String content) throws FormatException {
        Matcher matcher = Pattern.compile("(.+) /by (.+)").matcher(content);
        if (!matcher.matches()) {
            throw new FormatException("deadline");
        }
        return new Deadline(matcher.group(1).trim(), matcher.group(2).trim());
    }
    
    private static Event createEvent(String content) throws FormatException {
        Matcher matcher = Pattern.compile("(.+) /from (.+) /to (.+)").matcher(content);
        if (!matcher.matches()) {
            throw new FormatException("event");
        }
        return new Event(matcher.group(1).trim(), matcher.group(2).trim(), matcher.group(3).trim());
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

        @Override
        public int getPriority() {
            return TODO_PRIORITY;
        }

        @Override
        public int compareTo(Task other) {
            if (this.getPriority() != other.getPriority()) {
                return this.getPriority() - other.getPriority();
            }
            return this.description.compareTo(other.description);
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

        @Override
        public int getPriority() {
            return DEADLINE_PRIORITY;
        }

        @Override
        public int compareTo(Task other) {
            if (this.getPriority() != other.getPriority()) {
                return this.getPriority() - other.getPriority();
            }
            if (other instanceof Deadline) {
                return this.by.compareTo(((Deadline) other).by);
            }
            return this.description.compareTo(other.description);
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

        @Override
        public int getPriority() {
            return EVENT_PRIORITY;
        }

        @Override
        public int compareTo(Task other) {
            if (this.getPriority() != other.getPriority()) {
                return this.getPriority() - other.getPriority();
            }
            if (other instanceof Event) {
                return this.from.compareTo(((Event) other).from);
            }
            return this.description.compareTo(other.description);
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






