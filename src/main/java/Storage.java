import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    public void load(TaskList tasks) throws WenJigglyBotException {
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            System.out.println("Tasks loaded from " + file.getPath());
        } catch (IOException e) {
            throw new WenJigglyBotException("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }


}