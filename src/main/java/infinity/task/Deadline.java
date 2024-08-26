package infinity.task;

import infinity.infinityexception.InfinityException;
import infinity.parser.Parser;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * This class is the deadline task that the bot will recognise and manage.
 */
public class Deadline extends Task {
    private LocalDateTime byDate;

    /**
     * Constructor for the Deadline class.
     * @param description The description of the deadline
     * @throws InfinityException If the format of the deadline is wrong
     */
    public Deadline(String description) throws InfinityException {
        try {
            this.setDescription(description.split(" /by ")[0]);

            String by = description.split(" /by ")[1];
            byDate = Parser.parseDateTime(by);

            this.setTypeOfTask("D");
        } catch (DateTimeException e) {
            throw new InfinityException("""
                    Oops, I think your format is a little wrong. 
                    I only understand dates in the format of DD-MM-YYYY HHMM or DD/MM/YYYY HHMM""");
        }
    }

    /**
     * 2nd Constructor for the Deadline class.
     * @param isDone The status of the deadline
     * @param description The description of the deadline
     * @param by The date of the deadline
     * @throws InfinityException If the format of the deadline is wrong
     */
    public Deadline(boolean isDone, String description, String by) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.byDate = Parser.parseDateTime(by);
        this.setTypeOfTask("D");
    }

    /**
     * Parses the save-file format of the deadline
     * @param delimiter The delimiter to separate the fields
     * @return The save-file format of the deadline
     */
    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s", 
                Character.toString(this.typeOfTask), delimiter, 
                this.isDone ? "1" : "0", delimiter, 
                Parser.parseDateTimeString(byDate), delimiter, 
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", 
                Character.toString(this.typeOfTask), 
                this.isDone ? "X" : " ", 
                this.description, 
                Parser.parseDateTimeStringAlt(byDate));
    }
}