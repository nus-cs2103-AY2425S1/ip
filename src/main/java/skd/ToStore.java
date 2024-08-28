package skd;

import skd.task.Deadline;
import skd.task.Event;
import skd.task.Task;
import skd.task.ToDo;
import skd.task.TaskType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToStore {
    private final Path filePath;

    public ToStore(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.notExists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return tasks;
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("     Unable to load tasks from the file. Starting with an empty list.");
        }

        return tasks;
    }

    public void save(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toString());
        }
        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("     Unable to save tasks to the file.");
        }
    }
}