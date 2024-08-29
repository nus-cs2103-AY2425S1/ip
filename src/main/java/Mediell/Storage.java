package Mediell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String STORAGE_FOLDER_PATH = "./data";
    private final String DATA_FILE = "data.txt";
    private Path dataFilePath;

    public Storage() throws IOException {
        Files.createDirectories(Paths.get(STORAGE_FOLDER_PATH));
        dataFilePath = Path.of(STORAGE_FOLDER_PATH, DATA_FILE);
    }

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
