package thanos.stubs;

import java.util.ArrayList;

import thanos.storage.IStorage;
import thanos.tasks.Task;

public class StorageStub implements IStorage {
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> tasks) {
        return;
    }
}
