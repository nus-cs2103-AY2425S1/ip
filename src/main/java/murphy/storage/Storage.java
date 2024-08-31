package murphy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import murphy.MurphyException;
import murphy.task.Task;
import murphy.task.TaskList;

/**
 * Creates (if not existing), reads and writes to .txt file which serves as a save file for tasks.
 */
public class Storage {
    private final String filepath;
    private final File file;

    /**
     * Creates a Storage object with file set to designated filepath, creating a new file with the
     * necessary parent directories if the file doesn't exist.
     * @throws MurphyException If the file cannot be created.
     */
    public Storage(String filepath) throws MurphyException {
        this.filepath = filepath;
        File file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new MurphyException("Error creating new save file: " + e.getMessage());
            }
        }
        this.file = file;
    }

    /**
     * Returns a Scanner which reads from the file stored in the Storage object.
     * @throws MurphyException If the file cannot be found.
     */
    public Scanner load() throws MurphyException {
        try {
            return new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new MurphyException("Error loading save file: " + e.getMessage());
        }
    }

    /**
     * Overrides the contents of the Storage's file with the current contents of a TaskList.
     * @throws MurphyException If there is an error writing to the file.
     */
    public void write(TaskList taskList) throws MurphyException {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(taskList.toSaveString());
            fw.close();
        } catch (IOException e) {
            throw new MurphyException("Error writing to save file: " + e.getMessage());
        }
    }
}
