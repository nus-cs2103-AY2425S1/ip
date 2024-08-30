import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

public class Storage {
    private final String filePath;
    private boolean working;

    Storage(String filePath) {
        this.filePath = filePath;
        this.working = true;
    }

    public void writeToFile(String text) {
        if (this.working) {
            try {
                FileWriter fw = new FileWriter(this.filePath);
                fw.write(text);
                fw.close();
            } catch (IOException e) {
                System.out.println("Error: Changes failed to save to hard drive");
            }
        }
    }

    List<String> load() throws LoafyException {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            new File(this.filePath).createNewFile();
            Path path = Paths.get(this.filePath);
            return Files.readAllLines(path);
        } catch (SecurityException | IOException | NullPointerException e) {
            this.working = false;
            throw LoafyException.ofLoadingError();
        }
    }
}
