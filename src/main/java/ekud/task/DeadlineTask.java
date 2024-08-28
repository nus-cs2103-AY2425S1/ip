package ekud.task;

import ekud.exceptions.EkudException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DeadlineTask extends Task {
    public static DateTimeFormatter READ_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
    public static DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a", Locale.ENGLISH);

    protected LocalDateTime deadline;

    public DeadlineTask(String description, String deadline) throws EkudException {
        super(description);
        if (deadline == null || deadline.isEmpty()) {
            throw new EkudException(
                    """
                            Whoopsies!! Looks like you forgot your deadline!
                            I'll say this once: next time mark your deadline with '/deadline'.""");
        }
        try {
            this.deadline = LocalDateTime.parse(deadline, READ_FORMAT);
        } catch (DateTimeParseException e) {
            throw new EkudException(
                    """
                            Whoopsies!! It looks like you tried to pass a deadline that I cannot read!
                            I'd recommend that you follow the 'd/M/yyyy HHmm' format. Or else...""");
        }
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I'm sorry, but, nothing does not have a DEADLINE.\nTry giving me an actual ekud.task.";
    }

    @Override
    public String getSaveTaskString() {
        return String.format("D | %s | %s", super.getSaveTaskString(), deadline.format(READ_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(PRINT_FORMAT));
    }
}
