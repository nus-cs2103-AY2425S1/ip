package luke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import luke.env.Constants;
import luke.task.Task;
import luke.task.TaskList;

public class Storage {
    private final Path saveDataPath = Paths.get(Constants.FILE_PATH);

    /**
     * Checks if save data is present. If present, the contents of the file will be returned as a string list.
     * @return a list of strings where each string is a line from the save file
     * @throws IOException if any I/O errors occur
     * @throws NoSaveDataFoundException if no save data is present
     */
    public List<String> loadData() throws IOException, NoSaveDataFoundException {
        if (Files.notExists(saveDataPath)) {
            throw new NoSaveDataFoundException();
        }
        return Files.readAllLines(saveDataPath);
    }

    /**
     * Creates a save file.
     * This function will only be called if the user indicates that they want a save file to be created.
     * @throws IOException if any I/O errors occur
     */
    public void createSaveFile() throws IOException {
        Files.createFile(saveDataPath);
    }

    /**
     * Writes the task list data into the save file.
     * @param taskList  a list of Task objects
     */
    public void saveData(TaskList taskList) {
        try {
            // clear file
            FileWriter fw = new FileWriter(saveDataPath.toString(), false);
            // add all tasks in the current list into the file
            for (Task task : taskList.getTaskList()) {
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
