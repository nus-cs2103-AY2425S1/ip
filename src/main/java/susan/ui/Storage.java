package susan.ui;

import susan.task.TaskList;

import java.io.File;
import java.io.FileWriter;

/**
 * Handles saving tasks to file
 */
public class Storage {
    final String FILE_PATH = "./src/data/SusanToDoList.txt";

    public Storage() {}

    /**
     * Creates data directory if it does not exist
     * Writes updated TaskList into file
     */
    public void load(TaskList tasks) throws SusanException {
        // Create data file
        File dataPath = new File("./src/data");
        if (!dataPath.exists()) {
            boolean x = dataPath.mkdir();
            assert x : "Failed to create data path";
        }
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(tasks.printList());
            fw.close();
        } catch (Exception e) {
            throw new SusanException("Error saving tasks to file");
        }
    }
}
