package Tasks;

import java.io.FileWriter;

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

    public void writeToFile(FileWriter fileWriter) {
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