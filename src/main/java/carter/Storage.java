package carter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represent the storage for the task in Carter.
 */
public class Storage {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String filePath;


    /**
     * Constructs a new instance of Storage with specified file path.
     *
     * @param filePath The file path where the tasks to be stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "The file path should not be null";
        this.filePath = filePath;
    }

    /**
     * Load the tasks from the storage file.
     * If the file does not exist, it will be created.
     *
     * @return A list of tasks from storage file.
     * @throws CarterException If the file is corrupted.
     */
    public List<Task> load() throws CarterException {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            boolean fileNotExist = !file.exists();

            if (fileNotExist) {
                createFile(file);
                return tasks;
            }

            List<String> data = readFile(file);

            for (String row : data) {
                tasks.add(parseTask(row));
            }
        } catch (IOException e) {
            throw new CarterException("Error loading task from the file");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks into the storage file.
     *
     * @param tasks tasks to be saved
     * @throws CarterException If there is an error saving the task into the storage file.
     */
    public void save(List<Task> tasks) throws CarterException {
        assert tasks != null : "task list should not be null";
        writeFile(tasks, filePath);
    }

    /**
     * Archives the list of tasks into the archive file.
     *
     * @param tasks tasks to be archived.
     * @throws CarterException If there is an error archive the task into archive file.
     */
    public void archive(List<Task> tasks) throws CarterException {
        File originalFile = new File(filePath);
        File directory = originalFile.getParentFile();
        File archiveFile = new File(directory, "archive.txt");

        try {
            createFile(archiveFile);
            writeFile(tasks, archiveFile.getPath());
        } catch (IOException e) {
            throw new CarterException("Error create a new txt file");
        }
    }

    private void writeFile(List<Task> tasks, String filePath) throws CarterException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                assert task != null : "task should not be null";
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new CarterException("Error saving data to file");
        }
    }

    private void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private List<String> readFile(File file) throws IOException {
        List<String> data = new LinkedList<>();
        Scanner s = new Scanner(file);

        while (s.hasNext()) {
            data.add(s.nextLine());
        }

        return data;
    }

    private Task parseTask(String line) throws CarterException {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");

        switch(taskType) {
        case "T":
            return new ToDo(parts[2].trim(), isDone);
        case "D":
            return new Deadline(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), FORMATTER), isDone);
        case "E":
            return new Event(parts[2].trim(),
                    LocalDateTime.parse(parts[3].trim(), FORMATTER),
                    LocalDateTime.parse(parts[4].trim(), FORMATTER), isDone);
        default:
            throw new CarterException("File has been corrupted. Invalid task type: " + taskType);
        }
    }
}
