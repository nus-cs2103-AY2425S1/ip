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

    private void writeToDoEncoding(FileWriter fileWriter) throws IOException {
        String toWrite = "T | ";
        if (this.isDone) {
            toWrite += "1 | ";
        } else {
            toWrite += "0 | ";
        }
        toWrite += this.name + "\n";
        fileWriter.write(toWrite);
    }

    /**
     * Writes the to do description to a file given an existing filewriter.
     * @param fileWriter a pre-initialised filewriter object to write to a file with.
     * @throws IOException if there is a problem writing to the file.
     */
    @Override
    public void writeToFile(FileWriter fileWriter) throws IOException {
        // Write event to file
        try {
            writeToDoEncoding(fileWriter);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    /**
     * Writes the to do description to a file given a file path.
     * @param filePath A path object to the file to write the event encoding to.
     * @throws IOException if there is a problem writing to the file.
     */
    @Override
    public void writeToFile(Path filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(filePath), true);
            writeToDoEncoding(fileWriter);
            fileWriter.close();
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{T}" + super.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo objToDo = (ToDo) obj;
        if (!this.name.equals(objToDo.name)) {
            return false;
        }
        return true;
    }
}

