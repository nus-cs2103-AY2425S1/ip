import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriting {
    protected static void writeToFile(String filePath, ArrayList<Task> updatedList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : updatedList) {
            fw.write(t + "\n");
        }
        fw.close();
    }

    protected static String listToStringFormatted(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String type = t.getType();
        String status = t.getStatusIcon();
        String description = t.getDescription();
        if (type == "E") {
            String by = t.getBy();
        }
        if (type == "D") {
            String from = t.getFrom();
            String to = t.getTo();
        }

        String formatted = type + "|" + status + "|" + description;
        fw.write(formatted);
        fw.close();
        return formatted;
    }
}
