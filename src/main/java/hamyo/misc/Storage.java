package hamyo.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import hamyo.tasks.Deadline;
import hamyo.tasks.Event;
import hamyo.tasks.Task;
import hamyo.tasks.TaskList;
import hamyo.tasks.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 * The path of the file to load from or save into is "./savedTasks.txt" by
 * default, specified in the Hamyo class constructor.
 *
 * @author Han Yu
 */
public class Storage {

    private final String path;
    private File file;

    /**
     * Constructor for a Storage instance. If the file in the specified path does
     * not exist, a new file with the specified path will be created.
     *
     * @param path path of the file to locate. "./savedTasks.txt" by default.
     */
    public Storage(String path) {
        this.path = path;
        try {
            File tempFile = new File("./savedTasks.txt");
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            this.file = tempFile;
        } catch (IOException e) {
            Ui.printException(e);
        }
    }

    /**
     * Load tasks from the file of the specified path into the list of tasks.
     *
     * @param tasks The list of the users' tasks.
     * @throws HamyoException If file is corrupted.
     */
    public void loadData(TaskList tasks) throws HamyoException {
        try {
            tasks.clear();
            Scanner scannedTasks = new Scanner(this.file);
            while (scannedTasks.hasNext()) {
                String[] task = scannedTasks.nextLine().split(" \\| ");
                Task tempTask;
                switch (task[0]) {
                case "T":
                    tempTask = new ToDo(task[2]);
                    break;
                case "D":
                    tempTask = new Deadline(task[2], task[3]);
                    break;
                case "E":
                    tempTask = new Event(task[2], task[3], task[4]);
                    break;
                default:
                    throw new HamyoException("Invalid case " + task[0] + ".");
                }
                switch (task[1]) {
                case "1":
                    tempTask.mark(false);
                    break;
                case "0":
                    break;
                default:
                    throw new HamyoException("Invalid boolean " + task[1] + ".");
                }
                tasks.add(tempTask);
            }
            scannedTasks.close();
        } catch (HamyoException e) {
            throw new HamyoException("Possible File Corruption. " + e.getMessage());
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }

    /**
     * Saves tasks from the list of tasks into the file of the specified path.
     *
     * @param tasks The list of the users' tasks.
     * @throws HamyoException If issues are encountered during file write.
     */
    public void saveData(TaskList tasks) throws HamyoException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            StringBuilder newData = new StringBuilder();
            for (Task task : tasks) {
                newData.append(task.toFileFormat()).append(System.lineSeparator());
            }
            fileWriter.write(newData.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }
}
