import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private File file;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage load() throws NayanaException{
        this.file = new File(filePath);

        try {
            if(!file.exists()) {
                file.createNewFile();
                return this;
            }
        } catch (IOException e) {
            throw new NayanaException("Error reading from file: " + e.getMessage());
        }

        return this;

    }

    public void writeToFile(ArrayList<Task> tasks) throws NayanaException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String taskString = (i + 1) + ". " + task.getType() + task.getStatus() + " " + task.getDescription() + " " + task.getDates();
                textToAdd.append(taskString).append("\n");
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new NayanaException(e.getMessage());
        }

    }
}
