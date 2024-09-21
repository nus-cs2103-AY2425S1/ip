package chobo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the user's storage.
 */
public class Storage {
    private final String filePath;
    private final Ui ui;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath File path
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads tasks into array list.
     *
     * @return the array list
     */
    public TaskList loadTasks() throws InputException{
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String name = parts[2];
                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E") : "Task type should be one of those";

                Command command;
                switch (taskType) {
                case "T":
                    command = new AddToDoCommand(name);
                    break;
                case "D":
                    String by = parts[3];
                    command = new AddDeadlineCommand(name, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    command = new AddEventCommand(name, from, to);
                    break;
                default:
                    continue;
                }
                command.execute(tasks, ui, this);
                if (isDone) {
                    tasks.getTask(tasks.getTotalTask() - 1).mark();
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks in file.
     *
     * @param tasks List of tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't save tasks.");
        }
    }
}

