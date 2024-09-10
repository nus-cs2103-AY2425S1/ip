package jay.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;
import jay.task.DeadlineTask;
import jay.task.EventTask;
import jay.task.Task;
import jay.task.ToDoTask;

/**
 * Represents the storage of tasks.
 */
public class Storage {
    private final Path folderPath;
    private final Path filePath;

    /**
     * Constructs a storage object.
     *
     * @param folderName The name of the folder to store the tasks.
     * @param fileName The name of the file to store the tasks.
     */
    public Storage(String folderName, String fileName) {
        this.folderPath = Paths.get(folderName);
        this.filePath = Paths.get(folderName, fileName);
    }

    /**
     * Saves the tasks to the file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public void saveTasks(String taskList) throws DataIOException {
        try {
            // Create the folder if it does not exist
            if (Files.notExists(this.folderPath)) {
                Files.createDirectories(this.folderPath);
            }
            // Open or create the file if it does not exist
            Files.write(filePath, taskList.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new DataIOException("OOPS!!! There was an error saving the tasks to the file.");
        }
    }

    //CHECKSTYLE.OFF: Indentation
    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws DataIOException If there is an error loading the tasks.
     * @throws InvalidDataFormatException If the data format is invalid.
     */
    public ArrayList<Task> loadTasks() throws DataIOException, InvalidDataFormatException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            if (Files.notExists(this.filePath)) {
                throw new DataIOException("OOPS!!! The file does not exist.");
            }

            String fileContent = Files.readString(filePath);
            String[] tasksStr = fileContent.split("\n");

            for (String taskStr : tasksStr) {
                String[] taskDetails = taskStr.split("\\|");
                Task.Type taskType = null; // preserve the task type
                switch (taskDetails[0].trim()) {
                case "T":
                    taskType = Task.Type.ToDo;
                    break;
                case "D":
                    taskType = Task.Type.Deadline;
                    break;
                case "E":
                    taskType = Task.Type.Event;
                    break;
                default:
                    throw new InvalidDataFormatException("OOPS!!! "
                            + "There was an error loading the tasks from the file.");
                }
                boolean isDone = taskDetails[1].trim().equals("1");
                String description = taskDetails[2].trim();
                // preserve the date for deadline and event tasks
                String date = "";

                switch (taskType) {
                case ToDo:
                    tasks.add(new ToDoTask(description, isDone));
                    break;
                case Deadline:
                    date = taskDetails[3].trim();
                    tasks.add(new DeadlineTask(description, isDone, date));
                    break;
                case Event:
                    date = taskDetails[3].trim();
                    String startTime = taskDetails[4].trim();
                    String endTime = taskDetails[5].trim();

                    tasks.add(new EventTask(description, isDone, date, startTime, endTime));
                    break;
                default:
                    throw new DataIOException("OOPS!!! There was an error loading the tasks from the file.");
                }
            }

           return tasks;
        } catch (IOException e) {
            throw new DataIOException("OOPS!!! There was an error loading the tasks from the file.");
        } catch (InvalidDateException | InvalidTimeException e) {
            throw new InvalidDataFormatException("OOPS!!! There was an error loading the tasks from the file."
                    + " Please check the date and time format.");
        }
    }
}
