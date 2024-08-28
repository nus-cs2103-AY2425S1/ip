import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String name, String deadline){
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        // Write event to file
        try {
            String toWrite = "D | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.deadline + "\n";
            fileWriter.write(toWrite);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{D}" + super.toString() + " (by: " + this.deadline + ")";
    }
}
