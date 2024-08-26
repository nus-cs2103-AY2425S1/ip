import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task from the tasks arraylist into the data file
     *
     * @param tasks a task list to be saved in the data file
     */
    public void saveTasks(List<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // create directories just in case they don't exist

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks present in the data file
     *
     * @return arraylist of tasks
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // return an empty arraylist of tasks if the file doesn't exist yet
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                Task task = getTask(taskLine);

                if (task != null) {
                    tasks.add(task);
                }

            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;

    }

    /**
     * Returns a task whose type depends on the task data entry
     *
     * @param taskLine a line in the nether.txt task data file
     * @return a task to be added to the tasks arraylist
     */
    private static Task getTask(String taskLine) {
        String[] taskParts = taskLine.split("\\|");
        Task task = null;

        switch (taskParts[0]) {
        case "T":
            task = new TodoTask(taskParts[2]);
            break;
        case "D":
            task = new DeadlineTask(taskParts[2], taskParts[3]);
            break;
        case "E":
            task = new EventTask(taskParts[2], taskParts[3], taskParts[4]);
            break;
        }

        if (task != null && taskParts[1].equals("X")) {
            task.markAsDone();
        }
        return task;
    }


}
