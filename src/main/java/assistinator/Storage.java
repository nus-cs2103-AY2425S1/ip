package assistinator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import assistinator.tasks.DeadlineTask;
import assistinator.tasks.EventTask;
import assistinator.tasks.Task;
import assistinator.tasks.TodoTask;


/**
 * API of the storage class.
 */
public class Storage {
    private String filePath;

    /**
     * Initialises a storage class.
     * @param filePath File path to tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to file in initialised file path.
     * @param tasks Task list.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            String content = tasks.stream()
                    .map(Task::toStorageString)
                    .collect(Collectors.joining(System.lineSeparator()));
            Files.writeString(Paths.get(filePath), content);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads task list from file.
     * @return Task list.
     * @throws AssistinatorException If file not in provided file path.
     */
    public ArrayList<Task> loadTasks() throws AssistinatorException {
        Path path = Paths.get(filePath);

        // Check if the directory exists, if not, create it
        Path parentDir = path.getParent();
        if (Files.notExists(parentDir)) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                throw new AssistinatorException("Unable to create directory: " + e.getMessage());
            }
        }

        // Check if the file exists, if not, create an empty file
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new AssistinatorException("Unable to create file: " + e.getMessage());
            }
        }

        try {
            return Files.lines(path)
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        String type = parts[0].trim();
                        return getTask(parts, type);
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new AssistinatorException("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Converts lines in files to tasks.
     * @param parts Task information.
     * @param type Type of task.
     * @return Task.
     */
    public Task getTask(String[] parts, String type) {
        try {
            if (parts.length < 3) {
                return null;
            }

            boolean isDone = TaskStatus.isDone(parts[1].trim());
            String description = parts[2].trim();

            Task task;
            switch (type) {
            case "T":
                task = new TodoTask(description);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                String deadline = parts[3].trim();
                task = new DeadlineTask(description, deadline);
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                String start = parts[3].trim();
                String end = parts[4].trim();
                task = new EventTask(description, start, end);
                break;
            default:
                return null;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return null;
        }
    }
}
