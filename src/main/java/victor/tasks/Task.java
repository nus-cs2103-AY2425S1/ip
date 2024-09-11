package victor.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Parent task class that Event, Deadline, and ToDo inherit from. Contains
 * the name of the event and the status of the task as completed or not.
 */
public class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructs the task given a string with the task name, and sets the done
     * status to false.
     * @param name A string with the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public void writeToFile(FileWriter fileWriter) throws IOException {
    }

    public void writeToFile(Path filePath) throws IOException {
    }

    @Override
    public String toString() {
        String out;
        if (this.isDone) {
            out = "{X} " + this.name;
        } else {
            out = "{ } " + this.name;
        }
        return out;
    }
}
