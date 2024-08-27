package dumpling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;

import java.util.List;

import dumpling.task.Task;
import dumpling.task.TaskList;

public class Storage {

    private String filePath;

    public Storage(String filePath) throws DumplingException {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DumplingException("There was an issue creating a new data file!");
        }
    }

    public List<Task> load() throws DumplingException {
        return Parser.loadData(this.filePath);
    }

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
