package barney.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import barney.data.TaskList;
import barney.data.exception.BarneyException;
import barney.data.exception.InvalidSaveFormatException;
import barney.data.task.DeadlineTask;
import barney.data.task.EventTask;
import barney.data.task.Task;
import barney.data.task.TodoTask;

/**
 * The Storage class is responsible for loading and saving data to a file.
 */
public class Storage {

    private static final String SAVE_FILE_DELIMITER = "###";
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to save/load data from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads a file and returns an ArrayList of Task objects.
     *
     * @return The ArrayList of Task objects read from the file.
     * @throws FileNotFoundException      If the file specified by the filePath does
     *                                    not exist.
     * @throws InvalidSaveFormatException If the file has an invalid format or
     *                                    contains invalid data.
     */
    private ArrayList<Task> readFile() throws FileNotFoundException, InvalidSaveFormatException {
        ArrayList<Task> taskList = new ArrayList<>();
        File listFile = new File(filePath);
        try (Scanner fileScanner = new Scanner(listFile)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] taskData = line.split(SAVE_FILE_DELIMITER);

                Task newTask;

                // description
                String description = taskData[1];

                // tag
                String tag = taskData[2];

                // taskType
                String taskType = taskData[3];
                switch (taskType) {
                case "T" -> newTask = new TodoTask(description);
                case "D" -> newTask = new DeadlineTask(description, taskData[4]);
                case "E" -> newTask = new EventTask(description, taskData[4], taskData[5]);
                default -> throw new InvalidSaveFormatException("Invalid task type in the file: " + taskData[3]);
                }

                // isMarked
                switch (taskData[0]) {
                case "1" -> newTask.mark();
                case "0" -> newTask.unmark();
                default -> throw new InvalidSaveFormatException("Invalid task status in the file: " + taskData[1]);
                }

                // tag
                if (!tag.isEmpty()) {
                    newTask.setTag(tag);
                }

                taskList.add(newTask);
            }
        }
        return taskList;
    }

    /**
     * Loads data from a file.
     *
     * @return An ArrayList of Task objects containing the loaded data.
     * @throws BarneyException If the file is not found or there is an error reading
     *                         the file.
     */
    public ArrayList<Task> loadData() throws BarneyException {
        try {
            return readFile();
        } catch (FileNotFoundException e) {
            throw new BarneyException("File not found: " + e.getMessage());
        } catch (Exception e) {
            throw new BarneyException("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Writes the given list of tasks to a file.
     *
     * @param taskList the list of tasks to be written
     * @throws FileNotFoundException if the file specified by the file path cannot
     *                               be found
     * @throws IOException           if an I/O error occurs while writing to the
     *                               file
     */
    private void writeFile(ArrayList<Task> taskList) throws FileNotFoundException, IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : taskList) {
                for (String data : task.toSaveArray()) {
                    fileWriter.write(data + SAVE_FILE_DELIMITER);
                }
                fileWriter.write("\n");
            }
        }
    }

    /**
     * Writes the data from the given TaskList to a file.
     *
     * @param taskList the TaskList containing the data to be written
     * @throws BarneyException if there is an error writing the file
     */
    public void writeData(TaskList taskList) throws BarneyException {
        try {
            writeFile(taskList.getArrayList());
        } catch (FileNotFoundException e) {
            throw new BarneyException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new BarneyException("Error writing file: " + e.getMessage());
        } catch (Exception e) {
            throw new BarneyException(e.getMessage());
        }
    }
}
