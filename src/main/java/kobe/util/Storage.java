package kobe.util;

import kobe.task.Deadline;
import kobe.task.Event;
import kobe.task.Task;
import kobe.task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            new File(file.getParent()).mkdirs(); // Create the directory if it doesn't exist
            file.createNewFile(); // Create the file if it doesn't exist
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String name = parts[2];

                switch (taskType) {
                    case "T":
                        Task todo = new Todo(name);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                        Task deadline = new Deadline(name, by);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                        Task event = new Event(name, from, to);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                    default:
                        throw new IOException("Invalid task type in file: " + taskType);
                }
            }
            reader.close();
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
