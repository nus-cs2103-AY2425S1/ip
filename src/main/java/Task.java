import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    private String description;
    private boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getStatusIcon() {
        return (isMarked ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> toSaveArray() {
        return new ArrayList<String>(Arrays.asList((this.isMarked ? "1" : "0"), this.description));
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
