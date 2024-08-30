package Tuesday.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    protected String fromMsg;
    protected String toMsg;

    public Event(String description, String fromMsg, String toMsg) {
        super(description);
        this.fromMsg = fromMsg;
        this.toMsg = toMsg;
    }

    public Event(String description, String fromMsg, String toMsg, boolean done) {
        super(description, done);
        this.fromMsg = fromMsg;
        this.toMsg = toMsg;
    }
  
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromMsg + " to: " + this.toMsg +")";
    }
}
