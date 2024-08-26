package alice.storage;

import alice.task.*;
import java.io.*;
import java.util.*;

public class Storage {
    private final String dataDirectoryPath;
    private final String tasksFileName;

    public Storage(String dataDirectoryPath, String tasksFileName) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.tasksFileName = tasksFileName;
    }

    public List<Task> loadTasks() throws IOException, InvalidTaskException {
        File dataDirectory = new File(dataDirectoryPath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        List<Task> tasks = new ArrayList<>();
        File tasksFile = new File(String.format("%s/%s", dataDirectoryPath, tasksFileName));
        FileReader input = new FileReader(tasksFile.getAbsoluteFile());
        BufferedReader reader = new BufferedReader(input);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            Task task = Task.fromJsonString(line);
            tasks.add(task);
        }
        reader.close();
        input.close();
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File dataDirectory = new File(dataDirectoryPath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File tasksFile = new File(String.format("%s/%s", dataDirectoryPath, tasksFileName));
        // overwrite file
        FileWriter output = new FileWriter(tasksFile.getAbsoluteFile(), false);
        BufferedWriter writer = new BufferedWriter(output);
        for (Task task : tasks) {
            writer.write(task.toJsonString());
            writer.write("\n");
        }
        writer.close();
        output.close();
    }
}