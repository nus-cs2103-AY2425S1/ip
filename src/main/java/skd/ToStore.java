package skd;

import skd.Parser;
import task.Task;

import java.nio.file.*;
import java.util.*;
import java.io.IOException;

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