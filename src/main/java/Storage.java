import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Load tasks from the file
    public List<Task> load() throws IOException, XiziException {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks; // Return an empty task list if file doesn't exist
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        try  {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = getTask(line);
                tasks.add(task);
            }
        } finally {

        }

        return tasks;
    }

    private static Task getTask(String line) throws XiziException {
        String[] parts = line.split(" \\| ");
        Task task;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new XiziException("File data corrupted: Unknown task type.");
        }
        if (parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    // Save tasks to the file
    public void appendTask(Task task) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(task.toFileFormat());
            writer.newLine();

        }
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }


}




