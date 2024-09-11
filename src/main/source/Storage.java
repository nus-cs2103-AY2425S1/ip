package main.source;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import main.tasks.*;

public class Storage {
    public Path dataFilePath;
    ArrayList<Task> taskList;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String dataFilePath) {
        this.dataFilePath = Path.of(dataFilePath);
        this.taskList = new ArrayList<>();
    }

    public void saveTasks(TaskList taskList) throws IOException {
        String[] data = taskList.export();
        if (!Files.exists(dataFilePath)) {
            Files.createDirectories(dataFilePath.getParent());
            Files.createFile(dataFilePath);
        }

        StringBuilder builder = new StringBuilder();
        if (data.length > 0) {
            builder.append(data[0]);
            for (int i = 1; i < data.length; i++) {
                builder.append("\n").append(data[i]);
            }
        }
        Files.writeString(dataFilePath, builder.toString());
    }



    public ArrayList<Task> loadTasks() throws IOException {
        if (Files.exists(dataFilePath)) {
            List<String> lines = Files.readAllLines(dataFilePath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                Task task = decodeTasks(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }

    public static Task decodeTasks(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String text = parts[2];

        switch (taskType) {
            case "T":
                return new ToDo(text, isDone);
            case "D":
                LocalDateTime time = LocalDateTime.parse(parts[3], formatter);
                return new Deadline(text, time, isDone);
            case "E":
                String from = parts[3];
                String to = parts[4];
                return new Event(text, from, to, isDone);
            default:
                System.out.println("Unknown task type: " + taskType);
                return null;
        }
    }
    
}
