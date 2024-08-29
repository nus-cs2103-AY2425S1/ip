import java.io.FileWriter;
import java.io.IOException;

public class TaskListSaver {
    String filePath;

    TaskListSaver(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String text) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save tasks to hard disk");
        }
    }
}
