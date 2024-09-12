package duke;

import duke.Exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Storage class to handle reading and writing to file.
 */
public class Storage {
    private static Path filePath;
    private String line;
    private final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
    public Storage(String filePath) throws DukeException {
        Storage.filePath = Paths.get(filePath);
        createFile();
    }

    /**
     * Creates directory if it does not exist.
     * @throws DukeException if error creating directory
     */
    private void createDirectory() throws DukeException {
        Path directory = filePath.getParent();
        if (Files.notExists(directory)) {
            try {
                Files.createDirectories(directory);
                System.out.println("new directory created: " + directory);
            } catch (IOException e) {
                throw new DukeException("Error creating directory");
            }
        }
        assert Files.exists(directory) : "Directory should exist";
    }

    /**
     * Checks if file path have a directory.
     * If it does not exist, it creates the directory and creates file if it does not exist.
     * @throws DukeException if error creating file
     */
    public void createFile() throws DukeException {
        if(Files.notExists(filePath)){
            createDirectory();
            try {
                Files.createFile(filePath);
                System.out.println("new file created: " + filePath);
            } catch (IOException e) {
                throw new DukeException("Error creating file");
            }
        }
        assert Files.exists(filePath) : "File should exist after createFile is called";
    }

    /**
     * Writes tasks to file.
     * @param tasks ArrayList of tasks to be written to file
     * @throws DukeException Error writing to file
     */
    public void writeFile(ArrayList<Task> tasks) throws DukeException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toString()))) {
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
    }

    /**
     * Loads tasks from file to use in Nameless class
     * @return ArrayList of tasks to be
     * @throws DukeException Error reading file
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isDone;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            while ((line = br.readLine()) != null) {
                isDone = line.charAt(4) == '1';
                tasks.add(stringToTask(line));
                tasks.get(tasks.size() - 1).setDone(isDone);
            };
        } catch (IOException e) {
            throw new DukeException("Error reading file");
        }
        return tasks;
    }

    /**
     * Converts string to Task object.
     * @param line String to be converted to Task object
     * @return Task object
     */
    private Task stringToTask(String line) {
        String[] words = line.split(" \\| ");
        if(words[0].equals("T")){
            return new Todo(words[2]);
        } else if(words[0].equals("D")){
            return new Deadline(words[2], LocalDateTime.parse(words[3], DISPLAY_FORMAT));
        } else if(words[0].equals("E")){
            return new Event(words[2], LocalDateTime.parse(words[3], DISPLAY_FORMAT),
                    LocalDateTime.parse(words[4], DISPLAY_FORMAT));
        } else {
            return null;
        }
    }
}
