package rose;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import rose.task.Deadline;
import rose.task.Event;
import rose.task.Task;
import rose.task.Todo;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 *
 * <p>If the specified file does not exist, the class will create it along with any necessary
 * directories.</p>
 */
public class Storage {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the {@code filePath}.
     *
     * <p>This method reads the contents of the file and converts each line into a {@link rose.task.Task}
     * object. The tasks are stored in an {@link java.util.ArrayList} and returned.
     *
     * <p>If the file does not exist, it is created along with any necessary directories. If the file contains
     * dates in an incorrect format, those tasks will not be loaded.</p>
     *
     * @return An {@link java.util.ArrayList} of {@link rose.task.Task} objects representing the tasks
     *         stored in the file.
     * @throws IOException If an I/O error occurs during file reading or creation.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            List<String> entries = Files.readAllLines(Paths.get(filePath));

            try {
                for (String entry : entries) {
                    String[] parts = entry.split(",", -1);
                    switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("X"), parts[3]));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2],
                                LocalDate.parse(parts[3], INPUT_FORMAT),
                                parts[1].equals("X"), parts[4]));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2],
                                LocalDate.parse(parts[3], INPUT_FORMAT),
                                LocalDate.parse(parts[4], INPUT_FORMAT),
                                parts[1].equals("X"), parts[5]));
                        break;
                    }
                }
            } catch (DateTimeParseException e) {
                System.out.println("List of tasks cant be fully loaded because date is in the wrong format");
            }
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified by the {@code filePath}.
     *
     * <p>This method writes each task in the provided {@link java.util.ArrayList} to the file in a
     * comma-separated format.
     *
     * @param tasks An {@link java.util.ArrayList} of {@link rose.task.Task} objects representing
     *              the tasks to be saved.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            writer.write(task.commaString() + System.lineSeparator());
        }

        writer.close();
    }
}
