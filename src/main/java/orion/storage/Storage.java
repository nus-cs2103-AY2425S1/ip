package orion.storage;

import orion.task.Deadline;
import orion.task.Event;
import orion.task.Task;
import orion.task.Todo;
import orion.task.*;
import orion.orionExceptions.FileInitializationException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DATA_FILE_PATH = "./data/tasks.csv";

    public Storage() throws FileInitializationException {
        initializeFile();
    }

    private void initializeFile() throws FileInitializationException {
        File dataFile = new File(DATA_FILE_PATH);
        try {
            if (!dataFile.exists()) {
                if (dataFile.getParentFile() != null && !dataFile.getParentFile().exists()) {
                    boolean dirsCreated = dataFile.getParentFile().mkdirs();
                    if (!dirsCreated) {
                        throw new FileInitializationException("Failed to create directories: " + dataFile.getParentFile());
                    }
                }

                boolean fileCreated = dataFile.createNewFile();
                if (!fileCreated) {
                    throw new FileInitializationException("Failed to create file: " + dataFile.getPath());
                }
            }
        } catch (IOException e) {
            throw new FileInitializationException("IOException occurred: " + e.getMessage());
        }
    }

    public List<Task> read() throws FileInitializationException {
        List<Task> loadedTasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int taskId = Integer.parseInt(parts[0]);
                String type = parts[1];
                String description = parts[2];
                boolean completed = Boolean.parseBoolean(parts[3]);

                Task task = null;
                switch (type) {
                    case "TODO":
                        task = new Todo(taskId, description);
                        break;
                    case "DEADLINE":
                        LocalDateTime by = LocalDateTime.parse(parts[4]);
                        task = new Deadline(taskId, description, by);
                        break;
                    case "EVENT":
                        String[] eventTimes = parts[4].split("\\|");
                        LocalDateTime from = LocalDateTime.parse(eventTimes[0]);
                        LocalDateTime to = LocalDateTime.parse(eventTimes[1]);
                        task = new Event(taskId, description, from, to);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + type);
                }

                task.setCompleted(completed);
                loadedTasks.add(task);
            }
        } catch (IOException e) {
            throw new FileInitializationException("Error reading from file: " + e.getMessage());
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new FileInitializationException("Error parsing task data: " + e.getMessage());
        }

        return loadedTasks;
    }

    public void write(List<Task> tasks) throws FileInitializationException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : tasks) {
                StringBuilder taskLine = new StringBuilder();
                taskLine.append(task.getTaskID()).append(",");
                taskLine.append(getTaskType(task)).append(",");
                taskLine.append(task.getDescription()).append(",");
                taskLine.append(task.isCompleted());

                if (task instanceof Deadline) {
                    taskLine.append(",").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskLine.append(",").append(((Event) task).getFrom())
                            .append("|").append(((Event) task).getTo());
                }

                bw.write(taskLine.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileInitializationException("Error writing to file: " + e.getMessage());
        }
    }

    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "TODO";
        } else if (task instanceof Deadline) {
            return "DEADLINE";
        } else if (task instanceof Event) {
            return "EVENT";
        } else {
            throw new IllegalArgumentException("Unknown task type: " + task.getClass().getName());
        }
    }
}