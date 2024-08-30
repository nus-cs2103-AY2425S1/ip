import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    protected String from_msg;
    protected String to_msg;

    public Event(String description, String from_msg, String to_msg) {
        super(description);
        this.from_msg = from_msg;
        this.to_msg = to_msg;
    }

    public Event(String description, String from_msg, String to_msg, boolean done) {
        super(description, done);
        this.from_msg = from_msg;
        this.to_msg = to_msg;
    }

    @Override
    public String write_to_datafile(File dataFile) {
        try {
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter wr = new FileWriter(dataFile, true);

                String builder = "E | "+ this.getDone1() + " | " + super.write_to_datafile(dataFile)
                        + " | " + this.from_msg + "-" + this.to_msg + "\n";
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
        return "[E]" + super.toString() + " (from: " + this.from_msg + " to: " + this.to_msg +")";
    }
}
