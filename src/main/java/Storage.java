import java.io.File;
import java.io.IOException;

public class Storage {
    public static final String DATA_PATH = "./data/julie.txt";
    public static File data = new File(DATA_PATH);
    public static void start() {
        try {
            File directory = new File(data.getParent());
            if (!directory.exists()) {
                directory.mkdirs();  // Create directory if it does not exist
            }
            if (!data.exists()) {
                data.createNewFile();  // Create file if it does not exist
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
        }
    }
}
