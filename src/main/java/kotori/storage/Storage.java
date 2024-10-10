package kotori.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import kotori.tasklist.Task;
import kotori.tasklist.TaskList;
import kotori.ui.Ui;

/**
 * This class represent the Storage of the chatbot.
 * */

public class Storage {
    private File file;
    private boolean isCorrupted = false;
    private boolean hasFile = true;
    private boolean isSuccess = true;

    /**
     * Create a storage
     * */
    public Storage(String directoryName, String fileName) {

        this.file = makeFile(directoryName, fileName);
    }

    /**
     * Loads from the memory file and create a TaskList Object.
     *
     * @return The TaskList loaded from the hard disk file.
     * */
    public TaskList load() {
        try {
            return readList();
        } catch (FileNotFoundException e) {
            hasFile = false;
            return new TaskList();
        } catch (CorruptedFileException e) {
            isCorrupted = true;
            return new TaskList();
        }
    }
    /**
     * Reads the list from a file.
     * @return The tasklist obtained
     * @throws CorruptedFileException if the file is corrupted.
     * @throws FileNotFoundException if the file is not found.
     * */
    private TaskList readList() throws CorruptedFileException, FileNotFoundException {
        try {
            TaskList result = new TaskList();
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                final String separator = " \\| ";
                String[] elements = input.split(separator);
                boolean isInvalidLength = elements.length < 1;
                if (isInvalidLength) {
                    throw new CorruptedFileException();
                } else {
                    result.add(Task.read(elements));
                }
            }
            return result;
        } catch (DateTimeParseException e) {
            throw new CorruptedFileException();
        }
    }
    /**
     * Checks if the file is corrupted.
     * @return if the file is corrupted
     * */
    public boolean isCorrupted() {
        return isCorrupted;
    }
    /**
     * Checks if the file exits.
     * @return If the file exits
     * */
    public boolean hasFile() {
        return hasFile;
    }

    /**
     * Update the hard disk file with the current TaskList.
     *
     * @param tasks the current TaskList.
     * */
    public void updateFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(file);
            String content = "";
            for (int i = 0; i < tasks.size(); i++) {
                content += tasks.get(i).getStorageMessage() + "\n";
            }
            writer.write(content);
            writer.close();
        } catch (FileNotFoundException e) {
            Ui.printMessages("Sorry~ I can not find the storage file",
                "Please ensure there is a file with path data/Kotori.Kotori.txt");
        } catch (IOException e) {
            Ui.printMessages(String.format("There is something wrong about: %s", e.getMessage()));
        }
    }

    /**
     * makes a file with specific directory name and file name
     *
     * @param directoryName name of directory.
     * @param fileName name of the file
     * @return the file produced
     * @throws Error if the file does not exist and can not create one.
     * */

    private File makeFile(String directoryName, String fileName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                this.isSuccess = false;
                throw new Error("A fatal error has occurs in creating the file");
            }
        }
        return file;
    }

    /**
     * Check the storage is done successfully
     * */
    public boolean isSuccess() {
        return isSuccess;
    }
}

