package victor.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Task {
    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public void writeToFile(FileWriter fileWriter) throws IOException {
        return;
    }

    public void writeToFile(Path filePath) throws IOException {
        return;
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
