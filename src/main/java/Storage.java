import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    public List<Task> loadTask() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                tasks.add(task);
            }
        }

        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(taskToString(task) + "\n");
        }
        writer.close();
    }

    private Task parseTask(String line) {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

        Task task;
        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String by = parts[3].trim();
                LocalDateTime byDate = LocalDateTime.parse(parts[3].trim(), formatter);
                task = new Deadline(description, byDate);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                LocalDateTime fromDate = LocalDateTime.parse(parts[3].trim(), formatter);
                LocalDateTime toDate = LocalDateTime.parse(parts[4].trim(), formatter);
                task = new Event(description, fromDate, toDate);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private String taskToString(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();
        String extra = "";

        if (task instanceof Deadline) {
            extra = "|" + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            extra = "|" + ((Event) task).getFrom() + "|" + ((Event) task).getTo();
        }

        return taskType + " | " + isDone + " | " + description + extra;
    }


}
