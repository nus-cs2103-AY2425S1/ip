import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath; // e.g. "data/tasks.txt"
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>(); // List of tasks to be returned
        File file = new File(filePath); // Create a File object using the file path
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create parent directories if they do not exist
            file.createNewFile(); // Create a new file if it does not exist
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file)); // Create a BufferedReader object
            String line; // Read the file line by line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| "); // Split the line by " | "
                String type = parts[0]; // Get the type of task
                boolean isDone = parts[1].equals("1"); // Get the status of the task
                String description = parts[2]; // Get the description of the task
                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break; // Add a new Todos object to the list
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        tasks.add(new Deadline(description, by, isDone));
                        break; // Add a new Deadline object to the list
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        tasks.add(new Event(description, from, to, isDone));
                        break; // Add a new Event object to the list
                    default:
                        throw new IOException("Corrupted data file."); // Throw an exception if the file is corrupted
                }
            }
            reader.close(); // close the reader
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}