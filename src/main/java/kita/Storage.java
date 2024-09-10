package kita;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles reading and writing tasks to a file.
 */
public class Storage {
    private static final String LOCATION_DIR = "data";
    private static final String LOCATION_FILENAME = "kita.txt";
    private static final String LOCATION = LOCATION_DIR + "/" + LOCATION_FILENAME;
    private File saveFile;
    private Path saveFilePath;

    /**
     * Constructor for the Storage class.
     *
     * @throws FileSystemException if there is an error with the file system.
     * @throws IOException         if an I/O error occurs.
     */
    public Storage() throws IOException {
        this.saveFile = new File(LOCATION);
        if (!this.saveFile.exists() || !this.saveFile.isFile()) {
            System.out.println("Save file does not exist, creating one now at " + LOCATION);
            this.createSaveFile();
        }

        this.saveFilePath = Paths.get(LOCATION);
    }

    /**
     * Creates a new file at the specified path - regardless of whether the file exists or not.
     * The directory will also be created along the path if it does not exist.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void createSaveFile() throws IOException {
        File directory = new File(LOCATION_DIR);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.println("Failed to create directory: " + LOCATION_DIR);
                throw new FileSystemException(LOCATION_DIR);
            }
        }

        if (!this.saveFile.createNewFile()) {
            System.out.println("Failed to create file: " + LOCATION);
            throw new FileSystemException(LOCATION);
        }
    }

    /**
     * Returns the tasks from the file as an ArrayList of Task objects.
     *
     * @return an ArrayList of Task objects.
     * @throws IOException if an I/O error occurs.
     */
    public ArrayList<Task> readTasksFromFile() throws IOException {
        try {
            assert saveFilePath != null;
            List<String> lines = Files.readAllLines(this.saveFilePath);
            ArrayList<Task> savedTasks = new ArrayList<>();
            for (String line : lines) {
                String[] splitLine = line.split(",");
                assert splitLine.length >= 1;
                Task taskToAdd;
                if (splitLine[0].equals("E")) {
                    taskToAdd = new Event(splitLine[2], splitLine[3], splitLine[4]);
                } else if (splitLine[0].equals("D")) {
                    taskToAdd = new Deadline(splitLine[2], splitLine[3]);
                } else {
                    taskToAdd = new ToDo(splitLine[2]);
                }
                taskToAdd.setCompleted(splitLine[1].equals("0") ? false : true);
                savedTasks.add(taskToAdd);
            }
            return savedTasks;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Format error - file might be corrupted, returning an empty task list instead.");
            return new ArrayList<>();
        }
    }

    /**
     * Writes the tasks to the save file.
     *
     * @param commandList The ArrayList of Task objects to save.
     * @throws IOException if an I/O error occurs.
     */
    public void writeTasksToFile(ArrayList<Task> commandList) throws IOException {
        ArrayList<String> taskStrings = new ArrayList<>();

        for (Task task : commandList) {
            String type = task.type();
            String checked = task.getCompleted() ? "1" : "0";
            String name = task.getName();
            String additionalProperties;

            if (type.equals("E")) {
                Event eventTask = (Event) task;
                additionalProperties = "," + eventTask.getFrom() + "," + eventTask.getTo();
            } else if (type.equals("D")) {
                Deadline deadlineTask = (Deadline) task;
                additionalProperties = "," + deadlineTask.getByTime();
            } else {
                additionalProperties = "";
            }

            taskStrings.add(type + "," + checked + "," + name + additionalProperties);
        }
        assert saveFilePath != null;
        Files.write(saveFilePath, taskStrings);
    }
}
