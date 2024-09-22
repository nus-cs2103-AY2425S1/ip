package babygronk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

import task.Task;

/**
 * Creates file and reads and stores data in it.
 */
public class Storage {
    private final String fileName;

    public Storage(String file) {
        fileName = file;
    }

    private boolean fileCreated() {
        File dataFile = new File(fileName);
        if (!dataFile.exists()) {
            boolean directoryCreated = dataFile.getParentFile().mkdirs();
            if (!directoryCreated) {
                System.out.println("Directory was not created");
                return (false);
            }
            boolean isCreated = false;
            try {
                isCreated = dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
            if (!isCreated) {
                System.out.println("File was not created");
                return (false);
            }
        }
        return (true);
    }

    /**
     * Creates the file if it doesn't exist.
     * Reads from the file if it exists.
     *
     * @return Stream of Strings for each line in the file or null if empty
     */
    public Stream<String> init() {
        if (fileCreated()) {
            FileReader fileReader;
            try {
                fileReader = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return (null);
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return (bufferedReader.lines());
        }
        return (null);
    }

    /**
     * Saves tasks into the storage file using bufferedWriter.
     *
     * @param tasks List of tasks.
     */
    public void saveData(Stream<Task> tasks) {
        if (fileCreated()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                tasks.forEach(task -> {
                    try {
                        bufferedWriter.write(task.toStorageFormat());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        System.out.println("Failed to save task: " + task);
                    }
                });
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
    }
}
