import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws AstaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new AstaException("No existing data file found.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new AstaException("Invalid task format: " + line);
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task;

                switch (type) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER);
                    task = new Deadline(description, by, isDone);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(parts[4].trim(), DATE_TIME_FORMATTER);
                    task = new Event(description, from, to, isDone);
                    break;
                default:
                    throw new AstaException("Unknown task type: " + type);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new AstaException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws AstaException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AstaException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
