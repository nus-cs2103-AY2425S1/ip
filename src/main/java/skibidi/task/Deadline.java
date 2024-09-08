package skibidi.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Task subclass with end date. */
public class Deadline extends AbstractTask {
    private final LocalDate by;

    /** Construct Deadline instance using command inputs. */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /** Constructor for Deadline instance using deserialized inputs. */
    public Deadline(String marker, String description, LocalDate by) {
        super(marker, description);
        this.by = by;
    }


    /**
     * Validate arguments for creating a new instance of Deadline, and returns
     * a new instance if valid. Otherwise throws TaskValidationException.
     * @param args
     * @throws TaskValidationException
     */
    public static Deadline validateThenCreate(String ...args) throws TaskValidationException {
        if (args.length != 2) {
            throw new TaskValidationException("Invalid number of arguments given for Deadline!");
        }
        if (args[0].isBlank()) {
            throw new TaskValidationException("Description cannot be empty!");
        }
        try {
            Deadline deadline = new Deadline(
                    args[0].strip(),
                    LocalDate.parse(args[1].strip()));
            return deadline;
        } catch (DateTimeParseException e) {
            throw new TaskValidationException("Could not parse date argument given to create Deadline!");
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialize() {
        return String.join("|", new String[]{"D", getStatusIcon(), description, by.toString()});
    }
}
