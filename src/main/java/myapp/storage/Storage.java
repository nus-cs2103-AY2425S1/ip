package myapp.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import myapp.task.*;
import myapp.exception.RubyException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, RubyException {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return tasks;
        }

        List<String> lines = Files.readAllLines(path);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task;
            switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                    task = new Deadline(parts[2], by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                    task = new Event(parts[2], from, to);
                    break;
                default:
                    throw new RubyException("Invalid task type.");
            }
            if (parts[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        for (Task task : tasks) {
            String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
            String dateTimeInfo = "";
            if (task instanceof Deadline) {
                dateTimeInfo = ((Deadline) task).getBy().format(formatter);
            } else if (task instanceof Event) {
                dateTimeInfo = ((Event) task).getFrom().format(formatter) + " | " +
                        ((Event) task).getTo().format(formatter);
            }
            writer.write(taskType + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() +
                    (taskType.equals("T") ? "" : " | " + dateTimeInfo) + "\n");
        }
        writer.close();
    }
}
