import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles reading and writing TaskLog to file
 */
public class Storage {
    private static final Path STORAGE_PATH = Paths.get( "taskStorage", "taskLog.tsv");

    Storage() {
        File file = new File(STORAGE_PATH.toString());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.isFile()) {
            createFile(file);
        }
    }

    public TaskLog getTasks() {
        return new TaskLog();
    }

    public void storeTasks(TaskLog taskLog) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH.toString());
            for (Task task : taskLog.getLog()) {
                fw.write(task.toTsv());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            // don't create it
            e.printStackTrace();
        }
    }


}
