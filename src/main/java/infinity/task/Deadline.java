package infinity.task;

import infinity.infinityexception.InfinityException;
import infinity.parser.Parser;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime byDate;

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

    public Deadline(boolean isDone, String description, String by) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.byDate = Parser.parseDateTime(by);
        this.setTypeOfTask("D");
    }

    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s", 
                this.typeOfTask, delimiter, 
                this.isDone ? "1" : "0", delimiter, 
                Parser.parseDateTimeString(byDate), delimiter, 
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", 
                this.typeOfTask, 
                this.isDone ? "X" : " ", 
                this.description, 
                Parser.parseDateTimeStringAlt(byDate));
    }
}