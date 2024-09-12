package dumpling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import dumpling.task.Task;
import dumpling.task.TaskList;

/**
 * Storage class to handle all data writing and reading
 */
public class Storage {

    private String filePath;

    /**
     * Storage constructor. Creates the given file at the filepath if the file does not exist.
     *
     * @param filePath Data filepath for reading and writing
     * @throws DumplingException Thrown if there is an issue with reading or creating the file
     */
    public Storage(String filePath) throws DumplingException {
        File dataDir = new File("./data");
        try {
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            this.filePath = filePath;
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DumplingException("There was an issue creating a new data file!");
        }
    }

    /**
     * Loads the data from harddisk
     * @return List of Task objects
     * @throws DumplingException Thrown when there are issues with loading the harddisk data
     */
    public List<Task> load() throws DumplingException {
        return Parser.loadData(this.filePath);
    }

    /**
     * Saves the information in TaskList to harddisk
     *
     * @param tasks TaskList of current state of Dumpling
     * @throws DumplingException Thrown when there is an issue writing or creating the file
     */
    public void save(TaskList tasks) throws DumplingException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            String data = tasks.getTasksForSaving();
            if (data.isEmpty()) {
                fileWriter.close();
                File dataFile = new File(this.filePath);
                dataFile.delete();
                return;
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DumplingException("There was an issue saving the data!");
        }
    }
}
