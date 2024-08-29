package FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class StorageFileWriter {
    FileWriter overWriteFile;
    FileWriter appendToFile;

    public StorageFileWriter(Path filePath) {
        try {
            overWriteFile = new FileWriter(String.valueOf(filePath), true);
            appendToFile = new FileWriter(String.valueOf(filePath));
        } catch (IOException fileWriteIOException) {
            throw new RuntimeException(fileWriteIOException);
        }
    }

    public void end() {
        try {
            overWriteFile.close();
            appendToFile.close();
        } catch (IOException closeException) {
            throw new RuntimeException(closeException);
        }
    }
}
