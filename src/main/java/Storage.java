import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
    File file;

    public Storage(File file) {
        this.file = file;
    }

    public boolean createFile() throws IOException {
        this.file.getParentFile().mkdir();
        return this.file.createNewFile();
    }

    public void writeFile(String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }
}
