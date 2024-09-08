package gray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

import gray.task.Task;

/**
 * Represents the tasks API
 */
public class Tasks {
    private final File saveFile;
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a tasks object
     * @param saveFile
     */
    public Tasks(File saveFile) {
        this.saveFile = saveFile;
        load();
    }

    private void save() {
        saveFile.getParentFile().mkdirs();
        try {
            Utility.serialise(saveFile, tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load() {
        saveFile.getParentFile().mkdirs();
        if (!saveFile.exists()) {
            return;
        }
        try {
            ArrayList<Task> taskList = (ArrayList<Task>) Utility.deserialise(saveFile);
            tasks.clear();
            tasks.addAll(taskList);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the list.
     *
     * @param index
     * @return
     */
    public Task remove(int index) {
        Task task = tasks.remove(index);
        save();
        return task;
    }

    /**
     * Searches the list for task that matches query.
     *
     * @param search
     * @return
     */
    public List<Task> search(String search) {
        return tasks.stream()
                .filter(task -> task.contains(search))
                .collect(Collectors.toList());
    }
}
