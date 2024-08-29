package rex.util;

import rex.task.Task;
import rex.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Storage {
    private static final String FILE_PATH = "./data/rex.txt";
    private static final String TEMP_PATH = "./data/tmp.txt";

    public void loadFile(TaskList list) throws IOException {
        // Create new file and directory for filepath
        File file = new File(FILE_PATH);
        File dir = file.getParentFile();

        createDirectory(dir);
        createFile(file);

        // Parse file data into tasks.TaskList
        Parser.parseFile(list, file);
    }

    public void updateFile(TaskList list) throws IOException {
        File file = new File(FILE_PATH);
        File temp = new File(TEMP_PATH);

        // Create temp file to copy taskList from
        Files.deleteIfExists(Paths.get(TEMP_PATH));
        createFile(temp);

        FileWriter writer = new FileWriter(temp, true);

        // Create new file with updated list
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.getTask(i);
            writer.write(task.formatted() + System.lineSeparator());
        }

        // Close writer
        writer.close();

        // Copy to save file
        Files.copy(Paths.get(TEMP_PATH), Paths.get(FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(TEMP_PATH));
    }

    private void createDirectory(File dir) {
        // Create new directory if it does not exist
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
    }

    private void createFile(File f) throws IOException {
        // Create new file if does not exist
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}
