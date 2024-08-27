import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIRECTORY = "data";
    private static final String FILE_NAME = "appleaster.txt";
    private static final Path FILE_PATH = Paths.get(DATA_DIRECTORY, FILE_NAME);

    public static void saveTaskList(List<Task> tasks) throws IOException {
        createDataDirectoryIfNotExists();
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        }
    }

    public static List<Task> loadTaskList() throws IOException {
        createDataDirectoryIfNotExists();
        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(stringToTask(line));
            }
        }
        return tasks;
    }

    private static void createDataDirectoryIfNotExists() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIRECTORY));
    }

    private static String taskToString(Task task) {
        if (task instanceof Todo) {
            return String.format("T | %d | %s", task.isCompleted() ? 1 : 0, task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("D | %d | %s | %s", task.isCompleted() ? 1 : 0, task.getDescription(), deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("E | %d | %s | %s | %s", task.isCompleted() ? 1 : 0, task.getDescription(), event.from, event.to);
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    private static Task stringToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isCompleted = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isCompleted) {
            task.markAsDone();
        }
        return task;
    }
}