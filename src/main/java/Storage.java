// import java.io.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws TarsException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new Deadline(description, isDone, by));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new Event(description, isDone, from, to));
                        break;
                    default:
                        String toThrow = "There was an issue with the task" + type + isDone + description;
                        throw new TarsException(toThrow);
                }
            }
        } catch (IOException e) {
            throw new TarsException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws TarsException {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            try {
                directory.mkdirs(); // Create directory
            } catch (SecurityException e) {
                System.out.println("There was a security/permission issue: " + e.getMessage());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(taskToFileString(task));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new TarsException("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to convert Task to string for saving to file
    private String taskToFileString(Task task) {
        String type = "";
        String additionalInfo = "";

        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
            additionalInfo = " | " + ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            type = "E";
            additionalInfo = " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }
        return type + " | " + (task.isDone() ? "1" : "0") + " | " + task.getName() + additionalInfo;
    }
}
