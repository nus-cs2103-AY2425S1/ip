package wiggly.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import wiggly.exception.WigglyException;
import wiggly.task.Task;
import wiggly.task.TaskList;

/**
 * A class representation of a file storage to load and save a list of tasks
 */
public class Storage {
    private final File file;

    /**
     * Creates a new {@code Storage} instance by creating a {@code File} instance with the given filePath
     * to load and save data
     *
     * @param filePath Path name of the file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file");
            }
        }
    }

    /**
     * Saves the given tasklist in {@code Storage} into {@code toFileFormat()} by overwriting the entire
     * stored file
     *
     * @param taskList The entire taskList to save overwrite into
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            String output = taskList.toFileFormat();
            fw.write(output);
            fw.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns an {@code ArrayList<Task>} by reading and parsing the contents of the stored file
     *
     * @return the tasks stored in the file
     * @throws WigglyException       if the file is in an incorrect format
     * @throws FileNotFoundException if the file cannot be found
     */
    public ArrayList<Task> load() throws WigglyException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(file);
        int count = 0;

        while (s.hasNextLine()) {
            String line = s.nextLine();

            try {
                tasks.add(Task.createFromData(line));
            } catch (IllegalArgumentException e) {
                throw new WigglyException(e.getMessage() + " at line " + count);
            } finally {
                count++;
            }
        }

        return tasks;
    }

}
