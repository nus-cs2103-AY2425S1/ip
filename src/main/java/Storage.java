import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, JarException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
                case "T":
                    tasks.add(new ToDo(description));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                case "D":
                    String by = parts[3];
                    tasks.add(new Deadline(description, by));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    tasks.add(new Event(description, from, to));
                    tasks.get(tasks.size() - 1).setStatus(isDone);
                    break;
                default:
                    throw new JarException("Data file corrupted. Invalid task type.");
            }
        }

        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }
        writer.close();
    }
}
