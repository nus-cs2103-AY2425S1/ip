package gray;

import java.io.File;
import java.io.IOException;

public class Storage {

    private final File saveFile;

    public Storage(String saveFilePath) {
        this.saveFile = new File(saveFilePath);
    }

    public void saveTasks(TaskList taskList) {
        saveFile.getParentFile().mkdirs();
        try {
            Utility.serialise(saveFile, taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskList loadTasks() {
        saveFile.getParentFile().mkdirs();
        if (!saveFile.exists()) return new TaskList();
        try {
            return (TaskList) Utility.deserialise(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
