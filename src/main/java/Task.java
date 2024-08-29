import java.util.ArrayList;

public class Task {
    private String description;
    private boolean done;
    protected static int count = 0;

    public Task(String description) {
        this.description = description;
        this.done = false;
        count++;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
        count++;
    }

    public Task() {

    }

    @Override
    public String toString() {
        return "["+ this.done+"] "+ this.description;
    }
}
