package axel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Storage} class handles the loading and saving of tasks to a file.
 * It supports the serialization and deserialization of tasks in the specific format.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file to load from or save to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file specified by {@code filePath}.
     * If the file does not exist, a new file is created.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file reading.
     * @throws CorruptedFileException If the file content is invalid or corrupted.
     */
    public List<Task> load() throws IOException, CorruptedFileException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new CorruptedFileException("Corrupted data in file: " + line);
                }

                Task task;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T":
                    task = new ToDoTask(description);
                    break;

                case "D":
                    if (parts.length < 4) {
                        throw new CorruptedFileException("Incomplete DeadlineTask data in file: " + line);
                    }
                    String by = parts[3];
                    task = new DeadlineTask(description, by);
                    break;

                case "E":
                    if (parts.length < 5) {
                        throw new CorruptedFileException("Incomplete EventTask data in file: " + line);
                    }
                    String from = parts[3];
                    String to = parts[4];
                    task = new EventTask(description, from, to);
                    break;

                default:
                    throw new CorruptedFileException("Invalid task type in file: " + type);
                }

                if (isDone) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Saves the given list of tasks to the file specified by {@code filePath}.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void save(List<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }
}
