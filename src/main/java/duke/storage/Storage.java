import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {
                switch (parts[0]) {
                case "T":
                    tasks.add(new ToDo(parts[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(parts[2], parts[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(parts[2], parts[3], parts[4], isDone));
                    break;
                default:
                    System.out.println("Warning: Corrupted task found in file. Skipping line.");
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Warning: Corrupted task data. Skipping line.");
            }
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }
}

