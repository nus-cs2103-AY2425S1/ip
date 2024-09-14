package skibidi.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Task subclass with start and end date. */
public class Event extends AbstractTask {
    private final LocalDate from;
    private final LocalDate to;

    /** Construct new Event instance using command inputs. */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** Construct new Event instance using deserialized inputs. */
    public Event(String marker, String description, LocalDate from, LocalDate to) {
        super(marker, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Validate arguments for creating a new instance of Event, and returns
     * a new instance if valid. Otherwise throws TaskValidationException.
     * @param args
     * @throws TaskValidationException
     */
    public static Event validateThenCreate(String ...args) throws TaskValidationException {
        if (args.length != 3) {
            throw new TaskValidationException("Invalid number of arguments given for Event!");
        }
        if (args[0].isBlank()) {
            throw new TaskValidationException("Description cannot be empty!");
        }
        try {
            Event event = new Event(
                    args[0].strip(),
                    LocalDate.parse(args[1].strip()),
                    LocalDate.parse(args[2].strip()));
            return event;
        } catch (DateTimeParseException e) {
            throw new TaskValidationException("Could not parse date argument given to create Event!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")" + " (to: " + to + ")";
    }

    @Override
    public String serialize() {
        return String.join("|", new String[]{"E", getStatusIcon(), description, from.toString(), to.toString()});
    }
}
