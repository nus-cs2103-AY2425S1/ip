package evan.main;

import evan.exception.FileCreationException;
import evan.exception.LoadingException;
import evan.exception.SavingException;
import evan.task.Deadline;
import evan.task.Event;
import evan.task.Task;
import evan.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) throws FileCreationException {
        this.filePath = Path.of(filePath);

        try {
            // Create the file if it doesn't exist
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            throw new FileCreationException(filePath);
        }
    }

    // The ExtractMethodRecommender warning is suppressed because the logic for decoding the task from a file line
    // should be in the same method.
    @SuppressWarnings("ExtractMethodRecommender")
    private static Task decodeTask(String s) throws LoadingException {
        // Split each line by the "|" character
        String[] parts = s.split("\\|");

        // Trim leading and trailing whitespace in all parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        try {
            String taskType = parts[0]; // "T", "D", or "E"
            String taskStatus = parts[1]; // "1" or "0"
            String description = parts[2];

            Task task;
            switch (taskType) {
            case "T" -> task = new Todo(description);
            case "D" -> {
                String by = parts[3];
                task = new Deadline(description, by);
            }
            case "E" -> {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
            default -> throw new LoadingException("Invalid task type.\nLine that caused the error: '" + s + "'");
            }

            switch (taskStatus) {
            case "0" -> task.markAsUndone();
            case "1" -> task.markAsDone();
            default -> throw new LoadingException("Invalid task status.\nLine that caused the error: '" + s + "'");
            }

            return task;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LoadingException("Invalid number of arguments for task type.\nLine that caused the error: '" + s + "'");
        }
    }


    public ArrayList<Task> load() throws LoadingException {
        ArrayList<Task> taskList = new ArrayList<>(); // Create the ArrayList that will eventually be returned

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task taskToAdd = decodeTask(line);
                taskList.add(taskToAdd);
            }
        } catch (IOException e) {
            throw new LoadingException("Error occurred while reading the file.");
        }

        return taskList;
    }

    public void save(TaskList taskList) throws SavingException {
        try {
            String data = taskList.encodeAsString();
            Files.writeString(filePath, data);
        } catch (IOException e) {
            throw new SavingException("Error occurred while saving to the file.");
        }
    }
}
