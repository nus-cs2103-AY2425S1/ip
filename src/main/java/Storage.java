import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws ChatBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String directoryPath = "./data";
            if (Files.exists(Paths.get(directoryPath)) &&
                    Files.isDirectory(Paths.get(directoryPath)) &&
                    Files.exists(Paths.get(filePath)) &&
                    Files.isRegularFile(Paths.get(filePath))) {
                Scanner s = new Scanner(Paths.get(filePath));
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    tasks.add(Parser.parseTaskFromFile(taskString));
                }
            }
        } catch (IOException e) {
            throw new ChatBotException("Read failed. Something went wrong: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws ChatBotException {
        try {
            String directoryPath = "./data";
            if (!Files.exists(Paths.get(directoryPath)) ||
                    !Files.isDirectory(Paths.get(directoryPath))) {
                Files.createDirectory(Paths.get(directoryPath));
            }

            if (!Files.exists(Paths.get(filePath)) ||
                    !Files.isRegularFile(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.formatToSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new ChatBotException("Save failed. Something went wrong: " + e.getMessage());
        }
    }
}