package beeboo.components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import beeboo.exception.NoFileException;
import beeboo.task.Tasks;

/**
 * The Storage class handles the reading and writing of task data to and from a file.
 * It provides methods for loading tasks from a file and saving tasks to a file.
 */
public class Storage {

    /**
     * The path to the file used for storing task data.
     */
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath the path to the file where task data is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file specified by the file path.
     * It reads the file line by line, parses the task data, and creates Task objects.
     *
     * @return an ArrayList of Tasks objects loaded from the file
     * @throws NoFileException if the file does not exist
     */
    public ArrayList<Tasks> load() throws NoFileException {
        File file = new File(filePath);
        ArrayList<Tasks> list = new ArrayList<>();
        if (!file.exists()) {
            throw new NoFileException("");
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] splitted = task.split("\\|");
                Tasks newTask = TaskCreator.createTask(splitted);
                if (newTask != null) {
                    list.add(newTask);
                }
            }
        } catch (IOException e) {
            System.out.println("Error has occurred while reading the file");
        }
        return list;
    }

    /**
     * Saves the specified list of tasks to the file specified by the file path.
     * Each task is saved in a format suitable for storage.
     *
     * @param list the list of Tasks objects to be saved
     */
    public void saveItem(TaskList list) {
        try (FileWriter writer = new FileWriter("./data/beeboo.txt")) {
            for (int i = 0; i < list.getSize(); i++) {
                Tasks task = list.get(i);
                writer.write(task.saveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
    }

    /**
     * Creates the directory for storing task data if it does not already exist.
     * This method ensures that the directory structure is in place for saving task data.
     */
    protected static void createFile() {
        Path path = Paths.get("./data");
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Unable to create directory");
            }
        }
    }
}
