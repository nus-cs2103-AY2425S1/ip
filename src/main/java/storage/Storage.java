package storage;

import commandparser.CommandParser;
import ui.Ui;
import task.Task;
import taskmanager.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected TaskManager taskManager;
    protected CommandParser commandParser;

    /**
     * Constructs a Storage object with the specified TaskManager.
     *
     * @param taskManager The TaskManager associated with this storage.
     */

    public Storage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Loads tasks from a file and populates the TaskManager.
     *
     * @param commandParser The command parser used to handle commands.
     * @param ui The UI component to handle user inputs.
     */
    public void loadTasks(CommandParser commandParser, Ui ui) {
        File f = new File("data/tasks.txt");
        if (f.exists()) {
            try (Scanner s = new Scanner(f)) {
                while (s.hasNext()) {
                    ui.handleInput(s.nextLine(), true);
                }

            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        } else {
            System.out.println("Error. Data file does not exist!");
        }
    }

    /**
     * Writes the current list of tasks to a file.
     */
    public void writeTasks() {
        try (FileWriter fw = new FileWriter("data/tasks.txt")) {
            ArrayList<Task> tasks = this.taskManager.getTasks();
            for (Task task: tasks) {
                fw.write(task.getInput() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("File does not exist!");
        }

    }
}