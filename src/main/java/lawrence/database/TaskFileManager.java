package lawrence.database;

import lawrence.parser.InputSource;
import lawrence.parser.TaskParser;
import lawrence.task.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interfaces with a text file to read and write tasks input by the user.
 * <p>
 * The contents in the file persist even after the program terminates.
 * </p>
 */
public class TaskFileManager {
    private final File FILE;

    /**
     * Default constructor. The {@link Path} instance provided can be a
     * relative or absolute path.
     *
     * @param path the path to the text file used to store tasks
     */
    public TaskFileManager(Path path) {
        FILE = path.toAbsolutePath()
                .normalize()
                .toFile();
    }

    /**
     * Reads from the file specified in the constructor, then converts the
     * stored task strings into an array of {@link Task} objects using
     * the {@link TaskParser} class.
     *
     * @return an array of {@link Task} objects
     * @throws IOException if reading from the file is unsuccessful
     */
    public Task[] readTasksFromFile() throws IOException {
        if (!FILE.exists()) {
            return new Task[0];
        }

        Scanner sc = new Scanner(new FileReader(FILE));
        sc.useDelimiter("\n");

        ArrayList<Task> tasks = new ArrayList<>();
        while(sc.hasNext()) {
            try {
                String taskString = sc.nextLine();
                tasks.add(TaskParser.createTask(taskString, InputSource.FILE));
            } catch (IllegalArgumentException | DateTimeParseException e) {
                // Task contains faulty information in file, just skip
            }
        }

        sc.close();

        // convert to task objects
        return tasks.toArray(new Task[0]);
    }

    /**
     * Saves the specified tasks into the text file.
     * <p>
     * This operation will overwrite the contents previously in the text file.
     * </p>
     *
     * @param tasks the tasks to save to the text file
     * @throws IOException if writing to the file is unsuccessful
     */
    public void saveTasksToFile(Task[] tasks) throws IOException {
        createFileIfNotExists();

        FileWriter writer = new FileWriter(FILE);
        String result = convertToSaveFormat(tasks);
        writer.write(result);
        writer.close();
    }

    /**
     * Converts and array of {@link Task} objects into a string.
     *
     * @param tasks the array of {@link Task} objects
     * @return a string representing the array of {@link Task} objects
     */
    private String convertToSaveFormat(Task[] tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.toSaveFormat());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Creates a file to store tasks if no such file already exists.
     * <p>
     * The relevant parent directories are also created if needed.
     * </p>
     *
     * @throws IOException if the creation of the file was unsuccessful
     */
    private void createFileIfNotExists() throws IOException {
        if (FILE.exists()) {
            return;
        }

        boolean parentDirectoryCreated = FILE.getParentFile().mkdirs();
        boolean fileCreated = FILE.createNewFile();

        if (!parentDirectoryCreated) {
            throw new IOException("An error occurred when trying to initialise the file directory " + FILE.getPath());
        }

        if (!fileCreated) {
            throw new IOException("An error occurred when trying to initialise " + FILE.getName());
        }
    }
}
