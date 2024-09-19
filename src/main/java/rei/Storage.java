package rei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles load data from a file or save data to a file
 */
public class Storage {

    private List<String> fileContent;
    private Path filePath;

    /**
     * Constructs a Storage instance
     * @param filePath where to load the stored data
     */
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

    /**
     * Loads the data stored in the instantiated filePath
     * @return list of stored tasks
     */
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
                listOfTasks.add(Task.createDeadline(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2))));
                if (taskPrompt.charAt(4) == '1') {
                    listOfTasks.get(i).markAsDone();
                }
            } else if (taskPrompt.startsWith("E ")) {

                listOfTasks.add(Task.createEvent(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2, taskPrompt.lastIndexOf("to") - 1)),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf("to") + 3))));
            } else {
                // do nothing
            }
        }

        return listOfTasks;
    }

    /**
     * Stores new updates on the list of tasks to the data storage file
     * @param tasks updated list of tasks
     */
    public void save(TaskList tasks) throws ReiException {
        String dataStorage = tasks.toStoringFormat();

        try {
            Files.writeString(filePath, dataStorage);
        } catch (IOException e) {
            throw new ReiException("Error in saving tasks: " + e.getMessage());
        }

    }
}
