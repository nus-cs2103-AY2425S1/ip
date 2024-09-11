package tuesday.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline task has a description and a specific due date by which it needs to be completed.
 */
public class Deadline extends Task {
    protected String byMsg;

    /**
     * Constructor for Deadline
     * Used for new task created
     *
     * @param description Description of the command
     * @param byMsg Postfix after /by
     */
    public Deadline(String description, String byMsg) {
        super(description);
        byMsg = this.handleByMsg(byMsg);
        this.byMsg = byMsg;
    }

    /**
     * Constructor for Deadline
     * Used for data collected from data file
     *
     * @param description Description of the command
     * @param byMsg Postfix after /by
     * @param done Marked data from data file
     */
    public Deadline(String description, String byMsg, boolean done) {
        super(description, done);
        byMsg = this.handleByMsg(byMsg);
        this.byMsg = byMsg;
    }

    /**
     * Handles the date and time of the by_msg
     *
     * @param byMsg Postfix after /by
     * @return Formated string of the date and time
     */
    private String handleByMsg(String byMsg) {
        //deadline readbook /by 2019-10-23 10:30
        String[] timingMsg = byMsg.split(" ");
        if (timingMsg.length < 2) {
            return byMsg;
        }

        timingMsg[0] = timingMsg[0].replaceAll("/", "-");
        String[] dateInParts = timingMsg[0].split("-");
        LocalDate date;
        if (dateInParts[2].length() == 4) {
            date = LocalDate.of(Integer.parseInt(dateInParts[2]), Integer.parseInt(dateInParts[1]),
                    Integer.parseInt(dateInParts[0]));
        } else {
            date = LocalDate.of(Integer.parseInt(dateInParts[0]), Integer.parseInt(dateInParts[1]),
                    Integer.parseInt(dateInParts[2]));
        }

        String stringDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));


        if (timingMsg[1].length() == 4) {
            timingMsg[1] = timingMsg[1].charAt(0) + "" + timingMsg[1].charAt(1) + ":"
                    + timingMsg[1].charAt(2) + "" + timingMsg[1].charAt(3);
        }
        LocalTime time = LocalTime.parse(timingMsg[1]);
        String stringTime = time.toString();

        return stringDate + " " + stringTime;
    }

    /**
     * Writes the date to the datafile
     *
     * @param dataFile The data file object
     * @return Empty string
     */
    @Override
    public String writeToDatafile(File dataFile) {
        try {
            assert dataFile.exists() : "Data file must be created";
            // boolean if true, then data will be written to the end of the file rather than the beginning.
            FileWriter fw = new FileWriter(dataFile, true);

            String builder = "D | " + this.getDone1() + " | " + super.writeToDatafile(dataFile)
                    + " | " + this.byMsg + "\n";
            fw.write(builder);

            //flushing & closing the writer
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("    An error occurred.");
        }
        return "";
    }

    /**
     * Used for an output message
     *
     * @return Output message
     */
    @Override
    public String toString() {
        assert this.byMsg != null : "Use the constructor first";
        return "[D]" + super.toString() + " (by: " + this.byMsg + ")";
    }
}
