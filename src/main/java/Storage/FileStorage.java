package Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorage {
    private final String filePath;
    public FileStorage(String dirPath) {
        filePath = dirPath + "/" + "avo.txt";
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String data) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
