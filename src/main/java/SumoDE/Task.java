import SumoDE.Exception.AlreadyUnmarkedException;
import SumoDE.Exception.UnknownCommandException;
import SumoDE.Exception.WrongSyntaxForCommandException;

public class Task {
    private boolean completed;
    private final String name;
    private static final String done = "[X]";
    private static final String undone = "[ ]";

    public Task(String name) {
        this.name = name;
    }

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

    public static Task of(Command command, String item) throws WrongSyntaxForCommandException, UnknownCommandException {
        switch(command) {
            case TODO:
                return new Todo(item);
            case DEADLINE:
                {
                    String[] parsed = Parser.parseDeadline(item);
                    return new Deadline(parsed[0], parsed[1]);
                }
            case EVENT:
                {
                    String[] parsed = Parser.parseEvent(item);
                    return new Event(parsed[0], parsed[1], parsed[2]);
                }
            default:
                throw new UnknownCommandException(command);  // shouldn't happen
        }

    }

    public void mark() throws AlreadyMarkedException {
        if (completed) {
            throw new AlreadyMarkedException(this);
        } else {
            this.completed = true;
        }
    }

    public void unmark() throws AlreadyUnmarkedException {
        if (!completed) {
            throw new AlreadyUnmarkedException(this);
        } else {
            this.completed = false;
        }
    }

    public String savedString() {
        return (this.completed ? "1" : "0") + " | " + this.name;
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}
