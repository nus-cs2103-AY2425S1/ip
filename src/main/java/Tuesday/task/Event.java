package Tuesday.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    // variable
    protected String fromMsg;
    protected String toMsg;

    /**
     * Constructor for Event
     * Used for new task created
     *
     * @param description Description of the command
     * @param from_msg Postfix after /from before /to
     * @param to_msg Postfix after /to
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
     * @param from_msg Postfix after /from before /to
     * @param to_msg Postfix after /to
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
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter wr = new FileWriter(dataFile, true);

                String builder = "E | "+ this.getDone1() + " | " + super.writeToDatafile(dataFile)
                        + " | " + this.fromMsg + "-" + this.fromMsg + "\n";
                wr.write(builder);

                //flushing & closing the writer
                wr.flush();
                wr.close();
            }
        } catch (IOException e) {
            System.out.println("    An error occurred.");
        }
        return "";
    }

    /**
     * Handles the date and time of the by_msg
     *
     * @param by_msg Postfix after /by
     * @return Formated string of the date and time
     */
    private String handleByMsg(String by_msg) {
        String[] timing_msg = by_msg.split(" ");

        timing_msg[0] = timing_msg[0].replaceAll("/","-");
        String[] date_in_parts = timing_msg[0].split("-");
        LocalDate date;
        if (date_in_parts[2].length() == 4) {
            date = LocalDate.of(Integer.parseInt(date_in_parts[2]), Integer.parseInt(date_in_parts[1]),
                    Integer.parseInt(date_in_parts[0]));
        } else {
            date = LocalDate.of(Integer.parseInt(date_in_parts[0]), Integer.parseInt(date_in_parts[1]),
                    Integer.parseInt(date_in_parts[2]));
        }

        String string_date = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));


        if (timing_msg[1].length() == 4) {
            timing_msg[1] = timing_msg[1].charAt(0) + "" + timing_msg[1].charAt(1) + ":"
                    + timing_msg[1].charAt(2) + "" + timing_msg[1].charAt(3);
        }
        LocalTime time = LocalTime.parse(timing_msg[1]);
        String string_time = time.toString();

        return string_date + " " + string_time;
    }

    /**
     * Used for an output message
     *
     * @return Output message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromMsg + " to: " + this.toMsg +")";
    }
}
