import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KafkaTextWriter {

    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                if (t instanceof Todo todo) {
                    fw.write("T | " + todo.getStatusBinary() + " | "
                            + todo.description + System.lineSeparator());
                } else if (t instanceof Deadline deadline) {
                    fw.write("D | " + deadline.getStatusBinary() + " | "
                            + deadline.description + " | " + deadline.by + System.lineSeparator());
                } else if (t instanceof Event event) {
                    fw.write("E | " + event.getStatusBinary() + " | "
                            + event.description + " | " + event.from + " | " + event.to
                            + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
