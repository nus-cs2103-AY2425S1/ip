package GPT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        try {
            if (!Files.exists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating directories: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task;
                    if (taskType.equals("T")) {
                        task = new ToDo(description);
                    } else if (taskType.equals("D")) {
                        LocalDateTime byDateTime = Parser.parseDateTime(parts[3]);
                        task = new Deadline(description, byDateTime);
                    } else if (taskType.equals("E")) {
                        LocalDateTime fromDateTime = Parser.parseDateTime(parts[3]);
                        LocalDateTime toDateTime = Parser.parseDateTime(parts[4]);
                        task = new Event(description, fromDateTime, toDateTime);
                    } else {
                        System.out.println("Unknown task type: " + taskType);
                        continue;
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
