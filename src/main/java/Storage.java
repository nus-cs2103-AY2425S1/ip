import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public List<Task> load() throws IOException, CorruptedFileException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        continue;
                    }

                    Task task;
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (type) {
                        case "T":
                            task = new ToDoTask(description);
                            break;
                        case "D":
                            if (parts.length < 4) {
                                continue;
                            }
                            String by = parts[3];
                            task = new DeadlineTask(description, by);
                            break;
                        case "E":
                            if (parts.length < 5) {
                                continue;
                            }
                            String from = parts[3];
                            String to = parts[4];
                            task = new EventTask(description, from, to);
                            break;
                        default:
                            throw new CorruptedFileException("Invalid task type in file: " + type);
                    }
                    if (isDone) {
                        task.markAsDone();
                    }
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }
    public void save(List<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }
}
