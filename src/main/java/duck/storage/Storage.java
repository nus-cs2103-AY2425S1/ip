package duck.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.TaskType;
import duck.data.task.ToDo;
import duck.util.Utils;

/**
 * Manages the loading and saving of tasks from/to a file.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Constructs a Storage object with the specified file path relative to the user's home directory.
     * If the file or its directory does not exist, they will be created.
     *
     * @param filePath The relative path to the file where tasks are stored, starting from the user's home directory.
     * @throws DuckException If there is an error creating the file or directory.
     */
    public Storage(String filePath) throws DuckException {
        this.filePath = new File(System.getProperty("user.home"), filePath).getAbsolutePath();
        file = createFileIfDoesNotExist(this.filePath);
    }

    /**
     * Returns the file managed by this Storage object.
     *
     * @return The file managed by this Storage object.
     */
    public File getFile() {

        return file;
    }

    /**
     * Creates the file and its directory if they do not exist.
     *
     * @param filePath The absolute path to the file to be created.
     * @return The created file.
     * @throws DuckException If there is an error creating the file or directory.
     */
    private File createFileIfDoesNotExist(String filePath) throws DuckException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists() && !directory.mkdirs()) {
                throw new DuckException("Error creating directory: " + directory.getPath());
            }
            if (file.createNewFile()) {
                System.out.println("New file created at: " + file.getPath());
            }

            assert file != null : "File is still null after initializing";
            return file;
        } catch (IOException e) {
            throw new DuckException("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and adds them to the given TaskList.
     *
     * @param tasks The TaskList to which the tasks will be added.
     * @throws DuckException If there is an error reading the file or if the file format is incorrect.
     */
    public void loadTasks(TaskList tasks) throws DuckException {
        try {
            assert file != null;

            Scanner sc = new Scanner(file);
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine();
                String[] words = line.split(" \\| ");
                Task task = null;

                switch (words[0]) {
                case "T":
                    if (hasCorrectFileFormat(words, TaskType.TODO)) {
                        task = new ToDo(
                                getTaskDoneBoolean(words[1]),
                                words[2]);
                    }
                    break;
                case "D":
                    if (hasCorrectFileFormat(words, TaskType.DEADLINE)) {
                        task = new Deadline(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                Utils.convertToDateTime(words[3]));
                    }
                    break;
                case "E":
                    if (hasCorrectFileFormat(words, TaskType.EVENT)) {
                        task = new Event(
                                getTaskDoneBoolean(words[1]),
                                words[2],
                                Utils.convertToDateTime(words[3]),
                                Utils.convertToDateTime(words[4]));
                    }
                    break;
                default:
                    break;
                }

                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("WARNING\nSkipping invalid line " + lineNumber
                            + "\n" + line + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckException("File not found: " + file.getPath());
        }
    }

    /**
     * Writes all tasks from the given TaskList to the file, overwriting the existing contents.
     *
     * @param tasks The TaskList containing tasks to be written to the file.
     * @throws DuckException If there is an error writing to the file.
     */
    public void writeTasks(TaskList tasks) throws DuckException {
        assert tasks != null : "Tasks is null!";

        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                assert task != null : "Trying to write null task into tasks";

                fw.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DuckException("Error updating file:\n" + e.getMessage());
        }
    }

    /**
     * Appends a task to the file.
     *
     * @param task The task to be appended.
     * @throws DuckException If there is an error writing to the file.
     */
    public void appendTask(Task task) throws DuckException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        } catch (IOException e) {
            throw new DuckException("Error writing to file:\n" + e.getMessage());
        }
    }

    private File getFileDirectory(String filePath) {
        return new File(filePath.substring(0, filePath.lastIndexOf('/')));
    }

    private boolean hasCorrectFileFormat(String[] words, TaskType type) {
        try {
            switch (type) {
            case TODO:
                return words.length == 3
                        && hasCorrectDoneFormat(words[1])
                        && !words[2].isEmpty();
            case DEADLINE:
                if (words.length == 4) {
                    Utils.convertToDateTime(words[3]);
                    return hasCorrectDoneFormat(words[1]) && !words[2].isEmpty();
                } else {
                    return false;
                }
            case EVENT:
                if (words.length == 5) {
                    return hasCorrectDoneFormat(words[1])
                            && !words[2].isEmpty()
                            && Utils.convertToDateTime(words[3])
                            .isBefore(Utils.convertToDateTime(words[4]));
                } else {
                    return false;
                }
            default:
                return false;
            }
        } catch (DuckException e) {
            return false;
        }
    }

    private boolean hasCorrectDoneFormat(String isDone) {
        return isDone.equals("0") || isDone.equals("1");
    }

    private boolean getTaskDoneBoolean(String isDone) throws DuckException {
        if (!hasCorrectDoneFormat(isDone)) {
            throw new DuckException("Invalid task status in file: " + isDone);
        }
        return isDone.equals("1");
    }
}
