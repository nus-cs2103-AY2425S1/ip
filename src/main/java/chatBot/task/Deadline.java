package chatbot.task;

import java.time.LocalDate;

import chatbot.exception.EmptyDescException;

/** Deadline is a subclass of Task and requires storage of one time */
public class Deadline extends Task {
    private final LocalDate deadline;

    // can consider Optional for time in the future

    /** Constructor */
    public Deadline(String desc, String deadline) throws EmptyDescException {
        super(desc);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String getOriginalCommand() {
        return "deadline " + super.getOriginalCommand() + " /by " + this.deadline.format(ORIGINALFORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(PRINTFORMAT) + ")";
    }
}
