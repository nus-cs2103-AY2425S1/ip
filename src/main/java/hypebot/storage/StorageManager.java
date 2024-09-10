package hypebot.storage;

import hypebot.task.Task;
import hypebot.task.TaskDateTimeParseException;
import hypebot.tasklist.Tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {
    private final File tasklistFile;

    public StorageManager(String filePath) {
        tasklistFile = new File(filePath);
        try {
            if (!tasklistFile.exists()) {
                tasklistFile.getParentFile().mkdirs();
                tasklistFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create tasklist file", e);
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, TaskDateTimeParseException {
        TasklistDecoder decoder = new TasklistDecoder(tasklistFile);
        return decoder.decode();
    }

    public void save(Tasklist tasklist) throws IOException {
        TasklistEncoder encoder = new TasklistEncoder(tasklistFile, tasklist);
        encoder.encode();
    }
}
