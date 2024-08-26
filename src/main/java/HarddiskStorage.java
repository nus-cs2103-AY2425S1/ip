import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
    import java.time.LocalDateTime;

public class HarddiskStorage {
    private final String filePath;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public HarddiskStorage(String filePath) {
        this.filePath = filePath;
    }

    public Map<Integer, Task> load() {
        Map<Integer, Task> tasks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                int taskId = Integer.parseInt(parts[0]);
                String type = parts[1];
                boolean isDone = parts[2].equals("1");
                String description = parts[3];
                Task task = null;

                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D")) {
                    LocalDateTime by = LocalDateTime.parse(parts[4], formatter);
                    task = new Deadline(description, by);
                } else if (type.equals("E")) {
                    LocalDateTime from = LocalDateTime.parse(parts[4], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[5], formatter);
                    task = new Event(description, from, to);
                }

                if (task != null && isDone) {
                    task.markDone();
                }

                tasks.put(taskId, task);
            }
        } catch (IOException e) {
            System.out.println("    No existing task file found. Starting with an empty task list.");
        }
        return tasks;
    }


    public void save(Map<Integer, Task> tasks) {
        try {
            File file = new File(filePath);

            File parentDir = file.getParentFile();

            // Ensure parent directories exist
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // Create the directories if they don't exist
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile(); // This will create the file if it doesn't exist
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                    Task task = entry.getValue();
                    writer.write(entry.getKey() + " | " + task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("    Error saving tasks to file.");
        }
    }
}

