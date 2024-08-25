package sumode.task;

import sumode.exception.AlreadyMarkedException;
import sumode.exception.AlreadyUnmarkedException;
import sumode.exception.UnknownCommandException;
import sumode.exception.WrongSyntaxForCommandException;
import sumode.util.Command;
import sumode.util.Parser;

/**
 * A class for various tasks.
 */
public class Task {
    private static final String done = "[X]";
    private static final String undone = "[ ]";
    private boolean completed;
    private final String name;

    /**
     * Constructor for Task
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns a task based on String in format which was saved in local drive.
     *
     * @param inputFromFile String in format which was saved in local drive.
     * @return Task needed.
     */
    public static Task createFromData(String inputFromFile) {
        String[] components = inputFromFile.split(" \\| ");
        Task returned = switch (components[0]) {
        case "T" -> new Todo(components[2]);
        case "E" -> new Event(components[2], components[3], components[4]);
        case "D" -> new Deadline(components[2], components[3]);
        default -> throw new IllegalArgumentException();
        };

        if (components[1].equals("1")) {
            try {
                returned.mark();
            } catch (AlreadyMarkedException e) {
                //do nothing as this won't happen as this is just read
                //however I need to handle it
            }
        }
        return returned;
    }

    /**
     * Returns a task based on User input.
     *
     * @param command Type of task.
     * @param item Details of task.
     * @return Task needed.
     */
    public static Task of(Command command, String item) throws WrongSyntaxForCommandException,
            UnknownCommandException {
        switch(command) {
        case TODO:
            if (item.trim().isEmpty()) {
                throw new WrongSyntaxForCommandException(Command.TODO);
            }
            return new Todo(item.trim());
        case DEADLINE: {
            String[] parsed = Parser.parseDeadline(item);
            return new Deadline(parsed[0], parsed[1]);
        }
        case EVENT: {
            String[] parsed = Parser.parseEvent(item);
            return new Event(parsed[0], parsed[1], parsed[2]);
        }
        default:
            throw new UnknownCommandException(command); // shouldn't happen
        }
    }

    /**
     * Mark the task.
     */
    public void mark() throws AlreadyMarkedException {
        if (completed) {
            throw new AlreadyMarkedException(this);
        } else {
            this.completed = true;
        }
    }

    /**
     * Unmark the task.
     */
    public void unmark() throws AlreadyUnmarkedException {
        if (!completed) {
            throw new AlreadyUnmarkedException(this);
        } else {
            this.completed = false;
        }
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    public String savedString() {
        return (this.completed ? "1" : "0") + " | " + this.name;
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}
