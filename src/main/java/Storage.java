import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws BingBongException {
        List<Task> tasks = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(filePath))) {
            String line;
            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] taskData = line.split(" \\| ");
                String type = taskData[0];
                boolean isDone = taskData[1].equals("1");
                String description = taskData[2];
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String by = taskData[3];
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String from = taskData[3];
                    String to = taskData[4];
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new BingBongException("Unknown task type in file.");
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BingBongException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws BingBongException {
        File file = new File(filePath);

        // Ensure the parent directory exists
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BingBongException("Failed to create directory: " + directory);
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BingBongException("Error writing to file: " + e.getMessage());
        }

    }
}
