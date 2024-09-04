import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Storage {
    private final File filePath;
    public Storage(String filePath) {
        this.filePath = new File(filePath);
        createFile();
    }
    public TaskList load() {
        return new TaskList();
    }

    private void createFile() {
        String temp = this.filePath.toPath().toString();
        int lastIndex = temp.lastIndexOf("\\");
        try {
            if (lastIndex != -1) {
                String firstPart = temp.substring(0, lastIndex);
                new File(firstPart).mkdirs();
                filePath.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with data file");
        }
    }

    public void save(String data) {
        try {
            FileWriter fw = new FileWriter(this.filePath.toPath().toAbsolutePath().toString());
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
