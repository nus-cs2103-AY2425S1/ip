import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = "./";
    }

    public ArrayList<Task> loadTasks(String filePath) {
        return new ArrayList<Task>();
    }

    public void saveTasks(String fileName, ArrayList<Task> todo) throws IOException {
        try {
            FileWriter fw = new FileWriter(this.filePath + "/" + fileName);
            for (int i = 0; i < todo.toArray().length; i++) {
                fw.write(todo.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
