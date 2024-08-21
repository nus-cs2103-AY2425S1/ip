public class Task {
    protected String description;
    protected boolean isDone;


    public static Task of(String descriptions) throws MissingInformationException{
        if (descriptions.startsWith("todo ")) {
            return new ToDo(descriptions.substring(5));
        } else if (descriptions.startsWith("deadline ")) {
            String[] strings = descriptions.substring(9).split("/");
            return new DeadLine(strings[0],strings[1].substring(3));
        } else if (descriptions.startsWith("event ")) {
            String[] strings = descriptions.substring(6).split("/");
            return new Event(strings[0],strings[1].substring(6),strings[2].substring(3));
        } else {
            return null;
        }
    }

    private Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(),description);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    private static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return String.format("[T][%s] %s",getStatusIcon(),description);
        }
    }

    private static class DeadLine extends Task {
        private String deadLine;

        public DeadLine(String description, String deadLine) {
            super(description);
            this.deadLine = deadLine;
        }

        @Override
        public String toString() {
            return String.format("[D][%s] %s (by: %s)",getStatusIcon(),description,deadLine);
        }
    }

    private static class Event extends Task {
        private String from;
        private String to;

        private Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("[E][%s] %s (from: %s to: %s)",getStatusIcon(),description,from,to);
        }
    }

    //...
}
