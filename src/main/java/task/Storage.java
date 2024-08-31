package task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidStorageFileException;
import utility.Parser;

/**
 * The Storage class deals with loading and saving tasks from the file
 */
public class Storage {
    private final Path path = Paths.get("src", "main", "data", "level-hundred.txt");

    // Creates file if it does not exist
    private File createFileIfNotExists() throws InvalidStorageFileException {
        try {
            // Create parent directory
            Path parent = this.path.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectory(parent);
            }

            // Create file 
            if (!Files.exists(this.path)) {
                Files.createFile(this.path);
            }

            return this.path.toFile();
        } catch (IOException e) {
            System.out.println(e);
            throw new InvalidStorageFileException();
        }
    }

    /**
     * Loads the tasks from the file
     *
     * @return ArrayList<Task> of tasks from the file
     * @throws InvalidStorageFileException
     */
    public ArrayList<Task> load() throws InvalidStorageFileException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            this.createFileIfNotExists();
            List<String> lines = Files.readAllLines(this.path);
            for (String line : lines) {
                tasks.add(Parser.parseStorageFileLine(line));
            }

            return tasks;
        } catch (IOException | InvalidStorageFileException e) {
            throw new InvalidStorageFileException();
        }
    }

    /**
     * Updates the tasks from an ArrayList of tasks
     *
     * @param taskList ArrayList<Task> of tasks
     * @throws InvalidStorageFileException
     */
    public void update(ArrayList<Task> taskList) throws InvalidStorageFileException {
        try {
            // Create file content
            StringBuilder s = new StringBuilder();
            for (Task t : taskList) {
                s.append(t + System.lineSeparator());
            }
            String fileContent = s.toString();

            // Write file content into storage file
            File f = this.createFileIfNotExists();
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (IOException e) {
            throw new InvalidStorageFileException();
        }
    }
}