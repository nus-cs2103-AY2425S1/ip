import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    final String desc;
    boolean isDone;

    Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    void markDone() {
        this.isDone = true;
    }

    void markNotDone() {
        this.isDone = false;
    }

    String getType() {
        return "[ ]";
    }

    @Override
    public String toString() {
        return getType() + (isDone ? "[X] " : "[ ] ") + desc;
    }

    static class ToDo extends Task {
        ToDo(String desc) {
            super(desc);
        }

        @Override
        String getType() {
            return "[T]";
        }
    }

    static class Deadline extends Task {
        LocalDateTime by;

        Deadline(String desc, LocalDateTime by) {
            super(desc);
            this.by = by;
        }

        @Override
        String getType() {
            return "[D]";
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return super.toString() + " (by: " + by.format(formatter) + ")";
        }
    }

    static class Event extends Task {
        LocalDateTime from;
        LocalDateTime to;

        Event(String desc, LocalDateTime from, LocalDateTime to) {
            super(desc);
            this.from = from;
            this.to = to;
        }

        @Override
        String getType() {
            return "[E]";
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
        }
    }

}
