public class Task {
    protected String description;
    protected boolean isDone;

    public static Task decideTask(String currentCommand) throws EmptyDescriptionException {

        if (currentCommand.startsWith("deadline ")) {
            String rest = currentCommand.substring(9);
            if (rest.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] parse = rest.split("/");
            return new Deadline(parse[0], parse[1]);
        } else if (currentCommand.startsWith("todo ")) {
            String rest = currentCommand.substring(5);
            if (rest.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(rest);
        } else {
            String rest = currentCommand.substring(6);
            if (rest.isEmpty()) {
                throw new EmptyDescriptionException("OOPS!!! The description of a event cannot be empty.");
            }
            String[] parse = rest.split("/");
            return new Event(parse[0], parse[1], parse[2]);
        }
    }

    private Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark(){
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), this.description);
    }

    /* the subclass of Task */
    private static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by.substring(by.indexOf(" ") + 1);
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by: %s)", super.toString(), this.by);
        }
    }

    private static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from.substring(from.indexOf(" ") + 1);
            this.to = to.substring(to.indexOf(" ") + 1);
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %sto: %s)", super.toString(), this.from, this.to);
        }
    }

}
