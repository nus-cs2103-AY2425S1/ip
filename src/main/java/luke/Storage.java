package luke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import luke.env.Constants;
import luke.task.Task;
import luke.task.TaskList;

/**
 * The {@code storage} class handles the loading and saving of task data from a file.
 * <p>
 * This class is responsible for:
 * <ul>
 *     <li>Checking if save data exists</li>
 *     <li>Creating a save file at saveDataPath if it doesn't exist</li>
 *     <li>Saving the current task list to the save file</li>
 *     <li>Loading task data from the save file into the application</li>
 * </ul>
 * The save data is stored in a file at a path specified by {@link Constants#FILE_PATH}.
 *
 * @see TaskList
 * @see Task
 * @see Ui
 */
public class Storage {
    private static final Path saveDataPath = Paths.get(Constants.FILE_PATH);

    /**
     * Checks if save data is present. If present, the contents of the file will be returned as a string list.
     * @return a list of strings where each string is a line from the save file
     * @throws IOException if any I/O errors occur
     */
    public static List<String> loadData() throws IOException {
        if (Files.notExists(saveDataPath)) {
            Files.createDirectories(saveDataPath.getParent());
            createSaveFile();
        }
        return Files.readAllLines(saveDataPath);
    }

    /**
     * Creates a save file to be stored at the location specified in saveDataPath.
     * @throws IOException If any I/O errors occur
     */
    public static void createSaveFile() throws IOException {
        Files.createFile(saveDataPath);
    }

    /**
     * Writes the task list data into the save file.
     * @param taskList A list of Task objects
     */
    public static void saveData(ArrayList<Task> taskList) {
        try {
            // clear file
            FileWriter fw = new FileWriter(saveDataPath.toString(), false);
            // add all tasks in the current list into the file
            for (Task task : taskList) {
                fw.write(task.taskInSaveData());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("woops! i ran into an issue saving your list. your last saved list can be found "
                    + "at luke.Luke.txt. try launching me again to continue.");
            System.exit(0);
        }
    }
}
