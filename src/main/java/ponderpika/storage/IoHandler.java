package ponderpika.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import ponderpika.exception.CreateFileFormatException;
import ponderpika.exception.DuplicateTaskException;
import ponderpika.exception.LoadFileFormatException;
import ponderpika.exception.PonderPikaException;
import ponderpika.exception.ProcessFileFormatException;
import ponderpika.exception.SaveFileFormatException;
import ponderpika.task.Deadline;
import ponderpika.task.Event;
import ponderpika.task.Task;
import ponderpika.task.TaskList;
import ponderpika.task.Todo;

/**
 * This class is responsible for handling file operations.
 * It includes methods for creating a file, saving task data to a file, and loading task data from a file.
 */
public class IoHandler {
    private File file = null;

    public IoHandler(String filePath) {
        this.file = new File(filePath);
    }
    /**
     * Creates the necessary directories or the file if it does not exist.
     * Throws a PonderPikaException if an I/O error.
     *
     * @throws CreateFileFormatException if an I/O error occurs while creating the file or directories
     */
    public void create() throws CreateFileFormatException {
        Path path = Paths.get(file.getPath());
        Path parentPath = path.getParent();
        try {
            if (Files.notExists(parentPath)) {
                Files.createDirectories(path.getParent());
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new CreateFileFormatException();
        }
    }

    /**
     * Saves the provided list of tasks to the file.
     * Creates the file if it does not already exist.
     * Throws a PonderPikaException if an I/O error occurs.
     *
     * @param tasks the list of tasks to be saved
     * @throws SaveFileFormatException if an I/O error occurs while writing to the file
     */
    public void saveData(TaskList tasks) throws SaveFileFormatException {
        assert tasks != null;
        try {
            if (!file.exists()) {
                create();
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                String saveFormat = task.saveFullDetails();
                fw.write(saveFormat);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException | PonderPikaException e) {
            throw new SaveFileFormatException();
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     * If the file does not exist or is empty, returns an empty list.
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws LoadFileFormatException if there are loading errors due to file format issues
     */
    public TaskList loadData() throws LoadFileFormatException {
        TaskList list = new TaskList();
        if (!file.exists()) {
            return list;
        }

        try (FileReader fr = new FileReader(file); Scanner sc = new Scanner(fr)) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                processFileFormat(line, list);
            }
        } catch (PonderPikaException | IOException e) {
            throw new LoadFileFormatException();
        }

        return list;
    }

    /**
     * Processes a line of text representing a task and adds it to the specified task list.
     * <p>
     * This method parses the input line to determine the type of task (Deadline, Event, or To-do),
     * checks if the task is marked as done, and adds it to the provided task list. The line must contain
     * at least four segments separated by a pipe ('|') character
     * </p>
     *
     * @param line the line of text to be processed, containing task information
     * @param list the task list to which the task will be added
     * @throws LoadFileFormatException if the line does not contain the expected number of segments
     * @throws ProcessFileFormatException if the task type is invalid or cannot be processed
     * @throws DuplicateTaskException if the task being added is a duplicate of an existing task
     */
    private void processFileFormat(String line, TaskList list) throws LoadFileFormatException,
            ProcessFileFormatException, DuplicateTaskException {
        String[] splitlines = line.split("\\|");
        if (splitlines.length < 4) {
            throw new LoadFileFormatException();
        }

        String taskType = splitlines[0].trim();
        boolean isDone = "true".equals(splitlines[1].trim());
        switch (taskType) {
        case "D":
            Deadline d = createDeadline(splitlines);
            if (isDone) {
                d.markDone();
            }
            list.addTask(d);
            break;
        case "E":
            Event e = createEvent(splitlines);
            if (isDone) {
                e.markDone();
            }
            list.addTask(e);
            break;
        case "T":
            Todo t = createTodo(splitlines);
            if (isDone) {
                t.markDone();
            }
            list.addTask(t);
            break;
        default:
            throw new ProcessFileFormatException();
        }
    }

    /**
     * Creates a Deadline object from the provided array of strings.
     * <p>
     * This method extracts the task description and the due date from the input array,
     * and parses the date and time using the specified format
     * </p>
     *
     * @param splitlines an array of strings containing task details, where the description
     *                   is at index 2 and the due date at index 3
     * @return a Deadline object with the specified description and due date
     */
    private Deadline createDeadline(String[] splitlines) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Deadline(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(), formatter));
    }

    /**
     * Creates an Event object from the provided array of strings.
     * <p>
     * This method extracts the task description and the start and end times from the input array,
     * and parses the date and time using the specified format
     * </p>
     *
     * @param splitlines an array of strings containing task details, where the description
     *                   is at index 2, the start time at index 3, and the end time at index 4
     * @return an Event object with the specified description, start time, and end time
     */
    private Event createEvent(String[] splitlines) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Event(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(), formatter),
                LocalDateTime.parse(splitlines[4].trim(), formatter));
    }

    /**
     * Creates a To-do object from the provided array of strings.
     * <p>This method extracts the task description from the input array.</p>
     *
     * @param splitlines an array of strings containing task details, where the description
     *                   is at index 2
     * @return a To-do object with the specified description
     */
    private Todo createTodo(String[] splitlines) {
        return new Todo(splitlines[2].trim());
    }
}
