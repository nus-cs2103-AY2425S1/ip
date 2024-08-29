package greetbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

abstract class Task {
    protected String description;
    private final DateTimeFormatter RUNNINGFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final DateTimeFormatter DATABASEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected boolean isDone;

    abstract public String transferToDatabaseString();

    public static void decideTaskFromDatabase(String currentCommand, ArrayList<Task> list) {
        if (currentCommand.startsWith("deadline")) {
            String rest = currentCommand.substring(9);
            String[] parse = rest.split("/");
            list.add(new Deadline(parse[0], parse[1]));

        } else if (currentCommand.startsWith("todo")) {

            String rest = currentCommand.substring(5);
            list.add(new Todo(rest));

        } else if (currentCommand.startsWith("event")) {

            String rest = currentCommand.substring(6);
            String[] parse = rest.split("/");
            list.add(new Event(parse[0], parse[1], parse[2]));
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


    private int databaseMark() {return this.isDone ? 1 : 0;}
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s ", (isDone ? "X" : " "), this.description.trim());
    }

    /* the subclass of Task */
    public static class Deadline extends Task {
        protected LocalDate by;

        public Deadline(String description, String by) {
            super(description);
            this.by = LocalDate.parse(by.substring(by.indexOf(" ") + 1), super.DATABASEFORMAT);

        }


        @Override
        public String transferToDatabaseString() {
            return String.format("D | %d | %s | %s",
                    super.databaseMark(),
                    super.description.trim(),
                    this.by.format(super.DATABASEFORMAT).trim());
        }

        @Override
        public String toString() {
            return String.format("[D]%s(by: %s)", super.toString(), this.by.format(super.RUNNINGFORMAT));
        }
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String transferToDatabaseString() {
            return String.format("T | %d | %s", super.databaseMark(), super.description.trim());
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from.substring(from.indexOf(" ") + 1);
            this.to = to.substring(to.indexOf(" ") + 1);
        }


        @Override
        public String transferToDatabaseString() {
            return String.format("E | %d | %s | %s | %s",
                    super.databaseMark(),
                    super.description.trim(),
                    this.from.trim(),
                    this.to.trim());
        }

        @Override
        public String toString() {
            return String.format("[E]%s(from: %s to: %s)", super.toString(), this.from.trim(), this.to);
        }
    }

}
