import java.io.File;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfNotExists() {
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
