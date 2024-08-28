import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

abstract class Task {
    protected String description;
    protected boolean isDone;
    abstract public String getFrom();
    abstract public String getTo();
    abstract public LocalDate getBy();

    public static void decideTask(String currentCommand, ArrayList<Task> list) throws EmptyDescriptionException, RandomInputException {

        if (currentCommand.startsWith("deadline ") || currentCommand.startsWith("deadline")) {
            //special case
            if (currentCommand.equals("deadline") || currentCommand.equals("deadline ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of deadline cannot be empty.");
            }

            String rest = currentCommand.substring(9);
            String[] parse = rest.split("/");
            list.add(new Deadline(parse[0], parse[1]));

        } else if (currentCommand.startsWith("todo ") || currentCommand.startsWith("todo")) {
            //special case
            if (currentCommand.equals("todo") || currentCommand.equals("todo ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of todo cannot be empty.");
            }

            String rest = currentCommand.substring(5);
            list.add(new Todo(rest));

        } else if (currentCommand.startsWith("event ") || currentCommand.startsWith("event")) {
            //special case
            if (currentCommand.equals("event") || currentCommand.equals("event ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of event cannot be empty.");
            }

            String rest = currentCommand.substring(6);
            String[] parse = rest.split("/");
            list.add(new Event(parse[0], parse[1], parse[2]));

        } else if (currentCommand.startsWith("delete ") || currentCommand.startsWith("delete")) {
            if (currentCommand.equals("delete") || currentCommand.equals("delete ")) {
                throw new EmptyDescriptionException("OOPS!!! The description of a delete cannot be empty.");
            }

            String rest = currentCommand.substring(7);
            Task temp = list.get(Integer.parseInt(rest) - 1);
            list.remove(Integer.parseInt(rest) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(temp.toString());


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
    }

    public void unmark(){
        this.isDone = false;

    }

    public boolean isMarked() {
        return this.isDone;
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
        protected LocalDate by;

        public Deadline(String description, String by) {
            super(description);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.by = LocalDate.parse(by.substring(by.indexOf(" ") + 1), formatter);
        }

        public LocalDate getBy() {
            return this.by;
        }

        public String getFrom() {
            return "";
        }

        public String getTo() {
            return "";
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }

    private static class Todo extends Task {
        @Override
        public String getFrom() {
            return "";
        }

        @Override
        public String getTo() {
            return "";
        }

        @Override
        public LocalDate getBy() {
            return LocalDate.now();
        }
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
        public String getFrom() {
            return this.from;
        }

        @Override
        public String getTo() {
            return this.to;
        }

        @Override
        public LocalDate getBy() {
            return LocalDate.now();
        }

        @Override
        public String toString() {
            return String.format("[E]%s (from: %sto: %s)", super.toString(), this.from, this.to);
        }
    }

}
