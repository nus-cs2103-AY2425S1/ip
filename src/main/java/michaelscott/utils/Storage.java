package michaelscott.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.task.TaskParser;

/**
 * Handles storage of tasks, including reading and loading data from file
 */
public class Storage {
    private final String filePath;

    public Storage(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("./userdata"));
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
        this.filePath = "./userdata/tasks.txt";
        loadTasks(taskList);
    }

    public Storage() {
        this.filePath = "./";
    }

    /**
     * Reads the task from file and updates the task list.
     *
     * @param todo the task list.
     */
    public void saveTasks(ArrayList<Task> todo) throws MichaelScottException {
        File file = new File(this.filePath);
        FileWriter fw;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            for (int i = 0; i < todo.toArray().length; i++) {
                fw.write(todo.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new MichaelScottException("Error saving data: " + e.getMessage());
        }
    }

    public void loadTasks(TaskList taskList) {
        File file = new File(this.filePath);
        Scanner sc;
        String line;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            sc = new Scanner(file);
            while (sc.hasNext()) {
                line = sc.nextLine();
                taskList.addTask(TaskParser.parseTask(line));
            }
            sc.close();
        } catch (IOException | MichaelScottException e ) {
            Ui.showError(e.getMessage());
        }
    }
}
