package stan;

import stan.tasks.Task;

/**
 * A mock implementation of the Task class used for testing purposes.
 * This class provides basic functionality for testing without implementing the full behavior of a task.
 */
public class MockTask extends Task {

    /**
     * Constructs a MockTask with the given description.
     *
     * @param description The description of the task.
     */
    public MockTask(String description) {
        super(description);
    }

    /**
     * Returns a mock string for testing purposes when saving to storage.
     *
     * @return A mock empty string.
     */
    @Override
    public String toStorageString() {
        return ""; // Return a mock string for testing purposes
    }

    /**
     * Returns a string representation of the mock task for testing purposes.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[MockTask] " + description; // Return a mock string for testing purposes
    }
}
