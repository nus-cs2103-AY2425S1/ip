package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    }

    /**
     * Marks the task as not done by setting the {@code isDone} flag to {@code false}.
     */
    public void unMark() {
        isDone = false;
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
     * Returns the end time of the task.
     *
     * @return a LocalDateTime value
     */
    public abstract LocalDateTime endTime();

    /**
     * Checks if the String is the name of this task (eg: deadline, todo, event).
     *
     * @param s the String that need to be checked
     * @return a boolean value
     */
    public abstract boolean isReferToTask(String s);

    /**
     * Returns String representation of the task
     *
     * @return a String
     */
    @Override
    public String toString() {
        return getStatusMark() + " " + this.description;
    }

    public static class Deadlines extends Task {

        private LocalDateTime time;

        public Deadlines(String content, LocalDateTime by) {
            super(content);
            this.time = by;
        }

        /**
         * Returns the deadline of this task.
         *
         * @return a LocalDateTime value
         */
        @Override
        public LocalDateTime endTime() {
            return this.time;
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
        public boolean isReferToTask(String s) {
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

    public static class Events extends Task {

        private LocalDateTime start, end;

        public Events(String content, LocalDateTime start, LocalDateTime end) {
            super(content);
            this.start = start;
            this.end = end;
        }

        /**
         * Returns the end time of the task.
         *
         * @return a LocalDateTime value
         */
        @Override
        public LocalDateTime endTime() {
            return this.end;
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
        public boolean isReferToTask(String s) {
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

    public static class ToDos extends Task {
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
        public boolean isReferToTask(String s) {
            return s.trim().toLowerCase().equals("todo");
        }

        /**
         * Returns the deadline of this task, which is infinite.
         *
         * @return a LocalDateTime value.
         */
        @Override
        public LocalDateTime endTime() {
            return LocalDateTime.MAX;
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
