package carly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import carly.exception.CarlyException;

/**
 * Manages the storage of task data by handling file operations.
 * Ensures that the necessary directories and files are created
 * and provides methods to write task data to the specified file.
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        LoadFile();
    }

    /** Ensures that the necessary directories and file are created. */
    private void LoadFile() {
        File file = new File(this.filepath);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    /** Writes the provided text to the file specified by the filepath. */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        fw.write(textToAdd);
        fw.close();
    }

    /** Saves the formatted task list to the file. */
    public void savesFile(TaskList tasklist) throws IOException, CarlyException {
        String textToAdd = tasklist.getFormattedTaskList();
        this.writeToFile(textToAdd);
    }
}