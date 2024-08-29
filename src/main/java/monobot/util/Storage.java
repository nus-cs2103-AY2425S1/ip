package monobot.util;

import monobot.exception.MonoBotException;
import monobot.task.Deadline;
import monobot.task.Event;
import monobot.task.Task;
import monobot.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws MonoBotException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : tasks) {
                bufferedWriter.write(taskToFileFormat(task));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            throw new MonoBotException("Error saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws MonoBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = parseTaskFromFileLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new MonoBotException("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static String taskToFileFormat(Task task) {
        String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String isDone = task.getIsDone() ? "1" : "0";

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("%s | %s | %s | %s", taskType, isDone, deadline.getDescription(), deadline.getDueBy().format(FILE_DATE_FORMAT));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("%s | %s | %s | %s | %s", taskType, isDone, event.getDescription(),
                    event.getFrom().format(FILE_DATE_FORMAT),
                    event.getTo().format(FILE_DATE_FORMAT));
        } else {
            return String.format("%s | %s | %s", taskType, isDone, task.getDescription());
        }
    }

    private static Task parseTaskFromFileLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Invalid task format in file: " + line);
            return null;
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Invalid deadline format in file: " + line);
                    return null;
                }
                LocalDateTime deadlineDate = LocalDateTime.parse(parts[3], FILE_DATE_FORMAT);
                task = new Deadline(description, deadlineDate.format(PARSER));
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("Invalid event format in file: " + line);
                    return null;
                }
                LocalDateTime startTime = LocalDateTime.parse(parts[3], FILE_DATE_FORMAT);
                LocalDateTime endTime = LocalDateTime.parse(parts[4], FILE_DATE_FORMAT);
                task = new Event(description, startTime.format(PARSER), endTime.format(PARSER));
                break;
            default:
                System.out.println("Unknown task type in file: " + line);
                return null;
        }

        if (isDone) {
            task.markTask();
        }

        return task;
    }
}

