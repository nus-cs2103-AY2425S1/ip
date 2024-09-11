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
    private static final String DATE_FORMAT = "MMM dd yyyy";
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

    /**
     * Creates file encoding version of deadline and writes to file given a filewriter.
     * @param fileWriter A FileWriter object that writes to a pre-initialised file with all task data.
     * @throws IOException if there is a problem writing to the file.
     */
    private void writeDeadlineEncoding(FileWriter fileWriter) throws IOException {
        String toWrite = "D | ";
        if (this.isDone) {
            toWrite += "1 | ";
        } else {
            toWrite += "0 | ";
        }
        toWrite += this.name + " | " + this.deadline + "\n";
        fileWriter.write(toWrite);
    }

    /**
     * Writes the deadline description to a file given an existing filewriter.
     * @param fileWriter a pre-initialised filewriter object to write to a file with.
     * @throws IOException if there is a problem writing to the file.
     */
    @Override
    public void writeToFile(FileWriter fileWriter) throws IOException {
        // Write event to file
        try {
            writeDeadlineEncoding(fileWriter);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    /**
     * Writes the deadline description to a file given a file path.
     * @param filePath A path object to the file to write the event encoding to.
     * @throws IOException if there is a problem writing to the file.
     */
    @Override
    public void writeToFile(Path filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(filePath), true);
            writeDeadlineEncoding(fileWriter);
            fileWriter.close();
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{D}" + super.toString() + " (by: " + getFormattedDate() + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline objDeadline = (Deadline) obj;
        if (!this.name.equals(objDeadline.name)) {
            return false;
        } else {
            return this.deadlineDate.equals(objDeadline.deadlineDate);
        }
    }

    /**
     * Returns the deadline date formatted in MMM dd yyyy format.
     * @return A string with the deadline date formatted.
     */
    private String getFormattedDate() {
        return this.deadlineDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
