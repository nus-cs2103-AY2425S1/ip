package moimoi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import moimoi.exception.MoiMoiException;
import moimoi.exception.StorageCorruptedException;
import moimoi.exception.StorageIOException;
import moimoi.task.Deadline;
import moimoi.task.Event;
import moimoi.task.Task;
import moimoi.task.TaskEnum;
import moimoi.task.Todo;

/**
 * Represents the data file for program storage.
 */
public class Storage {

    private String path;

    /**
     * Constructs an encapsulation of a storage data file, with the specified path.
     *
     * @param path Path of the storage data file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads tasks from storage and returns them as a list.
     *
     * @return List of tasks from storage.
     * @throws MoiMoiException If storage is corrupted, or an I/O error occurs.
     */
    public ArrayList<Task> load() throws MoiMoiException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(new File(this.path));
            while (sc.hasNextLine()) {
                String[] taskInfo = sc.nextLine().split(" \\| ");
                tasks.add(this.createTask(taskInfo));
            }
        } catch (FileNotFoundException e) {
            this.createFile();
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks into the storage data file.
     *
     * @param tasks List of tasks to be saved.
     * @throws StorageIOException If an I/O error occurs.
     */
    public void save(TaskList tasks) throws StorageIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (int i = 1; i <= tasks.size(); i = i + 1) {
                Task task = tasks.get(i);
                fileWriter.write(task.stringStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

    /**
     * Returns a task corresponding to the specified information.
     *
     * @param taskInfo Task information.
     * @return Task corresponding to the specified information.
     */
    private Task createTask(String[] taskInfo) throws MoiMoiException {
        try {
            Task task;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (TaskEnum.valueOf(taskInfo[0])) {
            case T:
                task = new Todo(taskInfo[2]);
                break;
            case D:
                LocalDateTime by = LocalDateTime.parse(taskInfo[3], formatter);
                task = new Deadline(taskInfo[2], by);
                break;
            case E:
                LocalDateTime from = LocalDateTime.parse(taskInfo[3], formatter);
                LocalDateTime to = LocalDateTime.parse(taskInfo[4], formatter);
                task = new Event(taskInfo[2], from, to);
                break;
            default:
                throw new StorageCorruptedException();
            }

            String taskStatusIcon = taskInfo[1];
            if (taskStatusIcon.equals("X")) {
                task.mark();
            } else if (!taskStatusIcon.equals(" ")) {
                throw new StorageCorruptedException();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new StorageCorruptedException();
        }
    }

    /**
     * Creates a data file for storage.
     *
     * @throws StorageIOException If an I/O error occurs.
     */
    private void createFile() throws StorageIOException {
        try {
            File file = new File(this.path);
            File folder = new File(file.getParent());
            folder.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

}
