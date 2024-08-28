import java.io.File;

public class Storage {
    private File taskStore;
    Storage(String filePath) {
        this.taskStore = new File(filePath);
    }
}
