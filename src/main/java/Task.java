import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    } 

    public static Task toDo(String description) {
        return new ToDo(description);
    }

    public static Task toDo(String description, boolean isDone) {
        return new ToDo(description, isDone);
    }

    public static Task deadline(String description, LocalDate time) {
        return new Deadline(description, time);
    }

    public static Task deadline(String description, boolean isDone, LocalDate time) {
        return new Deadline(description, isDone, time);
    } 

    public static Task event(String description, LocalDate start, LocalDate end) {
        return new Event(description, start, end);
    }

    public static Task event(String description, boolean isDone, LocalDate start, LocalDate end) {
        return new Event(description, isDone, start, end);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusData() {
        return (isDone ? "1" : "0"); // modified status icon used for storing and parsing
    }

    public void completeTask() {
        this.isDone = true;
    }

    public void uncompleteTask() {
        this.isDone = false;
    }

    public String toString() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

   abstract public String storeData();

    protected static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public ToDo(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toString() {
            String body = super.toString();
            return "[T]" + body;
        }

        @Override
        public String storeData() {
            String statusIcon = this.getStatusData();
            return "T " + statusIcon + " /d" + this.description;
        }
    }
    
    protected static class Deadline extends Task {
        protected LocalDate time;

        public Deadline(String description, LocalDate time) {
            super(description);
            this.time = time;
        }

        public Deadline(String description, boolean isDone, LocalDate time) {
            super(description, isDone);
            this.time = time;
        }
        
        @Override
        public String toString() {
            String body = super.toString();
            return "[D]" + body + " (by: " + time + ")";
        }

        @Override
        public String storeData() {
            String statusIcon = this.getStatusData();
            return "D " + statusIcon + " /d" + this.description + " /t" + this.time;
        }
    }

    protected static class Event extends Task {
        protected LocalDate start;
        protected LocalDate end;

        public Event(String description, LocalDate start, LocalDate end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
            super(description, isDone);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            String body = super.toString();
            return "[E]" + body + " (from: " + start + " to: " + end + ")";
        }

        @Override
        public String storeData() {
            String statusIcon = this.getStatusData();
            return "E " + statusIcon + " /d" + this.description + " /b" + this.start + " /e" + this.end;
        }
    }
}