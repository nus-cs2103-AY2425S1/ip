import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Task> loadTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            Task task = parseTask(sc.nextLine());
            if (task != null) {
                tasks.add(task);
            }
        }
        sc.close();
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write("%s%n".formatted(task.toFileFormat()));
            }
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("X");
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description, LocalDateTime.parse(parts[3], formatter));
            case "E" -> {
                LocalDateTime start = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime end = LocalDateTime.parse(parts[4], formatter);
                yield new Event(description, start, end);
            }
            default -> null;
        };

        if (task != null) {
            task.markAsDone(isDone);
        }
        return task;
    }
}
