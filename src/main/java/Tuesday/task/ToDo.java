package Tuesday.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task{
    /**
     * Constructor for ToDo
     * Used for new task created
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo
     * Used for data collected from data file
     *
     * @param description
     * @param done
     */
    public ToDo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Writes the date to the datafile
     *
     * @param dataFile Date file which stores the data
     * @return Content of the change file
     */
    @Override
    public String write_to_datafile(File dataFile) {
        String builder = "";
        try {
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter wr = new FileWriter(dataFile, true);

                builder = "T | "+ this.getDone1() + " | " + super.write_to_datafile(dataFile)+ "\n";
                wr.write(builder);

                //flushing & closing the writer
                wr.flush();
                wr.close();
            }
        } catch (IOException e) {
            System.out.println("    An error occurred.");
        }
        return builder;
    }

    /**
     * Used for an output message
     *
     * @return Output message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
