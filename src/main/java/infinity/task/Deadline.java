package infinity.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import infinity.infinityexception.InfinityException;
import infinity.parser.Parser;
import infinity.storage.Storage;

/**
 * This class is the deadline task that the bot will recognise and manage.
 */
public class Deadline extends Task {
    private final LocalDateTime byDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the deadline.
     * @throws InfinityException If the format of the deadline is wrong.
     */
    public Deadline(String description) throws InfinityException {
        try {
            String[] splitInput = description.split(" /by ");
            this.setDescription(splitInput[0]);
            String by = splitInput[1];
            byDate = Parser.parseDateTime(by);

            this.setTypeOfTask(Task.TaskTypes.D);
        } catch (DateTimeException e) {
            throw new InfinityException("""
                    Oops, I think your date format is a little wrong.
                    I only understand dates in the format of DD-MM-YYYY HHMM or DD/MM/YYYY HHMM\n""");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InfinityException("""
                    Oops, I think your format is a little wrong.
                    Did you forget the date, '/' or the task?\n""");
        }
    }

    /**
     * 2nd Constructor for the Deadline class.
     *
     * @param isDone The status of the deadline.
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     * @throws InfinityException If the format of the deadline is wrong.
     */
    public Deadline(boolean isDone, String description, String by) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.byDate = Parser.parseDateTime(by);
        this.setTypeOfTask(Task.TaskTypes.D);
    }

    /**
     * Parses the save-file format of the deadline.
     *
     * @param delimiter The delimiter to separate the fields.
     * @return The save-file format of the deadline.
     */
    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s",
                this.typeOfTask.toString(), delimiter,
                this.isDone ? Storage.DONE_MARKER : Storage.UNDONE_MARKER, delimiter,
                Parser.parseDateTimeString(byDate), delimiter,
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.typeOfTask.toString(),
                this.isDone ? MARKED_MARKER : UNMARKED_MARKER,
                this.description,
                Parser.parseDateTimeStringAlt(byDate));
    }
}
