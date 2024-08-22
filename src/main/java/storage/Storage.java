package storage;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

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
                    LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                    tasks.add(new DeadLine(description, by));
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
