package victor.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class that handles task with a deadline.
 */
public class Deadline extends Task {
    protected String deadline;
    protected LocalDate deadlineDate;

    /**
     * Constructor for the deadline class that assigns the deadline a name and a deadline. Parses
     * input and saves the deadline as a LocalDate object.
     * @param name A string with the name of the deadline to be stored.
     * @param deadline A string with the deadline of the deadline, should be either in format yyyy-mm-dd or dd-mm-yyyy.
     * @throws DateTimeParseException Thrown if the start or end time cannot be parsed as a LocalDate object.
     */
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
    public void writeToFile(FileWriter fileWriter) throws IOException {
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
    public void writeToFile(Path filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(filePath), true);
            String toWrite = "D | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.deadline + "\n";
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{D}" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
