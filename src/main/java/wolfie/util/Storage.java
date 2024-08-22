package wolfie.util;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.task.Deadline;
import wolfie.task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                    default:
                        throw new IOException("Unknown task type in file");
                }
            }
            reader.close();
        }
        return tasks;
    }

    public void save(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : taskList.getTasks()) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
        writer.close();
    }
}