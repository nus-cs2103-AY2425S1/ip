package optimus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//Documentation done with help of chatGPT
//Also used AI to help refine and refactor the storage method to improve quality of code
/**
 * The {@code Storage} class handles loading and saving tasks to and from a file.
 * It reads from an existing data file to initialize tasks and saves tasks back
 * to the file when changes are made.
 */
public class Storage {
    private String filepath;

    /**
     * Constructs a {@code Storage} object to manage saving and loading data to the specified file path.
     *
     * @param filepath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file at the specified {@code filepath}.
     * If the file does not exist, an empty list is returned, and a new file is created upon saving.
     *
     * @return A list of {@code Task} objects read from the file.
     * @throws FileNotFoundException If the file is not found.
     * @throws OptimusException If the file contains invalid task data.
     */
    List<Task> loadFile() throws FileNotFoundException, OptimusException {
        File f = new File(filepath);
        List<Task> record = new ArrayList<>();

        if (!f.exists()) {
            System.out.println("No existing data file found in the given directory. A new record will be established.");
            return record;
        }

        Scanner s = new Scanner(f);
        processFileLines(s, record);
        s.close();

        return record;
    }

    /**
     * Processes the lines in the file and converts them into {@code Task} objects.
     *
     * @param s      The {@code Scanner} object for reading lines from the file.
     * @param record The list to store the processed {@code Task} objects.
     * @throws OptimusException If a task cannot be created due to invalid data format.
     */
    private void processFileLines(Scanner s, List<Task> record) throws OptimusException {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = createTask(taskType, description, parts);

            if (task != null) {
                if (isDone) {
                    task.setDone();
                }
                record.add(task);
            }
        }
    }

    /**
     * Creates a {@code Task} object based on the task type and data provided.
     *
     * @param taskType    The type of task (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     * @param description The description of the task.
     * @param parts       The array of task data split from the file line.
     * @return A {@code Task} object, or {@code null} if the task type is unrecognized.
     * @throws OptimusException If an invalid task type or data is encountered.
     */
    private Task createTask(String taskType, String description, String[] parts) throws OptimusException {
        switch (taskType) {
            case "T":
                return new ToDo(description);
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                return new Deadline(description, by);
            case "E":
                String from = parts.length > 3 ? parts[3] : "";
                String to = parts.length > 4 ? parts[4] : "";
                return new Event(description, from, to);
            default:
                return null;
        }
    }

    /**
     * Saves the list of tasks to the file specified by {@code filepath}.
     *
     * @param record The list of {@code Task} objects to save.
     * @throws IOException If an error occurs during writing to the file.
     */
    public void saveToFile(List<Task> record) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task task : record) {
            fw.write(task.toSaveString() + System.lineSeparator());
        }
        fw.close();
    }
}
