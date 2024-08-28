import java.io.File;
import java.io.FileWriter;

public class History {

    public String filePath;
    public File saved;

    public History(String filePath) {
        this.filePath = filePath;
        this.saved = new File(filePath);
    }

    public void writeHistory(String toWrite) {

    }

}
