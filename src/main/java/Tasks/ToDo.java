package Tasks;

import Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        // Write event to file
        try {
            String toWrite = "T | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + "\n";
            fileWriter.write(toWrite);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{T}" + super.toString();
    }
}

