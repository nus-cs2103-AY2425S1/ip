import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Data {
    private static final String DATA_PATH = "data/data.txt";
    public static void init() {
        Path filePath = Paths.get(Data.DATA_PATH);
        if (Files.exists(filePath)) {
            return;
        }
        File f = new File(DATA_PATH);
    }
}
