package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chatbot.Parser;

/**
 * A class representing individual tasks
 * with a deadline.
 *
 * @author celeschai
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Instantiates new DeadLine object.
     *
     * @param name Task name.
     * @param deadline Deadline date-time in string format.
     * @throws DateTimeParseException If date-time string is in incorrect format.
     */
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = Parser.parseStringToDateTime(deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                Parser.parseDateTimeToString(this.deadline));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline deadline)) {
            return false;
        }
        return super.equals(deadline) && this.deadline.equals(deadline.deadline);
    }
}
