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

public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final String DIR_PATH;
    private final String FILE_PATH;

    public Storage(String DIR_PATH, String FILE_PATH) {
        this.DIR_PATH = DIR_PATH;
        this.FILE_PATH = FILE_PATH;
    }

    public ArrayList<Task> loadData() {
        // Check if the directory exists
        Path file = this.checkDirectory(DIR_PATH, FILE_PATH);
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
                if (parts.length != 3) throw new LineCorruptedException("Incorrect Format");
                taskList.add(new ToDo(parts[2]));
                break;
            case "D":
                if (parts.length != 4) throw new LineCorruptedException("Incorrect Format");
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
                if (parts.length != 5) throw new LineCorruptedException("Incorrect Format");
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
        Path directory = Paths.get(DIR_PATH);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        // Check if file exists
        Path file = Paths.get(FILE_PATH);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        return file;
    }

    public void saveTask(ArrayList<Task> taskList) {
        // write to the file the newly added task
        String taskAsCSV = taskList.get(taskList.size() - 1).saveAsCSV();
        // try to append string to end of data file
        try {
            Path file = Paths.get(FILE_PATH);
            // Check if the file already has content
            boolean fileHasContent = Files.size(file) > 0;
            if (fileHasContent) {
                Files.writeString(file, "\n" + taskAsCSV, StandardOpenOption.APPEND);
            } else {
                Files.writeString(file, taskAsCSV, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("An error occurred saving the file");
        }
    }

    public void updateSave(ArrayList<Task> taskList, int lineNumber) {
        Path file = Paths.get(FILE_PATH);

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(file);

            // Update the specific line (lineNumber is zero-based)
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, taskList.get(lineNumber).saveAsCSV());
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
}
