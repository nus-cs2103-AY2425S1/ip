public abstract class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task toDo(String description) {
        return new ToDo(description);
    }

    public static Task deadline(String description, String time) {
        return new Deadline(description, time);
    }

    public static Task event(String description, String start, String end) {
        return new Event(description, start, end);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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

    protected static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            String body = super.toString();
            return "[T]" + body;
        }
    }
    
    protected static class Deadline extends Task {
        
        protected String time;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
        }
        
        @Override
        public String toString() {
            String body = super.toString();
            return "[D]" + body + " (by: " + time + ")";
        }
    }

    protected static class Event extends Task {
        protected String start;
        protected String end;

        public Event(String description, String start, String end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            String body = super.toString();
            return "[E]" + body + " (from: " + start + " to: " + end + ")";
        }
    }
}