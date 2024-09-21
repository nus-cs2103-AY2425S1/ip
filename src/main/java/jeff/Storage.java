package jeff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jeff.exceptions.LineCorruptedException;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.ToDo;

/**
 * Manages the loading and saving of tasks to a file.
 *
 * The Storage class handles reading from and writing to a text file where the user's tasks are stored.
 */
public class Storage {
    private static final String DATE_FORMAT = "dd/MM/yyyy HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final String dirPath;
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param dirPath The path to the directory where tasks are stored.
     * @param filePath The path to the file in dirPath where tasks are stored.
     */
    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    public static String getDateFormat() {
        return DATE_FORMAT;
    }
    public static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }

    /**
     * Loads the tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> loadData() {
        // Check if the directory exists
        Path file = this.checkDirectory(dirPath, filePath);
        // Read the file
        return this.readFile(file);
    }

    private ArrayList<Task> readFile(Path file) {
        // Read file line by line
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                try {
                    this.processLine(line, taskList);
                } catch (LineCorruptedException e) {
                    System.out.println("Error, " + e.getMessage() + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with reading the file!");
        }
        return taskList;
    }

    private void processLine(String line, ArrayList<Task> taskList) throws LineCorruptedException {
        // Split the line by ","
        String[] parts = line.split(",");

        // Load task into the current tasklist
        switch (parts[0]) {
        case "T":
            if (parts.length != 3) {
                throw new LineCorruptedException("Incorrect Format");
            }
            taskList.add(new ToDo(parts[2]));
            break;
        case "D":
            if (parts.length != 4) {
                throw new LineCorruptedException("Incorrect Format");
            }
            try {
                taskList.add(
                        new Deadline(parts[2],
                                LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new LineCorruptedException("Incorrect date format at the file!");
            }
            break;
        case "E":
            if (parts.length != 5) {
                throw new LineCorruptedException("Incorrect Format");
            }
            try {
                taskList.add(
                        new Event(parts[2],
                                LocalDateTime.parse(parts[3].trim(), DATE_TIME_FORMATTER),
                                LocalDateTime.parse(parts[4].trim(), DATE_TIME_FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new LineCorruptedException("Incorrect date format at the file!");
            }
            break;
        default:
            throw new LineCorruptedException("Unsupported task");
        }
        // check if the task is already completed
        if (Objects.equals(parts[1], "X")) {
            taskList.get(taskList.size() - 1).markDone();
        }
    }

    private Path checkDirectory(String dirPath, String filePath) {
        // Check if directory does not exist
        Path directory = Paths.get(dirPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        // Check if file exists
        Path file = Paths.get(filePath);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        return file;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param taskList The list of tasks to save.
     * @throws IOException If an error occurs during file writing.
     */
    public void saveTask(ArrayList<Task> taskList) {
        // write to the file the newly added task
        String taskAsCsv = taskList.get(taskList.size() - 1).saveAsCsv();
        // try to append string to end of data file
        try {
            Path file = Paths.get(filePath);
            // Check if the file already has content
            boolean fileHasContent = Files.size(file) > 0;
            if (fileHasContent) {
                Files.writeString(file, "\n" + taskAsCsv, StandardOpenOption.APPEND);
            } else {
                Files.writeString(file, taskAsCsv, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("An error occurred saving the file");
        }
    }

    /**
     * Updates a specific line in a file with the CSV representation of a task from the task list.
     * This method is designed to replace a single line, identified by a zero-based line number,
     * with the updated task data. It handles file operations, ensuring that only the specified line is altered.
     *
     * @param taskList  the list of tasks, each capable of being saved in CSV format.
     * @param lineNumber the zero-based index of the line in the file to update.
     *                   This index must correspond to a valid position in the task list.
     */
    public void updateSave(ArrayList<Task> taskList, int lineNumber) {
        Path file = Paths.get(filePath);

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(file);

            // Update the specific line (lineNumber is zero-based)
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, taskList.get(lineNumber).saveAsCsv());
            } else {
                System.out.println("Invalid line number.");
                return;
            }

            // Write the updated lines back to the file
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Something went wrong updating the file");
        }
    }

    /**
     * Replaces the entire content of the file with the CSV representation of each task in the task list.
     * This method is used when a full update of the file is required, converting the entire list of tasks
     * into their CSV format and writing them to the file, effectively replacing all previous content.
     *
     * @param taskList the list of tasks, each capable of being saved in CSV format.
     */
    public void updateSave(ArrayList<Task> taskList) {
        Path file = Paths.get(filePath);

        try {
            // Create a list of strings to represent the updated file content
            List<String> lines = new ArrayList<>();

            // Convert each task in the taskList to its CSV representation
            for (Task task : taskList) {
                lines.add(task.saveAsCsv());
            }

            // Write the updated lines back to the file, replacing the entire content
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Something went wrong updating the file");
        }
    }
}
