package mittens.storage;

import mittens.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a file storage for saving and loading tasks.
 */
public class Storage {
    protected final Path filePath;
    
    /**
     * Creates a new Storage object with the specified file path.
     * 
     * @param filePath The file path where the tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves the task list to the storage file in CSV-like form.
     * 
     * @param taskList The task list to save
     * @throws StorageFileException If an error occurs while writing to the storage file
     */
    public void save(TaskList taskList) throws StorageFileException {
        try {
            ArrayList<String> encodedTasks = new ArrayList<>();
            for (Task task : taskList.getTasks()) {
                String taskEncoding = "";
                if (task instanceof Todo) {
                    taskEncoding = String.format("T|%s|%s",
                            task.getStatusIcon(),
                            task.getDescription());
                } else if (task instanceof Deadline) {
                    taskEncoding = String.format("D|%s|%s|%s",
                            task.getStatusIcon(),
                            task.getDescription(),
                            ((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskEncoding = String.format("E|%s|%s|%s|%s",
                            task.getStatusIcon(),
                            task.getDescription(),
                            ((Event) task).getFrom(),
                            ((Event) task).getTo());
                }
                encodedTasks.add(taskEncoding);
            }
            Files.write(this.filePath, encodedTasks, java.nio.file.StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new StorageFileException("Unable to write to storage file");
        }
    }
    
    /**
     * Loads the task list from the storage file.
     * 
     * @return The task list obtained by decoding the storage file
     * @throws StorageFileException If an error occurs while reading from the storage file
     * or if the file is corrupted
     */
    public TaskList load() throws StorageFileException {
        if (!Files.exists(this.filePath)) {
            try {
                Files.createDirectories(this.filePath.getParent());
                Files.createFile(this.filePath);
            } catch (IOException e) {
                throw new StorageFileException("Unable to create storage file");
            }
            return new TaskList();
        }
        
        try {
            ArrayList<String> encodedTasks = new ArrayList<>(Files.readAllLines(this.filePath));
            TaskList taskList = new TaskList();
            for (String encodedTask : encodedTasks) {
                String[] taskComponents = encodedTask.split("\\|");
                Task task = null;
                switch (taskComponents[0]) {
                    case "T":
                        task = new Todo(taskComponents[2]);
                        break;
                    case "D":
                        task = new Deadline(taskComponents[2], LocalDate.parse(taskComponents[3]));
                        break;
                    case "E":
                        task = new Event(taskComponents[2], LocalDate.parse(taskComponents[3]),
                                LocalDate.parse(taskComponents[4]));
                        break;
                    default:
                        throw new StorageFileException("Corrupted storage file");
                }
                if (taskComponents[1].equals("X")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
            }
            return taskList;
        } catch (IOException e) {
            throw new StorageFileException("Unable to read from storage file");
        } catch (DateTimeParseException e) {
            throw new StorageFileException("Corrupted storage file");
        }
    }
}
