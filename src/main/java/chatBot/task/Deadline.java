package chatBot.task;

import chatBot.exception.EmptyDescException;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate deadline;

    // can consider Optional for time in the future

    public Deadline(String desc, String deadline) throws EmptyDescException {
        super(desc);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String getOriginalCommand() {
        return "deadline " + super.getOriginalCommand() + " /by " + this.deadline.format(ORIGINALFORMAT);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(PRINTFORMAT) + ")";
    }
}
