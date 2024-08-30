import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;  // No data file exists, start with an empty list.
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), parts[1].equals("1")));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), parts[1].equals("1")));
                        break;
                    default:
                        throw new BobException("Unknown task type in data file.");
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new BobException("Error loading tasks: " + e.getMessage());
        } catch (Exception e) {
            throw new BobException("Corrupted data file.");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws BobException {
        try {
            File directory = new File(filePath).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BobException("Error saving tasks: " + e.getMessage());
        }
    }
}
