package rei;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Storage.java adapted by https://github.com/adipanda2002/ip/blob/master/src/main/java/pandabot/storage/Storage.java
/**
 * This class handles load data from a file or save data to a file
 */
public class Storage {

    private List<String> fileContent;
    private final String filePath;

    /**
     * Constructs a Storage instance
     * @param filePath where to load the stored data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }



    /**
     * Loads the data stored in the instantiated filePath
     * @return list of stored tasks
     */
    public List<Task> load() throws IOException {
        List<Task> listOfTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return listOfTasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String storedTask;
            String taskPrompt;
            while ((storedTask = reader.readLine()) != null) {
                if (storedTask.trim().isEmpty()) {
                    continue;
                }

                try {
                    taskPrompt = storedTask.substring(0, storedTask.lastIndexOf("|") - 1);
                    List<String> tags = Arrays.asList(storedTask.substring(storedTask.lastIndexOf(":") + 1).split(" "));

                    if (taskPrompt.startsWith("T ")) {
                        loadToDo(taskPrompt, tags, listOfTasks);
                    } else if (taskPrompt.startsWith("D ")) {
                        loadDeadline(taskPrompt, tags, listOfTasks);
                    } else if (taskPrompt.startsWith("E ")) {
                        loadEvent(taskPrompt, tags, listOfTasks);
                    } else {
                        assert false : "a Task musk be either a ToDo, a Deadline, or an Event";
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("There are errors in your Task Data File.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks: " + e.getMessage());
        }

        return listOfTasks;
    }

    /**
     * Stores new updates on the list of tasks to the data storage file
     * @param tasks updated list of tasks
     */
    public void save(TaskList tasks) throws ReiException {
        String dataStorage = tasks.toStoringFormat();

        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(dataStorage);
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks: " + e.getMessage());
        }

    }

    private static void loadEvent(String taskPrompt, List<String> tags, List<Task> listOfTasks) {
        Task task = Task.createEvent(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2, taskPrompt.lastIndexOf("to") - 1)),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf("to") + 3)));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(listOfTasks.size() - 1).markAsDone();
        }
    }

    private static void loadDeadline(String taskPrompt, List<String> tags, List<Task> listOfTasks) {
        Task task = Task.createDeadline(taskPrompt.substring(8, taskPrompt.lastIndexOf('|') - 1),
                LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2)));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(listOfTasks.size() - 1).markAsDone();
        }
    }

    private static void loadToDo(String taskPrompt, List<String> tags, List<Task> listOfTasks) {
        Task task = Task.createToDo(taskPrompt.substring(8));
        task.addTags(tags);
        listOfTasks.add(task);

        if (taskPrompt.charAt(4) == '1') {
            listOfTasks.get(listOfTasks.size() - 1).markAsDone();
        }
    }


}
