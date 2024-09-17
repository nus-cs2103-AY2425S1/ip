package hien.main;
import hien.exception.HienException;
import hien.task.Deadline;
import hien.task.Event;
import hien.task.Task;
import hien.task.Todo;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws HienException {
        TaskList tasks = new TaskList();
        try {
            Files.createDirectories(Paths.get("data"));
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    Task task;
                    switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            LocalDateTime by = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                            task = new Deadline(parts[2], by);
                            break;
                        case "E":
                            LocalDateTime from = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                            LocalDateTime to = LocalDateTime.parse(parts[4].trim(), INPUT_DATE_FORMAT);
                            task = new Event(parts[2], from, to);
                            break;
                        default:
                            continue;
                    }
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage() + "Return empty tasks");
            return new TaskList();
        }
        return tasks;
    }

    public void save(TaskList tasks) throws HienException {
        try {
            Files.createDirectories(Paths.get("data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks.getAllTasks()) {
                String line;
                if (task instanceof Todo) {
                    line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    line = "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy().format(INPUT_DATE_FORMAT);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    line = "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom().format(INPUT_DATE_FORMAT) + " | " + event.getTo().format(INPUT_DATE_FORMAT);
                } else {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
