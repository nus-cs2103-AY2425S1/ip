package pixy.storage;

import pixy.tasks.ToDos;
import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("X");
                Task task = getTask(parts, taskType, isDone);
                tasks.add(task);
            }
            s.close();
        }
        return tasks;
    }

    private Task getTask(String[] parts, String taskType, boolean isDone) {
        String description = parts[2];
        Task task = null;
        switch (taskType) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadlines(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
        }
        if (task != null && isDone) {
            task.markAsDone(true);
        }
        return task;
    }

    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(taskToFileFormat(task) + System.lineSeparator());
        }
        fw.close();
    }

    public String taskToFileFormat(Task task) {
        if (task instanceof ToDos) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadlines) {
            Deadlines deadline = (Deadlines) task;
            return "D | " + deadline.getStatusIcon() + " | " + deadline.getDescription() + " | " + deadline.getDueDateTime();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + event.getStatusIcon() + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
