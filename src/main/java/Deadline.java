import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String deadline;
    protected LocalDate deadlineDate;
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = deadline;
        try {
            this.deadlineDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException parseException) {
            this.deadlineDate = LocalDate.parse(deadline,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            this.deadlineDate = LocalDate.parse(this.deadlineDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        // Write event to file
        try {
            String toWrite = "D | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.deadline + "\n";
            fileWriter.write(toWrite);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{D}" + super.toString() + " (by: " +
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
