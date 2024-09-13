package mel.utils;

import mel.exceptions.MelException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class that handles a
 * hard disk save file for TaskList.
 */
public class Storage {
    /**
     * Save data directory path.
     */
    private final File dirData = new File("data");
    /**
     * Task list save file path.
     */
    private final File pathTask = new File(dirData, "tasks.txt");

    /**
     * Updates save file of task list, creates
     * data directory and save file if necessary.
     * @param taskList array representing list of tasks.
     * @throws IOException if I/O error occurs when writing save file.
     * @throws MelException if save file cannot be accessed.
     */
    public void updateTasks(String[] taskList) throws IOException, MelException {
        if (!dirData.exists()) {
            dirData.mkdir();
        }
        try {
            pathTask.canRead();
            pathTask.canWrite();
        } catch (SecurityException e) {
            throw new MelException("Mel is stunned!\n"
                    + "Mel couldn't access your file");
        }
        pathTask.delete();
        pathTask.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathTask));
        for (String str : taskList) {
            writer.write(str + "\n");
        }
        writer.flush();
        writer.close();
    }

}
