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

import ponderpika.task.Deadline;
import ponderpika.task.Event;
import ponderpika.task.Task;
import ponderpika.task.TaskList;
import ponderpika.task.Todo;
import ponderpika.exception.PonderPikaException;

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
        try {
            if (Files.notExists(path.getParent())) {
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
        assert tasks != null;
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
            throw new PonderPikaException(e.getMessage());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            if (!FILE.exists()) {
                return list;
            }

            FileReader fr = new FileReader(FILE);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitlines = line.split("\\|");
                switch (splitlines[0].trim()) {
                case "D":
                    Deadline d = new Deadline(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(),
                            formatter));
                    if (splitlines[1].trim().equals("true")) {
                        d.markDone();
                    }

                    list.addTask(d);
                    break;
                case "E":
                    Event e = new Event(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(), formatter),
                            LocalDateTime.parse(splitlines[4].trim(), formatter));
                    if (splitlines[1].trim().equals("true")) {
                        e.markDone();
                    }

                    list.addTask(e);
                    break;
                case "T":
                    Todo t = new Todo(splitlines[2].trim());
                    if (splitlines[1].trim().equals("true")) {
                        t.markDone();
                    }
                    list.addTask(t);
                    break;
                default:
                    throw new PonderPikaException("Error Loading Data! , Starting with an Empty TaskList!");
                }
            }
        } catch (IOException ignored) {
            throw new PonderPikaException("Error Loading Data! , Starting with an Empty TaskList!");
        }
        return list;
    }
}
