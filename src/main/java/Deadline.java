import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by, File dataFile) {
        super(description);
        this.by = by;
        this.write_to_datafile(dataFile);
    }

    public Deadline(String description, String by, File dataFile, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String write_to_datafile(File dataFile){
        try {
            if (dataFile.exists()) {
                FileWriter wr = new FileWriter(dataFile, true); //boolean if true, then data will be written to the end of the file rather than the beginning.

                String builder = "D | "+ this.getDone1() + " | " + super.write_to_datafile(dataFile) + " | " + this.by + "\n";
                wr.write(builder);

                //flushing & closing the writer
                wr.flush();
                wr.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return "";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
