package hypebot.storage;

import hypebot.tasklist.Tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static hypebot.common.Messages.ERROR_SAVE_TASKLIST;

public class TasklistEncoder {
    private File tasklistFile;
    private Tasklist tasks;

    public TasklistEncoder(File tasklistFile, Tasklist tasks) {
        this.tasklistFile = tasklistFile;
        this.tasks = tasks;
    }

    public void encode() throws IOException {
        if (!tasklistFile.exists()) {
            throw new FileNotFoundException(ERROR_SAVE_TASKLIST);
        }
        FileWriter tasklistWriter = new FileWriter(tasklistFile);
        tasklistWriter.write("");
        for (int i = 0; i < tasks.size(); i++) {
            tasklistWriter.append(tasks.getTaskByIndex(i).toFileString());
        }
        tasklistWriter.close();
    }
}
