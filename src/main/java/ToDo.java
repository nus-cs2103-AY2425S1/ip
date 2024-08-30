import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task{
    public ToDo(String description, File dataFile) {
        super(description);
        this.write_to_datafile(dataFile);
    }

    public ToDo(String description, File dataFile, boolean done) {
        super(description, done);
    }

    @Override
    public String write_to_datafile(File dataFile){
        try {
            if (dataFile.exists()) {
                FileWriter wr = new FileWriter(dataFile, true); //boolean if true, then data will be written to the end of the file rather than the beginning.

                String builder = "T | "+ this.getDone1() + " | " + super.write_to_datafile(dataFile)+ "\n";
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
        return "[T]" + super.toString();
    }
}
