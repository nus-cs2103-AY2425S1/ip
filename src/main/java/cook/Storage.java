package cook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Storage class to store file locally.
 */
public class Storage {
    // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
    private File file;

    /**
     * Constructs Storage object.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Reads from the local file.
     *
     * @return List of Task objects.
     * @throws IOException If file cannot be read from.
     * @throws ClassNotFoundException If serialized class cannot be found in file.
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
     * @param tasks List of Task objects.
     * @throws IOException If file cannot be written to.
     */
    public void writeFile(TaskList tasks) throws IOException {
        this.file.getParentFile().mkdirs();
        this.file.createNewFile();
        // Solution below adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.file));
        out.writeObject(tasks);
        out.close();
    }
}
