package sumode.util;

import sumode.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * In charge of saving/loading to the file path.
 */
public class Storage{

    private final String filePath;
    private final File f;
    private final Ui ui;

    /**
     * Constructor for Storage
     *
     * @param filePath File path to save data for tasks in lists.
     * @param ui UI for all outputs.
     */
    public Storage (String filePath, Ui ui) throws IOException {
        this.filePath = filePath;
        this.ui = ui;
        this.f = new File(filePath);

        // Ensure parent directories exist
        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                System.out.println("ZZZ");// Attempt to create the directory
                throw new IOException("Failed to create required directories for path: " + filePath);
            }
        }

        // Ensure the file itself exists
        if (!f.exists()) {
            if(!f.createNewFile()) {
                throw new IOException();
            }
        }
    }

    /**
     * Save the tasks onto the file path provided on initialisation.
     * <p>
     * Takes o(number of tasks) time as each task is re-analysed and put into the file.
     * @param tasks List of tasks to be save in file path.
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath, false);
            for (Task task : tasks) {
                fw.write(task.savedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            this.ui.latestSaveError();
        }
    }

    /**
     * Returns a String array based on what is saved in the file path during initialisation.
     * <p>
     * Each line in the file path is parsed into different slot in the array
     * The array size will be equivalent to number of lines in the file.
     * @return a String array of what each line is in the file.
     */
    public String[] load() throws FileNotFoundException {
            Scanner s = new Scanner(f);
            s.useDelimiter("\\A");
            return s.hasNext()
                    ? s.next().split("\n")
                    : new String[0];
    }
}
