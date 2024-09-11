package tuesday.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a event.
 * A event task has a description and a specific timeframe 'from' and 'to' which it needs to be completed
 */
public class Event extends Task {
    // variable
    protected String fromMsg;
    protected String toMsg;

    /**
     * Constructor for Event
     * Used for new task created
     *
     * @param description Description of the command
     * @param fromMsg Postfix after /from before /to
     * @param toMsg Postfix after /to
     */
    public Event(String description, String fromMsg, String toMsg) {
        super(description);
        this.fromMsg = fromMsg;
        this.toMsg = toMsg;
    }

    /**
     * Constructor for Event
     * Used for data collected from data file
     *
     * @param description Description of the command
     * @param fromMsg Postfix after /from before /to
     * @param toMsg Postfix after /to
     * @param done Marked task
     */
    public Event(String description, String fromMsg, String toMsg, boolean done) {
        super(description, done);
        this.fromMsg = fromMsg;
        this.toMsg = toMsg;
    }

    /**
     * Writes the date to the datafile
     *
     * @param dataFile Data file object
     * @return empty string
     */
    @Override
    public String writeToDatafile(File dataFile) {
        try {
            assert dataFile.exists() : "Data file must be created";
            // boolean if true, then data will be written to the end of the file rather than the beginning.
            FileWriter fw = new FileWriter(dataFile, true);

            String builder = "E | " + this.getDone1() + " | " + super.writeToDatafile(dataFile)
                    + " | " + this.fromMsg + "-" + this.fromMsg + "\n";
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
     * Handles the date and time of the byMsg and fromMsg
     *
     * @param byMsg Postfix after /by
     * @return Formated string of the date and time
     */
    private String handleByMsg(String byMsg) {
        //deadline readbook /by 2019-10-23 10:30
        String[] timingMsg = byMsg.split(" ");

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
     * Used for an output message
     *
     * @return Output message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromMsg + " to: " + this.toMsg + ")";
    }
}
