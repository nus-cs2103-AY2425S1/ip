package Mediell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Represents the storage of task in a txt file. */
public class Storage {
    private final String STORAGE_FOLDER_PATH = "./data";
    private final String DATA_FILE = "data.txt";
    private Path dataFilePath;

    public Storage() throws IOException {
        Files.createDirectories(Paths.get(STORAGE_FOLDER_PATH));
        dataFilePath = Path.of(STORAGE_FOLDER_PATH, DATA_FILE);
    }

    /**
     * Saves the data into a txt file.
     * @param taskList list of task to save into the file
     */
    public void saveData(TaskList taskList) throws IOException {
        String[] data = taskList.exportTasks();
        if (!Files.exists(dataFilePath)) {
            Files.createFile(dataFilePath);
        }
        StringBuilder builder = new StringBuilder();
        if (data.length != 0) {
            builder.append(data[0]);
            for (int i = 1; i < data.length; i++) {
                builder.append("\n");
                builder.append(data[i]);
            }
            Files.writeString(dataFilePath, builder.toString());
        }
    }

    /**
     * Loads the data from a txt file.
     * @return taskList list of task exported from the file
     */
    public TaskList loadData() throws IOException {
        TaskList temp = new TaskList();
        if (Files.exists(dataFilePath)) {
            String data = new String(Files.readAllBytes(dataFilePath));
            if (data.equals("")) {
                return temp;
            }
            temp.initTasks(data.split("\n"));
            return temp;
        }
        return temp;
    }
}
