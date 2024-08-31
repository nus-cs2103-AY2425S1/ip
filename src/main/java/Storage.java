import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;
    private final File file;

    public Storage(String filePath) throws  IOException {
        this.filePath = filePath;
        file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Task> loadTaskFromFile() throws IOException, SamException {
        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    Task todo = new ToDo(description);
                    if (todo.isDone()) {
                        todo.complete();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    String by = parts[3];
                    Task deadline = new Deadline(description, by);
                    if (deadline.isDone()) {
                        deadline.complete();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    Task event = new Event(description, from, to);
                    if (event.isDone()) {
                        event.complete();
                    }
                    tasks.add(event);
                    break;
                default:
                    System.out.println("Invalid data detected: " + line );
                    break;
            }
        }
        return tasks;
    }

    public void addTaskToFile(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(task.toStorageString() + System.lineSeparator());
        fileWriter.close();
    }

    public void saveTasksToFile(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.toStorageString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}

