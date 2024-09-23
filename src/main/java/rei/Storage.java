package rei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        String storedTask;
        String taskPrompt;

        for (int i = 0; i < fileContent.size(); i++) {
            storedTask = fileContent.get(i);
            taskPrompt = storedTask.substring(0, storedTask.lastIndexOf("|") - 1);
            List<String> tags = Arrays.asList(storedTask.substring(storedTask.lastIndexOf(":") + 1).split(" "));

            if (taskPrompt.startsWith("T ")) {
                loadToDo(taskPrompt, tags, listOfTasks, i);
            } else if (taskPrompt.startsWith("D ")) {
                loadDeadline(taskPrompt, tags, listOfTasks, i);
            } else if (taskPrompt.startsWith("E ")) {
                loadEvent(taskPrompt, tags, listOfTasks, i);
            } else {
                assert false : "a Task musk be either a ToDo, a Deadline, or an Event";
            }
        }

        return listOfTasks;
    }

    private static void loadEvent(String taskPrompt, List<String> tags, List<Task> listOfTasks, int i) {
        Task task = Task.createEvent(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2, taskPrompt.lastIndexOf("to") - 1)),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf("to") + 3)));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(i).markAsDone();
        }
    }

    private static void loadDeadline(String taskPrompt, List<String> tags, List<Task> listOfTasks, int i) {
        Task task = Task.createDeadline(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2)));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(i).markAsDone();
        }
    }

    private static void loadToDo(String taskPrompt, List<String> tags, List<Task> listOfTasks, int i) {
        Task task = Task.createToDo(taskPrompt.substring(8));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(i).markAsDone();
        }
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
