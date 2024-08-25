import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
            File folder = new File(folderPath);
            folder.mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("I/O error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String text) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("I/O error occurred.");
            e.printStackTrace();
        }
    }
}
