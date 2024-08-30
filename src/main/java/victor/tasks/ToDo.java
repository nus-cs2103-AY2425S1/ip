package victor.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * ToDo class that handles tasks without no associated times and only a name.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public void writeToFile(FileWriter fileWriter) throws IOException {
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
    public void writeToFile(Path filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(filePath), true);
            String toWrite = "T | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + "\n";
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{T}" + super.toString();
    }
}

