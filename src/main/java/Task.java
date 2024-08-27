public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task of(String s) {
        // /from and /to or /by
        if (s.contains("/from") && s.contains("/to") && s.contains("/by")) {
            throw new IllegalArgumentException("/from /to /by cannot exist simultaneously");
        }

        int i = s.indexOf(' ');
        String word = i == -1 ? "" : s.substring(0, i);
        if (word.equals("todo")) {
            s = s.substring(i + 1);
            return new Todo(s);
        } else if (word.equals("deadline")) {
            s = s.substring(i + 1);
            int index = s.indexOf("/by");
            String description = s.substring(0, index);
            String by = s.substring(index + 3);
            return new Deadline(description, by);
        } else if (word.equals("event")){
            s = s.substring(i + 1);
            int index1 = s.indexOf("/from");
            String description = s.substring(0, index1);
            int index2 = s.indexOf("/to");
            String from = s.substring(index1 + 5, index2);
            String to = s.substring(index2 + 3);
            return new Event(description, from, to);
        } else {
            return new Task(s);
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
        public String toString() {
            return String.format("[D][%s] %s (by:%s)",
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
        public String toString() {
            return String.format("[T][%s] %s (from:%s to:%s)",
                    super.getStatusIcon(), super.description, from, to);
        }
    }
}






