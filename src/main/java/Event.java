import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Event extends Task {
    protected String endTime;
    protected String startTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDeadline() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        // Write event to file
        try {
            String toWrite = "E | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.startTime + " | " + this.endTime + "\n";
            fileWriter.write(toWrite);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{E}" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
