package moimoi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import moimoi.util.exception.InvalidDateTimeRangeException;
import moimoi.util.exception.InvalidPeriodException;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.exception.StorageCorruptedException;
import moimoi.util.exception.StorageIoException;
import moimoi.util.task.Deadline;
import moimoi.util.task.Event;
import moimoi.util.task.Period;
import moimoi.util.task.Task;
import moimoi.util.task.TaskEnum;
import moimoi.util.task.Todo;

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
     * @throws StorageIoException If an I/O error occurs.
     */
    public void save(TaskList tasks) throws StorageIoException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (int i = 1; i <= tasks.getSize(); i = i + 1) {
                Task task = tasks.get(i);
                fileWriter.write(task.stringStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageIoException();
        }
    }

    /**
     * Returns a task corresponding to the specified information.
     *
     * @param taskInfo Task information.
     * @return Task corresponding to the specified information.
     * @throws StorageCorruptedException If any part of the task information is missing or invalid,
     *                                   i.e., the storage is corrupted.
     */
    private Task createTask(String[] taskInfo) throws StorageCorruptedException {
        try {
            Task task;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (TaskEnum.valueOf(taskInfo[0])) {
            case T:
                task = new Todo(taskInfo[2]);
                break;
            case D:
                LocalDateTime deadline = LocalDateTime.parse(taskInfo[3], formatter);
                task = new Deadline(taskInfo[2], deadline);
                break;
            case E:
                LocalDateTime start = LocalDateTime.parse(taskInfo[3], formatter);
                LocalDateTime end = LocalDateTime.parse(taskInfo[4], formatter);
                task = new Event(taskInfo[2], start, end);
                break;
            case P:
                double period = Double.parseDouble(taskInfo[3]);
                task = new Period(taskInfo[2], period);
                break;
            default:
                throw new StorageCorruptedException();
            }

            this.setTaskStatus(task, taskInfo[1]);

            return task;
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException
                 | InvalidDateTimeRangeException | InvalidPeriodException e) {
            throw new StorageCorruptedException();
        }
    }

    /**
     * Creates a data file for storage.
     *
     * @throws StorageIoException If an I/O error occurs.
     */
    private void createFile() throws StorageIoException {
        try {
            File file = new File(this.path);
            File folder = new File(file.getParent());
            folder.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageIoException();
        }
    }

    /**
     * Sets status of the specified task, according to its status icon retrieved from storage.
     *
     * @param task Task whose status is to be set.
     * @param taskStatusIcon Status icon of the task.
     * @throws StorageCorruptedException If the task's status icon is invalid, i.e., the storage is corrupted.
     */
    private void setTaskStatus(Task task, String taskStatusIcon) throws StorageCorruptedException {
        if (taskStatusIcon.equals("X")) {
            task.mark();
        } else if (taskStatusIcon.equals(" ")) {
            task.unmark();
        } else {
            throw new StorageCorruptedException();
        }
    }

}
