package cook;

import java.io.*;

/**
 * Storage class to store file locally.
 */
public class Storage {
    // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
    private File file;

    /**
     * Constructor for Storage class.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Reads from the local file.
     *
     * @throws IOException If file cannot be read from.
     */
    public TaskList readFile() throws IOException, ClassNotFoundException {
        // Solution below adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.file));
        TaskList tasks = (TaskList) in.readObject();
        in.close();
        return tasks;
    }

    /**
     * Writes to the local file.
     *
     * @throws IOException If file cannot be written to.
     */
    public void writeFile(TaskList tasks) throws IOException {
        // Solution below adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.file));
        out.writeObject(tasks);
        out.close();
    }
}
