package rei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<String> fileContent;
    private Path filePath;
    public Storage(String filePath) {
        try {
            this.filePath = Path.of(filePath);
            this.fileContent = Files.readAllLines(this.filePath);
        } catch (FileNotFoundException e) {
            Ui.print("Cannot read file : " + e.getMessage());
        } catch (IOException e) {
            Ui.print("Cannot read file : " + e.getMessage());
        }
    }

    public List<Task> load() {
        List<Task> listOfTasks = new ArrayList<>();
        String taskPrompt;

        for (int i = 0; i < fileContent.size(); i++) {
            taskPrompt = fileContent.get(i);
            if (taskPrompt.startsWith("T ")) {
                listOfTasks.add(Task.createToDo(taskPrompt.substring(8)));
                if (taskPrompt.charAt(4) == '1') {
                    listOfTasks.get(i).markAsDone();
                }
            } else if (taskPrompt.startsWith("D ")) {
                listOfTasks.add(Task.createDeadline(taskPrompt.substring(8, taskPrompt.lastIndexOf('|')),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2))));
                if (taskPrompt.charAt(4) == '1') {
                    listOfTasks.get(i).markAsDone();
                }
            } else { // Event

                listOfTasks.add(Task.createEvent(taskPrompt.substring(8, taskPrompt.lastIndexOf('|')),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2, taskPrompt.lastIndexOf("to") - 1)),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf("to") + 3))));
            }
        }

        return listOfTasks;
    }

    public void save(TaskList tasks) {
        String dataStorage = tasks.toStoringFormat();

        try {
            Files.writeString(filePath, dataStorage);
        } catch (IOException e) {
            Ui.print("Error in saving tasks: " + e.getMessage());
        }

    }
}
