import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    private File file;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private String FILE_PATH = "./data/agave.txt";

    public FileHandler(String filePath) {
        this.file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error when creating file: " + e.getMessage());
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for(Task tak : tasks) {
                fileWriter.write(tasks.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error when writing to file: " + e.getMessage());
        } finally {
            fileWriter.close();
        }
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                tasks.add(new Task(line));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error when reading from file: " + e.getMessage());
        } finally {
            bufferedReader.close();
        }
        return tasks;
    }
}
