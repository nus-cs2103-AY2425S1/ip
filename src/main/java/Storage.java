import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Scanner loadTaskList() throws FileNotFoundException {
        File file = new File(this.filePath);
        File directory = new File("src/textFile");
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }
        return new Scanner(file);
    }
    public void saveTask(TaskList taskLists) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter =  new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskLists.size(); i++) {
            Task task = taskLists.getTask(i);
            bufferedWriter.write(task.parseToFile());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
