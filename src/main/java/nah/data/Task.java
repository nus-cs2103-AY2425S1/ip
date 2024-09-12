package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class serves as an abstract base class for different types
 * of tasks that can be added to a task management application.
 * A task has a description and a completion status, which can be
 * marked as done or not done.
 *
 * <p>Subclasses of Task must implement the method brief, endTime,
 * isReferToTask and other methods to provide specific behavior
 * for different task types like deadlines, events, and todos.</p>
 *
 * @see Deadlines
 * @see Events
 * @see ToDos
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns if this task is done or not
     *
     * @return a boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done by setting the {@code isDone} flag to {@code true}.
     */

    public void mark() {
        isDone = true;
        assert isDone : "Failed to mark the task as done";
    }

    /**
     * Marks the task as not done by setting the {@code isDone} flag to {@code false}.
     */
    public void unMark() {
        isDone = false;
        assert !isDone : "Failed to unmark the task";
    }

    /**
     * Returns 1 if task id done, 0 otherwise.
     *
     * @return 1 or 0
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns the description of the task.
     *
     * @return a String
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Checks if a word appear in the description.
     *
     * @param word the word needs to be checked
     * @return a boolean value
     */
    public boolean isMatch(String word) {
        assert word != null && !word.trim().isEmpty() : "Word cannot be null or empty";
        if (isReferToTask(word)) {
            return true;
        }
        word = word.toLowerCase();
        String[] words = this.description.toLowerCase().split(" ", 2);
        while (words.length >= 2 && !words[1].trim().isEmpty()) {
            if (words[0].trim().equals(word)) {
                return true;
            }
            words = words[1].split(" ", 2);
        }
        if (words[0].trim().equals(word)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if one of these words appears the description.
     *
     * @param words a String that have 0, 1 or more than 2 words
     * @return a boolean value
     */
    public boolean isOneMatch(String words) {
        assert words != null : "Words cannot be null";
        if (words.trim().isEmpty()) {
            return true;
        }
        String[] wordsList = words.split(" ", 2);

        while (wordsList.length >= 2 && !wordsList[1].trim().isEmpty()) {
            if (isMatch(wordsList[0])) {
                return true;
            }
            wordsList = wordsList[1].split(" ", 2);
        }
        if (isMatch((wordsList[0]))) {
            return true;
        }
        return false;
    }

    /**
     * Returns a brief description of task to be store in a hard disk.
     *
     * @return a String
     */
    public abstract String brief();

    /**
     * Returns [X] if the task is done, [ ] otherwise.
     *
     * @return [X] or [ ]
     */
    private String getStatusMark() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Checks if the task has end time before the time.
     *
     * @return a LocalDateTime value
     */
    public abstract boolean isBefore(LocalDateTime due);


    /**
     * Checks if the String is the name of this task (eg: deadline, todo, event).
     *
     * @param s the String that need to be checked
     * @return a boolean value
     */
    public abstract boolean isReferingToTask(String s);

    /**
     * Returns String representation of the task
     *
     * @return a String
     */
    @Override
    public String toString() {
        return getStatusMark() + " " + this.description;
    }

    /**
     * The Deadlines class represents a task with a specific deadline.
     * It has a LocalDateTime field to store the deadline.
     *
     */
    public static class Deadlines extends Task {

        private LocalDateTime time;

        /**
         * Constructor.
         * @param content the description of the task
         * @param by deadline of the task
         */
        public Deadlines(String content, LocalDateTime by) {
            super(content);
            assert by != null : "deadline time cannot be null";
            this.time = by;
        }

        /**
         * Checks is the deadline of this task is before due.
         *
         * @return a LocalDateTime value
         */
        @Override
        public boolean isBefore(LocalDateTime due) {
            return this.time.isBefore(due);
        }

        /**
         * Return a brief description of task to be store in a hard disk.
         *
         * @return a String
         */
        @Override
        public String brief() {
            return "D | " + super.getStatus() + " | " + super.getTask() + " | "
                   + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        }

        /**
         * Checks if this String to lowercase is deadline.
         *
         * @param s the String that need to be checked
         * @return a boolean value
         */
        @Override
        public boolean isReferingToTask(String s) {
            assert s != null : "Task reference cannot be null";
            return s.trim().toLowerCase().equals("deadline");
        }

        /**
         * Returns String representation.
         *
         * @return a String
         */
        @Override
        public String toString() {
            return "[D] "
                    + super.toString()
                    + " (by: "
                    + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                    + ")";
        }
    }

    /**
     * The Events class represents a task that occurs during a specific
     * time period, with a start and end time. It has 2 LocalDateTime fields
     * to store the start and end times.
     *
     * <p>This class provides methods to retrieve the end time, format the task
     * for storage, and check if the task type is "event".</p>
     */
    public static class Events extends Task {

        private LocalDateTime start;
        private LocalDateTime end;

        /**
         * Constructor.
         * @param content the description of the task
         * @param start start of the event
         * @param end end of the event
         */
        public Events(String content, LocalDateTime start, LocalDateTime end) {
            super(content);
            assert start != null && end != null : "Start and End times cannot be null";
            assert start.isBefore(end) : "Start time must be before End time";
            this.start = start;
            this.end = end;
        }

        /**
         * Checks if the end of event is before due.
         *
         * @return a LocalDateTime value
         */
        @Override
        public boolean isBefore(LocalDateTime due) {
            return this.end.isBefore(due);
        }

        /**
         * Return a brief description of task to be stored in a hard disk.
         *
         * @return a String
         */
        @Override
        public String brief() {
            return "E | " + super.getStatus() + " | " + super.getTask() + " | "
                    + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                    + " | " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        }

        /**
         * Checks if the String to lowercase is "event".
         *
         * @param s the String that need to be checked
         * @return a boolean value
         */
        @Override
        public boolean isReferingToTask(String s) {
            assert s != null : "Task reference cannot be null";
            return s.trim().toLowerCase().equals("event");
        }

        /**
         * Returns String representation.
         *
         * @return a String
         */
        @Override
        public String toString() {
            return "[E] "
                    + super.toString()
                    + " (from: "
                    + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                    + " to: "
                    + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                    + ")";
        }
    }

    /**
     * The ToDos class represents a task without a specific deadline.
     *
     * <p>If the task type is "todo". The end time for a ToDo is considered to be infinite.</p>
     */
    public static class ToDos extends Task {
        /**
         * Constructor.
         * @param content the description of the task
         */
        public ToDos(String content) {

            super(content);
        }
        /**
         * Return a brief description of this task.
         *
         * @return a String
         */
        @Override
        public String brief() {
            return "T | " + super.getStatus() + " | " + super.getTask();
        }

        /**
         * Checks if the String to lowercase is "todo".
         *
         * @param s the String that need to be checked
         * @return a boolean value
         */
        @Override
        public boolean isReferingToTask(String s) {
            assert s != null : "Task reference cannot be null";
            return s.trim().toLowerCase().equals("todo");
        }

        /**
         * Returns false because this task has no due time.
         *
         * @return a LocalDateTime value.
         */
        @Override
        public boolean isBefore(LocalDateTime due) {
            return false;
        }

        /**
         * Returns String representation.
         *
         * @return a String
         */
        @Override
        public String toString() {
            return "[T] " + super.toString();
        }
    }
}
