package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String description;
    private boolean isDone = false;
    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Mark the task as done by setting the {@code isDone} flag to {@code true}.
     */

    public void mark() {
        isDone = true;
    }

    /**
     * Unmark the task by setting the {@code isDone} flag to {@code false}.
     */

    public void unMark() {
        isDone = false;
    }
    public int getStatus() {
        return isDone ? 1 : 0;
    }
    public String getTask() {
        return this.description;
    }

    /**
     * Check if a word appear in the description
     * @param word
     * @return
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
     * Check if one of these words match the description
     * @param words
     * @return
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
     * Return a brief description of task
     * @return
     */
    public abstract String brief();
    private String getStatusMark() {
        return isDone ? "[X]" : "[ ]";
    }
    public abstract LocalDateTime endTime();
    public abstract boolean isReferToTask(String s);
    /**
     * Return String representation
     * @return
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
         * Return the deadline
         * @return
         */
        @Override
        public LocalDateTime endTime() {
            return this.time;
        }

        /**
         * Return a brief description of task
         * @return
         */
        @Override
        public String brief() {
            return "D | " + super.getStatus() + " | " + super.getTask() + " | "
                   + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        }
        @Override
        public boolean isReferToTask(String s) {
            return s.trim().toLowerCase().equals("deadline");
        }
        /**
         * String representation
         * @return
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
         * Return the end time
         * @return
         */
        @Override
        public LocalDateTime endTime() {
            return this.end;
        }

        /**
         * Return a brief description of task
         * @return
         */
        @Override
        public String brief() {
            return "E | " + super.getStatus() + " | " + super.getTask() + " | "
                    + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                    + " | " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        }

        @Override
        public boolean isReferToTask(String s) {
            return s.trim().toLowerCase().equals("event");
        }

        /**
         * Return String representation
         * @return
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
         * Return a brief description of task
         * @return
         */
        @Override
        public String brief() {
            return "T | " + super.getStatus() + " | " + super.getTask();
        }

        @Override
        public boolean isReferToTask(String s) {
            return s.trim().toLowerCase().equals("todo");
        }

        /**
         * Return deadline, which is infinite
         * @return
         */
        @Override
        public LocalDateTime endTime() {
            return LocalDateTime.MAX;
        }

        /**
         * Return String representation
         * @return
         */
        @Override
        public String toString() {
            return "[T] " + super.toString();
        }
    }
}
