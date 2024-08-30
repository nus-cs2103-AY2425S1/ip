package nah.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
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
     * Return a brief description of task
     * @return
     */
    public abstract String brief();
    private String getStatusMark() {
        return isDone ? "[X]" : "[ ]";
    }
    public abstract LocalDateTime endTime();

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
