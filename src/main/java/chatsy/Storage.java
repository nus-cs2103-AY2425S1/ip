package chatsy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chatsy.exceptions.FileCorruptedException;
import chatsy.exceptions.FilePermissionException;
import chatsy.exceptions.InvalidTaskStringException;
import chatsy.exceptions.LocalFileException;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.Task;
import chatsy.tasks.TodoTask;

/**
 * Handles file storage and retrieval of tasks for the Chatsy application.
 */
public class Storage {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String directoryPath;
    private String filePath;

    /**
     * Constructs a {@code Storage} instance with the specified directory and file paths.
     *
     * @param directoryPath The directory where tasks will be stored.
     * @param filePath The file where tasks will be saved.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into a list.
     *
     * @return A list of tasks.
     * @throws LocalFileException If there is an issue with loading the file.
     */
    public List<Task> loadTasks() throws LocalFileException {
        List<Task> tasks = new ArrayList<>();
        try {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            } else {
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        tasks.add(parseTask(line));
                    }
                }
            }
        } catch (IOException | InvalidTaskStringException e) {
            throw new FileCorruptedException(filePath);
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws LocalFileException If there is an issue with saving the file.
     */
    public void saveTasks(List<Task> tasks) throws LocalFileException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(formatTaskForSaving(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FilePermissionException(filePath);
        }
    }

    private Task parseTask(String taskString) throws InvalidTaskStringException {
        String[] parts = taskString.split(" \\| ");

        // Check if the taskString has enough parts to parse
        if (parts.length < 3) {
            throw new InvalidTaskStringException();
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case "T":
            return new TodoTask(description, isDone);
        case "D":
            LocalDate by = LocalDate.parse(parts[3], DATE_FORMATTER);
            return new DeadlineTask(description, by, isDone);
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
            return new EventTask(description, from, to, isDone);
        default:
            throw new InvalidTaskStringException();
        }
    }

    private String formatTaskForSaving(Task task) {
        if (task instanceof TodoTask) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return "D | " + (deadlineTask.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription()
                    + " | " + deadlineTask.getBy().format(DATE_FORMATTER);
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return "E | " + (eventTask.isDone() ? "1" : "0") + " | " + eventTask.getDescription()
                    + " | " + eventTask.getFrom().format(DATE_TIME_FORMATTER)
                    + " | " + eventTask.getTo().format(DATE_TIME_FORMATTER);
        }
        return "";
    }
}
