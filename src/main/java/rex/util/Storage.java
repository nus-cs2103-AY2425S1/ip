package util;

import tasks.Task;
import tasks.TaskList;
import util.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Storage {
    private String filepath = "./data/rex.txt";
    private String temppath = "./data/tmp.txt";

    public void loadFile(TaskList list) throws IOException {
        // Create new file and directory for filepath
        File file = new File(filepath);
        File dir = file.getParentFile();

        createDirectory(dir);
        createFile(file);

        // Parse file data into tasks.TaskList
        Parser.parseFile(list, file);
    }

    public void updateFile(TaskList list) throws IOException {
        File file = new File(filepath);
        File temp = new File(temppath);

        // Create temp file to copy taskList from
        Files.deleteIfExists(Paths.get(temppath));
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
        Files.copy(Paths.get(temppath), Paths.get(filepath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(temppath));
    }

    private void createDirectory(File f) {
        // Create new directory if it does not exist
        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }

    private void createFile(File f) throws IOException {
        // Create new file if does not exist
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}
