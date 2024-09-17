package slothingwaffler;

import java.util.ArrayList;

/**
 * A stub class for simulating storage interactions in the SlothingWaffler application.
 * This class is used for testing purposes and does not interact with actual storage.
 */
public class StorageStub {

    /**
     * Loads tasks from the storage.
     * This method returns an empty list, as it is a stub for testing.
     *
     * @return An empty {@code ArrayList} of {@code Task} objects.
     */
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

}
