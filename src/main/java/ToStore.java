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
                tasks.add(parseTask(line));
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

    private Task parseTask(String line) {
        try {
            String type = line.substring(1, 2);
            boolean isDone = line.charAt(4) == 'X';
            String description;

            switch (type) {
                case "T":
                    description = line.substring(7);
                    return new ToDo(description, isDone);
                case "D":
                    description = line.substring(7, line.indexOf("(by:")).trim();
                    String by = line.substring(line.indexOf("(by:") + 5, line.indexOf(")")).trim();
                    return new Deadline(description, by, isDone);
                case "E":
                    description = line.substring(7, line.indexOf("(from:")).trim();
                    String from = line.substring(line.indexOf("(from:") + 7, line.indexOf(" to:")).trim();
                    String to = line.substring(line.indexOf("to:") + 4, line.indexOf(")")).trim();
                    return new Event(description, from, to, isDone);
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
        } catch (Exception e) {
            System.out.println("     Corrupted task entry found and skipped: " + line);
            return null;
        }
    }
}