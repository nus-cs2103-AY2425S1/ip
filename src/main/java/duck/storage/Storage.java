package duck.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duck.common.Utils;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.data.task.Event;
import duck.data.task.Task;
import duck.data.task.TaskType;
import duck.data.task.ToDo;


/**
 * Manages the loading and saving of tasks from/to a file.
 */
public class Storage {

    private static final String ERROR_CREATING_DIRECTORY = "Error creating directory: ";
    private static final String ERROR_CREATING_FILE = "Error creating file: ";
    private static final String ERROR_UPDATING_FILE = "Error updating file:\n";
    private static final String ERROR_WRITING_TO_FILE = "Error writing to file:\n";
    private static final String ERROR_FILE_NOT_FOUND = "File not found: ";
    private static final String FILE_FORMAT_DONE = "0";
    private static final String FILE_FORMAT_NOT_DONE = "1";
    private static final String FILE_FORMAT_TODO = "T";
    private static final String FILE_FORMAT_DEADLINE = "D";
    private static final String FILE_FORMAT_EVENT = "E";
    private static final String INVALID_LINE_WARNING = "WARNING\nSkipping invalid line ";
    private static final String INVALID_TASK_STATUS_IN_FILE = "Invalid task status in file: ";

    private static final int INDEX_DONE_STATUS = 1;
    private static final int INDEX_DESCRIPTION = 2;
    private static final int INDEX_DEADLINE_BY = 3;
    private static final int INDEX_EVENT_FROM = 3;
    private static final int INDEX_EVENT_TO = 4;
    private static final String KEY_BASE_PATH = "user.home";
    private static final String NEW_FILE_CREATED = "New file created at: ";
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
        this.filePath = new File(System.getProperty(KEY_BASE_PATH), filePath).getAbsolutePath();
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
                throw new DuckException(ERROR_CREATING_DIRECTORY + directory.getPath());
            }
            if (file.createNewFile()) {
                System.out.println(NEW_FILE_CREATED + file.getPath());
            }

            assert file != null : "File is still null after initializing";
            return file;
        } catch (IOException e) {
            throw new DuckException(ERROR_CREATING_FILE + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and adds them to the given TaskList.
     *
     * @param tasks The TaskList to which the tasks will be added.
     * @throws DuckException If there is an error reading the file or if the file format is incorrect.
     */
    public void loadTasks(TaskList tasks) throws DuckException {

        assert file != null;
        try (Scanner sc = new Scanner(file)) {
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine();
                Task task = parseTaskFromLine(line, lineNumber);

                if (task != null) {
                    tasks.add(task);
                } else {
                    logInvalidLine(line, lineNumber);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckException(ERROR_FILE_NOT_FOUND + file.getPath());
        }
    }

    /**
     * Parses a task from a line in the file.
     *
     * @param line The line to parse.
     * @param lineNumber The line number in the file.
     * @return The parsed Task, or null if the line is invalid.
     */
    private Task parseTaskFromLine(String line, int lineNumber) throws DuckException {
        String[] words = line.split(" \\| ");
        Task task = null;

        //CHECKSTYLE.OFF: Indentation
        switch (words[0]) {
            case FILE_FORMAT_TODO -> task = parseToDoTask(words);
            case FILE_FORMAT_DEADLINE -> task = parseDeadlineTask(words);
            case FILE_FORMAT_EVENT -> task = parseEventTask(words);
            default -> logInvalidLine(line, lineNumber);
        }
        //CHECKSTYLE.ON: Indentation
        return task;
    }

    private Task parseToDoTask(String[] words) throws DuckException {
        if (hasCorrectFileFormat(words, TaskType.TODO)) {
            return new ToDo(
                    getTaskDoneBoolean(words[INDEX_DONE_STATUS]),
                    words[INDEX_DESCRIPTION]);
        }
        return null;
    }

    private Task parseDeadlineTask(String[] words) throws DuckException {
        if (hasCorrectFileFormat(words, TaskType.DEADLINE)) {
            return new Deadline(
                    getTaskDoneBoolean(words[INDEX_DONE_STATUS]),
                    words[INDEX_DESCRIPTION],
                    Utils.convertToDateTime(words[INDEX_DEADLINE_BY]));
        }
        return null;
    }

    private Task parseEventTask(String[] words) throws DuckException {
        if (hasCorrectFileFormat(words, TaskType.EVENT)) {
            return new Event(
                    getTaskDoneBoolean(words[INDEX_DONE_STATUS]),
                    words[INDEX_DESCRIPTION],
                    Utils.convertToDateTime(words[INDEX_EVENT_FROM]),
                    Utils.convertToDateTime(words[INDEX_EVENT_TO]));
        }
        return null;
    }

    /**
     * Logs an invalid line with a warning message.
     *
     * @param line The invalid line.
     * @param lineNumber The line number where the error occurred.
     */
    private void logInvalidLine(String line, int lineNumber) {
        System.out.println(INVALID_LINE_WARNING + lineNumber + "\n" + line + "\n");
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
            throw new DuckException(ERROR_UPDATING_FILE + e.getMessage());
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
            throw new DuckException(ERROR_WRITING_TO_FILE + e.getMessage());
        }
    }

    /**
     * Checks if the task's file format is correct based on the task type.
     *
     * @param words The task details split from the file line.
     * @param type The type of task (TODO, DEADLINE, EVENT).
     * @return true if the format is correct, false otherwise.
     */
    private boolean hasCorrectFileFormat(String[] words, TaskType type) {
        try {
            // CHECKSTYLE.OFF: Indentation
            return switch (type) {
                case TODO -> isValidToDoFormat(words);
                case DEADLINE -> isValidDeadlineFormat(words);
                case EVENT -> isValidEventFormat(words);
                default -> false;
            };
            // CHECKSTYLE.ON: Indentation
        } catch (DuckException e) {
            return false;
        }
    }

    /**
     * Validates the format for a ToDo task.
     *
     * @param words The split task details.
     * @return true if the format is correct, false otherwise.
     */
    private boolean isValidToDoFormat(String[] words) {
        return words.length == 3
                && hasCorrectDoneFormat(words[INDEX_DONE_STATUS])
                && !words[INDEX_DESCRIPTION].isEmpty();
    }

    /**
     * Validates the format for a Deadline task.
     *
     * @param words The split task details.
     * @return true if the format is correct, false otherwise.
     */
    private boolean isValidDeadlineFormat(String[] words) throws DuckException {
        if (words.length == 4) {
            Utils.convertToDateTime(words[INDEX_DEADLINE_BY]); // Ensure date is valid.
            return hasCorrectDoneFormat(words[INDEX_DONE_STATUS])
                    && !words[INDEX_DESCRIPTION].isEmpty();
        }
        return false;
    }

    /**
     * Validates the format for an Event task.
     *
     * @param words The split task details.
     * @return true if the format is correct, false otherwise.
     */
    private boolean isValidEventFormat(String[] words) throws DuckException {
        if (words.length == 5) {
            return hasCorrectDoneFormat(words[INDEX_DONE_STATUS])
                    && !words[INDEX_DESCRIPTION].isEmpty()
                    && Utils.convertToDateTime(words[INDEX_EVENT_FROM])
                    .isBefore(Utils.convertToDateTime(words[INDEX_EVENT_TO]));
        }
        return false;
    }

    private boolean hasCorrectDoneFormat(String isDone) {
        return isDone.equals(FILE_FORMAT_DONE)
                || isDone.equals(FILE_FORMAT_NOT_DONE);
    }

    private boolean getTaskDoneBoolean(String isDone) throws DuckException {
        if (!hasCorrectDoneFormat(isDone)) {
            throw new DuckException(INVALID_TASK_STATUS_IN_FILE + isDone);
        }
        return isDone.equals(FILE_FORMAT_NOT_DONE);
    }
}
