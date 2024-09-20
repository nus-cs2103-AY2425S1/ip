package diomon;

import diomon.parser.Parser;
import diomon.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for handling the saving and loading
 * of task data to and from a file. It ensures that task data is persisted
 * between program runs.
 */
public class Storage {
    private final File filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * If the file does not exist, it creates the necessary directories and file.
     *
     * @param filePath The path of the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = new File(filePath);
        createFile();
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     * Each line in the file is expected to represent a task, which is parsed using {@link Parser#loadTask(String)}.
     *
     * @return An array of {@link Task} objects loaded from the file.
     * If the file is not found or empty, an empty array is returned.
     */
    public Task[] load() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner scanner = new Scanner(this.filePath);
            while (scanner.hasNext()) {
                String line =scanner.nextLine();
                if (line.isEmpty()){
                    continue;
                }
                tasks.add(Parser.loadTask(line));
            }
            return tasks.toArray(new Task[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new Task[0];
        }

    }

    /**
     * Creates the necessary directories and file if they do not already exist.
     * This method is called during the initialization of {@code Storage} to ensure
     * the storage file is ready for use.
     */
    private void createFile() {
        String temp = this.filePath.toPath().toString();
        int lastIndex = temp.lastIndexOf("\\");
        try {
            if (lastIndex != -1) {
                String firstPart = temp.substring(0, lastIndex);
                boolean createDir = new File(firstPart).mkdirs();
                boolean createFile = filePath.createNewFile();
                boolean success = createDir && createFile;
                if (!success) {
                    throw new RuntimeException("Data file failed to be created");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong with data file, might be security issue");
        }
    }

    /**
     * Saves the given task data to the file specified by {@code filePath}.
     * The entire contents of the file are replaced by the given string.
     *
     * @param data The task data to save to the file, formatted as a single string.
     */
    public void save(String data) {
        try {
            FileWriter fw = new FileWriter(this.filePath.toPath().toAbsolutePath().toString());
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
