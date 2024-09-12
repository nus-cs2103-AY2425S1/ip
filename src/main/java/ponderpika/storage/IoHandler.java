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

import ponderpika.exception.PonderPikaException;
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
    private static final File FILE = new File("./data/pika.txt");

    /**
     * Creates the necessary directories or the file if it does not exist.
     * Throws a PonderPikaException if an I/O error.
     *
     * @throws PonderPikaException if an I/O error occurs while creating the file or directories
     */
    public void create() throws PonderPikaException {
        Path path = Paths.get(FILE.getPath());
        Path parentPath = path.getParent();
        try {
            if (Files.notExists(parentPath)) {
                Files.createDirectories(path.getParent());
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new PonderPikaException(e.getMessage());
        }
    }

    /**
     * Saves the provided list of tasks to the file.
     * Creates the file if it does not already exist.
     * Throws a PonderPikaException if an I/O error occurs.
     *
     * @param tasks the list of tasks to be saved
     * @throws PonderPikaException if an I/O error occurs while writing to the file
     */
    public void saveData(TaskList tasks) throws PonderPikaException {
        try {
            if (!FILE.exists()) {
                create();
            }

            FileWriter fw = new FileWriter(FILE);
            for (Task task : tasks.getTasks()) {
                String saveFormat = task.saveFullDetails();
                fw.write(saveFormat);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new PonderPikaException("Could not write into file, Encountered formatting issues!");
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     * If the file does not exist or is empty, returns an empty list.
     *
     * @return an ArrayList of tasks loaded from the file
     */
    public TaskList loadData() throws PonderPikaException {
        TaskList list = new TaskList();
        if (!FILE.exists()) {
            return list;
        }

        try (FileReader fr = new FileReader(FILE); Scanner sc = new Scanner(fr)) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                processFileFormat(line, list);
            }
        } catch (IOException e) {
            throw new PonderPikaException("Error Loading Data! , Starting with an Empty TaskList!");
        }

        return list;
    }

    private void processFileFormat(String line, TaskList list) throws PonderPikaException {
        String[] splitlines = line.split("\\|");
        if (splitlines.length < 3) {
            throw new PonderPikaException("Error Loading Data! , Starting with an Empty TaskList!");
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
            throw new PonderPikaException("Error Loading Data! , Starting with an Empty TaskList!");
        }
    }

    private Deadline createDeadline(String[] splitlines) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Deadline(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(), formatter));
    }

    private Event createEvent(String[] splitlines) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Event(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(), formatter),
                LocalDateTime.parse(splitlines[4].trim(), formatter));
    }

    private Todo createTodo(String[] splitlines) {
        return new Todo(splitlines[2].trim());
    }
}
