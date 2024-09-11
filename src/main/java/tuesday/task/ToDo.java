package tuesday.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task with todo.
 * A todo task has a description.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo
     * Used for new task created
     *
     * @param description Description of the Command
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo
     * Used for data collected from data file
     *
     * @param description Description of the Command
     * @param done Marked task
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
    public String writeToDatafile(File dataFile) {
        String builder = "";
        try {
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter fw = new FileWriter(dataFile, true);

                builder = "T | " + this.getDone1() + " | " + super.writeToDatafile(dataFile) + "\n";
                fw.write(builder);

                //flushing & closing the writer
                fw.flush();
                fw.close();
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
