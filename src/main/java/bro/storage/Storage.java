package bro.storage;

import bro.task.TaskList;

// Handles writing and loading from storage
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToStorage() {}
    public TaskList loadFromStorage(){
        return null;
    };
}
