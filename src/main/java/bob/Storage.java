package bob;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from a file for the Bob chatbot.
 * The Storage class ensures that tasks are persistently stored and retrieved when needed.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object with the specified file path where tasks are stored.
     *
     * @param filePath The file path where the tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * The method reads from the file, parses the stored tasks, and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws ChatBotException If an error occurs during reading the file or parsing the tasks.
     */
    public ArrayList<Task> load() throws ChatBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String directoryPath = "./data";
            boolean directoryFileExists = Files.exists(Paths.get(directoryPath));
            boolean directoryExists = Files.isDirectory(Paths.get(directoryPath));
            boolean fileExists = Files.exists(Paths.get(filePath));
            boolean isRegularFile = Files.isRegularFile(Paths.get(filePath));

            if (directoryFileExists
                    && directoryExists
                    && fileExists
                    && isRegularFile) {
                Scanner s = new Scanner(Paths.get(filePath));
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    tasks.add(Parser.parseTaskFromFile(taskString));
                }
            }
        } catch (IOException e) {
            throw new ChatBotException("Read failed. Something went wrong: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file specified by the file path.
     * This method overwrites the file with the current tasks, ensuring persistence.
     *
     * @param tasks An ArrayList of Task objects to be saved to the file.
     * @throws ChatBotException If an error occurs during writing the tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws ChatBotException {
        try {
            String directoryPath = "./data";
            boolean directoryFileExists = Files.exists(Paths.get(directoryPath));
            boolean directoryExists = Files.isDirectory(Paths.get(directoryPath));
            boolean fileExists = Files.exists(Paths.get(filePath));
            boolean isRegularFile = Files.isRegularFile(Paths.get(filePath));

            if (!directoryFileExists
                    || !directoryExists) {
                Files.createDirectory(Paths.get(directoryPath));
            }

            if (!fileExists
                    || !isRegularFile) {
                Files.createFile(Paths.get(filePath));
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.formatToSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new ChatBotException("Save failed. Something went wrong: " + e.getMessage());
        }
    }
}
