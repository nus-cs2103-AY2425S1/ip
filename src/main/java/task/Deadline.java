package task;

import chatbot.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A class representing individual tasks
 * with a deadline.
 *
 * @author celeschai
 */
public class Deadline extends Task{
    private LocalDateTime deadline;

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
