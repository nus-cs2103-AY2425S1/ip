package thanos.storage;

import java.util.ArrayList;

import thanos.tasks.Task;

public interface IStorage {
    ArrayList<Task> load();
    void save(ArrayList<Task> taskList);
}
