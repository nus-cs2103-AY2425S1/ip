package hypebot.storage;

import hypebot.tasklist.Tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static hypebot.common.Messages.ERROR_SAVE_TASKLIST;

/**
 * Represents a TasklistEncoder that takes in a file to save Tasks in
 * a Tasklist to, then encodes them in the specified format for saving Tasks
 * in a text file.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class TasklistEncoder {
    private File tasklistFile;
    private Tasklist tasks;

    /**
     * Takes in a Tasklist object to save Tasks from and a File to encode Tasks to
     * and creates a new TasklistEncoder.
     *
     * @param tasklistFile File object containing file path to .txt file to save Task data to.
     * @param tasks Tasklist containing Tasks to be saved.
     */
    public TasklistEncoder(File tasklistFile, Tasklist tasks) {
        this.tasklistFile = tasklistFile;
        this.tasks = tasks;
    }

    /**
     * Encodes Tasks in the TasklistEncoder's tasks field in the format to save to tasks.txt.
     *
     * @throws IOException If file to save to does not exist.
     */
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
