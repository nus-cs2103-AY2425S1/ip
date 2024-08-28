import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class FileWrite {
    public static void saveFileContents(String filePath, ArrayList<Task> tasks) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.getFormattedString() + "\n");
        }
        fw.close();
    }
}
