package cook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
     * Creates file locally if it does not exist.
     *
     * @return Success of file creation.
     * @throws IOException If file existence cannot be checked.
     */
    public boolean createFile() throws IOException {
        this.file.getParentFile().mkdir();
        return this.file.createNewFile();
    }

    /**
     * Writes to the local file.
     *
     * @throws IOException If file cannot be written to.
     */
    public void writeFile(String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }
}
