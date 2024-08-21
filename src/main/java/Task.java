public class Task {
    protected String description;
    protected boolean isDone;


    public static Task of(String descriptions) throws MissingInformationException, InvalidInputException {
        if (descriptions.equals("todo") || descriptions.equals("deadline") || descriptions.equals("event")){
            throw new MissingInformationException("description", descriptions);
        }
        if (descriptions.startsWith("todo ")) {
            if (descriptions.length() <= 5 || descriptions.charAt(5) == ' ') {
                throw new MissingInformationException("description", "todo");
            }
            return new ToDo(descriptions.substring(5));
        } else if (descriptions.startsWith("deadline ")) {
            if (descriptions.length() <= 9 || descriptions.charAt(9) == ' ') {
                throw new MissingInformationException("description", "deadline");
            }
            String[] strings = descriptions.substring(9).split("/");
            if (strings.length < 2 || !strings[1].startsWith("by ")) {
                throw new MissingInformationException("by time", "deadline");
            }
            return new DeadLine(strings[0],strings[1].substring(3));
        } else if (descriptions.startsWith("event ")) {
            if (descriptions.length() <= 6 || descriptions.charAt(6) == ' ') {
                throw new MissingInformationException("description", "event");
            }
            String[] strings = descriptions.substring(6).split("/");
            if (strings.length < 2 || !strings[1].startsWith("from ")) {
                throw new MissingInformationException("from time", "event");
            } else if (strings.length < 3 || !strings[2].startsWith("to ")) {
                throw new MissingInformationException("to time", "event");
            }
            return new Event(strings[0],strings[1].substring(5),strings[2].substring(3));
        } else {
            throw new InvalidInputException();
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

    public void mark() throws IncorrectStateException{
        if (this.isDone) {
            throw new IncorrectStateException("mark");
        }
        this.isDone = true;
    }

    public void unmark() throws IncorrectStateException{
        if (!this.isDone) {
            throw new IncorrectStateException("unmark");
        }
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
