package Tuesday.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String writeToDatafile(File dataFile) {
        String builder = "";
        try {
            if (dataFile.exists()) {
                // boolean if true, then data will be written to the end of the file rather than the beginning.
                FileWriter wr = new FileWriter(dataFile, true);

                builder = "T | "+ this.getDone1() + " | " + super.writeToDatafile(dataFile)+ "\n";
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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
