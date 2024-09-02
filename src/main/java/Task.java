import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String toFileString();

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

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


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



    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setIsDone(boolean val) {
        isDone = val;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

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
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

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
        private String from;
        private String to;
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

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






