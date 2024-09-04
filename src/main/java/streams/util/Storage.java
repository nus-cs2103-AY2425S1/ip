package streams.util;

import streams.exception.StreamsException;
import streams.task.DeadlineTask;
import streams.task.EventTask;
import streams.task.Task;
import streams.task.ToDoTask;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private static String FILE_PATH;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    public ArrayList<Task> load() throws StreamsException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new StreamsException("failed to create directory: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDoTask(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        LocalDateTime by = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        task = new DeadlineTask(description, by);
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        LocalDateTime from = LocalDateTime.parse(parts[3], INPUT_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], INPUT_FORMATTER);
                        task = new EventTask(description, from, to);
                        break;
                    default:
                        continue;
                }
                if (isDone) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException | DateTimeParseException e) {
            throw new StreamsException("an error occurred while reading the file: " + e.getMessage());
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws StreamsException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                String taskType = task instanceof ToDoTask ? "T" :
                        task instanceof DeadlineTask ? "D" :
                                task instanceof EventTask ? "E" : "";
                String isDone = task.isDone() ? "1" : "0";
                String taskString = taskType + " | " + isDone + " | " + task.getDescription();

                if (task instanceof DeadlineTask) {
                    taskString += " | " + ((DeadlineTask) task).getBy().format(INPUT_FORMATTER);
                } else if (task instanceof EventTask) {
                    taskString += " | " + ((EventTask) task).getFrom().format(INPUT_FORMATTER) +
                            " | " + ((EventTask) task).getTo().format(INPUT_FORMATTER);
                }

                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new StreamsException("an error occurred while saving the tasks: " + e.getMessage());
        }
    }
}
