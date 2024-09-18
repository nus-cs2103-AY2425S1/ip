package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.NoLastCommandToUndo;
import task.Task;

/**
 * Manages saving and loading tasks from a file.
 */
public class Storage {

    private final String filePath;
    private final Map<String, Task> commands = new LinkedHashMap<>();

    /**
     * Initializes the Storage object with the specified file path.
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileIsAccessible();
    }

    /**
     * Ensures that the file and its directory are accessible.
     * If the directory or file doesn't exist, it attempts to create them.
     */
    private void ensureFileIsAccessible() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Unable to create file at " + filePath);
            }
        }

        if (!file.canRead() || !file.canWrite()) {
            System.out.println("Error: File " + filePath + " is not accessible for reading/writing.");
        }
    }

    /**
     * Loads tasks from the file.
     * @return The list of tasks loaded from the file.
     * @throws FileNotFoundException if the file is not found.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataSaved = new File(filePath);
        Scanner scanner = new Scanner(dataSaved);

        while (scanner.hasNext()) {
            String[] dataArr = scanner.nextLine().split(" \\| ");
            Task newTask = parser.Parser.parseSavedData(dataArr); // Assumes parseSavedData parses the saved data
            taskList.add(newTask);
        }

        return taskList;
    }

    /**
     * Saves the current task list to the file.
     * @param taskList The list of tasks to save.
     * @throws IOException if there's an error writing to the file.
     */
    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        String separation = " | ";
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            fw.write(task.toSavedFormat(separation) + "\n");
        }
        fw.close();
    }

    public int getCommandsSize() {
        return commands.size();
    }

    /**
     * keeps track of the all commands that the user enters.
     * @param command the command keyword that the user enters
     * @param task the task associated with the command
     */
    public void saveCommands(String command, Task task) {
        commands.put(command, task);
    }

    /**
     * Removes the last entry of the command arraylist
     * @throws NoLastCommandToUndo if the user hasn't entered any command, and he tries to undo
     */
    public Map<String, Task> lastCommand() throws NoLastCommandToUndo {
        if (commands.isEmpty()) {
            throw new NoLastCommandToUndo();
        }
        String lastCommand = commands.keySet()
                .toArray(new String[0])[commands.size() - 1];
        Task lastTask = commands.get(commands.keySet()
                .toArray(new String[0])[commands.size() - 1]);
        commands.remove(lastCommand);
        Map<String, Task> result = new HashMap<>();
        result.put(lastCommand, lastTask);
        return result;
    }
}
