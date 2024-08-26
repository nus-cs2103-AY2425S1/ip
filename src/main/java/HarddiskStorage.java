import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HarddiskStorage {
    private final String filePath;

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
                    String by = parts[4];
                    task = new Deadline(description, by);
                } else if (type.equals("E")) {
                    String from = parts[4];
                    String to = parts[5];
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

