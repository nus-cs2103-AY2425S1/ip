package tars;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;

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
                if (parts.length < 3) {
                    throw new TarsException("Corrupt task data: " + line);
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                try {
                    switch (type) {
                        case "T":
                            tasks.add(new Todo(description, isDone));
                            break;
                        case "D":
                            if (parts.length < 4) {
                                throw new TarsException("Corrupt deadline task data: " + line);
                            }
                            String by = parts[3];
                            tasks.add(new Deadline(description, isDone, by));
                            break;
                        case "E":
                            if (parts.length < 5) {
                                throw new TarsException("Corrupt event task data: " + line);
                            }
                            String from = parts[3];
                            String to = parts[4];
                            tasks.add(new Event(description, isDone, from, to));
                            break;
                        default:
                            throw new TarsException("Unknown task type: " + type);
                    }
                } catch (DateTimeParseException e) {
                    throw new TarsException("Invalid date format in task data: " + line);
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
