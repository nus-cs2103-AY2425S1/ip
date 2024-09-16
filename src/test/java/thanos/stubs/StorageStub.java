package thanos.stubs;

import java.util.ArrayList;

import thanos.storage.IStorage;
import thanos.tasks.Task;

/**
 * The {@code StorageStub} class is a stub implementation of the {@code IStorage} interface.
 * It is primarily used for testing purposes, providing a simplified version of storage
 * that does not perform any actual file operations.
 */
public class StorageStub implements IStorage {
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> tasks) {
    }
}
