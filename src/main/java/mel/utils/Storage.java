package mel.utils;

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
    private final File DIR_DATA = new File("data");
    /**
     * Task list save file path.
     */
    private final File PATH_TASKS = new File(DIR_DATA, "tasks.txt");

    /**
     * Updates save file of task list, creates
     * data directory and save file if necessary.
     * @param taskList array representing list of tasks.
     * @throws IOException if I/O error occurs when writing save file.
     */
    public void updateTasks(String[] taskList) throws IOException {
        if (!DIR_DATA.exists()) {
            DIR_DATA.mkdir();
        }
        PATH_TASKS.delete();
        PATH_TASKS.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TASKS));
        for (String str : taskList) {
            writer.write(str + "\n");
        }
        writer.flush();
        writer.close();
    }

}
