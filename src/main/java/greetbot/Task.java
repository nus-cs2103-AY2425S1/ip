package greetbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A abstract class that represents the tasks.
 */
abstract class Task {
    protected boolean isDone;
    protected String description;
    private final DateTimeFormatter RUNNINGFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final DateTimeFormatter DATABASEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * A abstract method that transfers task list to equivalent lines in database.
     * @return A string which will be written back to database.
     */
    public abstract String transferToDatabaseString();

    /**
     * Transfers tasks in database to task list used during program execution.
     * @param currentCommand A line in database expresses in equivalent command.
     * @param list Add the processed task to the database.
     */
    public static void decideTaskFromDatabase(String currentCommand, ArrayList<Task> list) {
        if (currentCommand.startsWith("deadline")) {
            final int deadlineAndSpaceTotalCharacters = 9;
            final int descriptionPart = 0;
            final int byPart = 1;
            String rest = currentCommand.substring(deadlineAndSpaceTotalCharacters);
            String[] parse = rest.split("/");
            list.add(new Deadline(parse[descriptionPart], parse[byPart]));

        } else if (currentCommand.startsWith("todo")) {
            final int todoAndSpaceTotalCharacters = 5;
            String rest = currentCommand.substring(todoAndSpaceTotalCharacters);
            list.add(new Todo(rest));

        } else if (currentCommand.startsWith("event")) {
            final int descriptionPart = 0;
            final int fromPart = 1;
            final int toPart = 2;
            final int eventAndSpaceTotalCharacters = 6;
            String rest = currentCommand.substring(eventAndSpaceTotalCharacters);
            String[] parse = rest.split("/");
            list.add(new Event(parse[descriptionPart], parse[fromPart], parse[toPart]));
        }
    }
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;

    }


    private int databaseMark() {
        return this.isDone ? 1 : 0;
    }
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

    /**
     * A specific type of task which represents a kind of event.
     * It contains the information of starting time and ending time.
     */
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
