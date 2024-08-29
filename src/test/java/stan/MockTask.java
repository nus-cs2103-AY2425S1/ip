package stan;
import stan.tasks.Task;
public class MockTask extends Task {

    public MockTask(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        // Return a mock string for testing purposes
        return "";
    }

    @Override
    public String toString() {
        // Return a mock string for testing purposes
        return "[MockTask] " + description;
    }
}
