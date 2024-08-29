import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    Path dirPath;
    Path filePath;

    public Storage(Path filePath) {
        this.dirPath = filePath;
        this.filePath = dirPath.resolve("data.txt");
    }

    public void writeToFile(TaskList taskList) {
        try {
            if (Files.notExists(dirPath)) {
                Files.createDirectory(dirPath);
                Files.createFile(filePath);

            } else if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            Elseptions.saveFileError();
        }

        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(taskList.fileString());
            fw.close();

        } catch (IOException e) {
            Elseptions.saveFileError();
        }
    }
}
