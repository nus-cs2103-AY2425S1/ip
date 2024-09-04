import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KafkaTextWriter {

    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath);) {
            for (Task t : tasks) {
                fw.write(t + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
