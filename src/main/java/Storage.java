import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return taskList; // Start with an empty list if file does not exist
        }

        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                taskList.add(task);
            }
            reader.close();
            return taskList;
        } catch (IOException e) {
            Ui.showLoadingError(e);
        } catch (AlfredException e) {
            handleCorruptedSave(e);
        }
        return new ArrayList<>();
    }

    public void saveTasks(List<Task> taskList) {
        try {
            Files.createDirectories(filePath.getParent()); // Ensure the folder exists
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            Ui.showSavingError(e);
        }
    }

    public void handleCorruptedSave(AlfredException err) {
        Ui.showCorruptedSaveError(err);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            Ui.showDeletionError(e);
        }
    }
}
