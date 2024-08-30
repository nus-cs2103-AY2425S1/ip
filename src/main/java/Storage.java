import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path saveDataPath = Paths.get(Constants.FILE_PATH);
    public List<String> loadData() throws IOException, NoSaveDataFoundException {
        if (Files.notExists(saveDataPath)) {
            throw new NoSaveDataFoundException();
        }
        return Files.readAllLines(saveDataPath);
    }

    public void createSaveFile() throws IOException {
        Files.createFile(saveDataPath);
    }

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
                    + "at Luke.txt. try launching me again to continue.");
            System.exit(0);
        }
    }
}
