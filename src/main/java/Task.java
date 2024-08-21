public class Task {
    protected String description;
    protected boolean isDone;

    public static Task decideTask(String currentCommand) throws EmptyDescriptionException, RandomInputException {

        if (currentCommand.startsWith("deadline ") || currentCommand.startsWith("deadline")) {
            //special case
            if (currentCommand.equals("deadline") || currentCommand.equals("deadline ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of deadline cannot be empty.");
            }

            String rest = currentCommand.substring(9);
            String[] parse = rest.split("/");
            return new Deadline(parse[0], parse[1]);

        } else if (currentCommand.startsWith("todo ") || currentCommand.startsWith("todo")) {
            //special case
            if (currentCommand.equals("todo") || currentCommand.equals("todo ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of todo cannot be empty.");
            }

            String rest = currentCommand.substring(5);
            return new Todo(rest);

        } else if (currentCommand.startsWith("event ") || currentCommand.startsWith("event")) {
            //special case
            if (currentCommand.equals("event") || currentCommand.equals("event ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of event cannot be empty.");
            }

            String rest = currentCommand.substring(6);
            String[] parse = rest.split("/");
            return new Event(parse[0], parse[1], parse[2]);

        } else if (currentCommand.startsWith("delete ") || currentCommand.startsWith("delete")) {
            if (currentCommand.equals("delete") || currentCommand.equals("delete ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of a delete cannot be empty.");
            }
            return new Task("test");

        } else {
            throw new RandomInputException("何のことを言っているのか分かりません");
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
            System.out.println("Got it. I've added this task:");
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by: %s)", super.toString(), this.by);
        }
    }

    private static class Todo extends Task {

        public Todo(String description) {
            super(description);
            System.out.println("Got it. I've added this task:");
            System.out.println(this.toString());
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
            System.out.println("Got it. I've added this task:");
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %sto: %s)", super.toString(), this.from, this.to);
        }
    }

}
