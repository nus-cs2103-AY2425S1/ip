package drbrown.utils;

import static java.lang.Boolean.parseBoolean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import drbrown.task.Deadline;
import drbrown.task.Event;
import drbrown.task.Task;
import drbrown.task.Todo;

/**
 * Handles the loading and saving of tasks to a file for the DrBrown application.
 * The tasks are stored in a specified file and are retrieved or updated as needed.
 */
public class Storage {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String filePath;
    /**
     * Constructs a Storage object with the specified file path for storing tasks.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as a TaskList.
     * If the file does not exist, it creates a new file.
     *
     * @return A TaskList containing all the tasks loaded from the file.
     * @throws DrBrownException If the file is corrupted or if an I/O error occurs.
     */
    public TaskList load() throws DrBrownException {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            assert file.exists() : "file doesn't exist";
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] sentenceSplit = scanner.nextLine().split(" \\| ");
                switch (sentenceSplit[0]) {
                case "T":
                    taskList.add(new Todo(parseBoolean(sentenceSplit[1]), sentenceSplit[2],
                            Task.Priority.valueOf(sentenceSplit[3])));
                    break;
                case "D":
                    taskList.add(new Deadline(parseBoolean(sentenceSplit[1]), sentenceSplit[2],
                            LocalDateTime.parse(sentenceSplit[4], DATE_TIME_FORMATTER),
                            Task.Priority.valueOf(sentenceSplit[3])));
                    break;
                case "E":
                    taskList.add(new Event(parseBoolean(sentenceSplit[1]), sentenceSplit[2],
                            LocalDateTime.parse(sentenceSplit[4], DATE_TIME_FORMATTER),
                            LocalDateTime.parse(sentenceSplit[5], DATE_TIME_FORMATTER),
                            Task.Priority.valueOf(sentenceSplit[3])));
                    break;
                default:
                    throw new DrBrownException("The file provided might be corrupted since it does not "
                            + "follow the specified format.");
                }
            }
        } catch (IOException e) {
            throw new DrBrownException("Oops! It seems like this is your first time. "
                    + "No worries, I've created a brand new file to get you started.");
        }
        return taskList;
    }

    /**
     * Updates the file with the current list of tasks.
     * If the file does not exist, it creates a new file.
     *
     * @param tasks The TaskList containing the current tasks to be saved to the file.
     */
    public void update(TaskList tasks) {
        assert tasks != null : "tasks should not be null";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            assert (file.exists()) : "File doesn't exist";
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getList()) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Seems like you messed up the file path somehow!");
        }
    }
}
