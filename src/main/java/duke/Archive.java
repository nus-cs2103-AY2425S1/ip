package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents the persistent archive storage for tasks.
 */
public class Archive {
    /**
     * The path of the file used for persistent storage.
     */
    private String path;

    /**
     * Constructor for an archive.
     *
     * @param path The path of the file used for persistent storage.
     */
    public Archive(String path) {
        this.path = path;
    }

    /**
     * Creates the archive file if it does not exist.
     *
     * @throws BobException If an IO error occurs.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() throws BobException {
        try {
            File f = new File(this.path);
            f.createNewFile();
        } catch (IOException e) {
            throw new BobException("Oh no! Something went wrong while creating your archive.");
        }
    }

    /**
     * Archive a list of tasks.
     *
     * @param tasks The list of tasks to archive.
     * @throws BobException If an IO error occurs.
     */
    public void archive(ArrayList<Task> tasks) throws BobException {
        assert tasks != null;

        try {
            FileWriter fw = new FileWriter(this.path, true);
            for (Task task : tasks) {
                fw.write(task.toString() + System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("Oh no! An IO error has occured.");
        }
    }

    /**
     * Retrieve the tasks from the archive.
     *
     * @return The list of tasks archived.
     * @throws BobException If an IO error occurs.
     */
    public ArrayList<Task> retrieve() throws BobException {
        try {
            File f = new File(this.path);

            ArrayList<Task> tasks = new ArrayList<>();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = Parser.parseStorage(line);
                tasks.add(task);
            }

            this.clear();

            return tasks;
        } catch (IOException e) {
            throw new BobException("Oh no! Something went wrong when reading from your archive.");
        }
    }

    /**
     * Clear the archive.
     *
     * @throws BobException If an IO error occurs.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void clear() throws BobException {
        try {
            File f = new File(this.path);
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            throw new BobException("Oh no! Something went wrong when clearing your archive.");
        }
    }
}
