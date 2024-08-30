package Tuesday.task;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by_msg;

    public Deadline(String description, String by_msg) {
        super(description);
        by_msg = handleByMsg(by_msg);
        this.by_msg = by_msg;
    }

    public Deadline(String description, String by_msg, boolean done) {
        super(description, done);
        by_msg = handleByMsg(by_msg);
        this.by_msg = by_msg;
    }


    private String handleByMsg(String by_msg) {
        //deadline readbook /by 2019-10-23 10:30
        String[] timing_msg = by_msg.split(" ");

        timing_msg[0] = timing_msg[0].replaceAll("/", "-");
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
    public String write_to_datafile(File dataFile) {
        try {
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter wr = new FileWriter(dataFile, true);

                String builder = "D | "+ this.getDone1() + " | " + super.write_to_datafile(dataFile)
                        + " | " + this.by_msg + "\n";
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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by_msg + ")";
    }
}
