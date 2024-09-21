package Tools;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Manages storage operations for task data, including loading from and saving to files.
 */
public class NewStorage {
    private String filePath;

    /**
     * Constructs a NewStorage object configured to operate on the specified file path.
     *
     * @param filePath The path of the file used for storage.
     */
    public NewStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file into a NewTaskList.
     *
     * @return A NewTaskList containing all tasks loaded from the file.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public NewTaskList load() throws FileNotFoundException {
        NewTaskList tasks = new NewTaskList();
        File file = new File(filePath);
        try {
            addTasks(tasks, file);
        } catch (FileNotFoundException e) {
            new File("./data").mkdirs(); // Try to create the directory if it doesn't exist
            try {
                file.createNewFile(); // Create the file if it does not exist
            } catch (Exception ex) {
                System.out.println("Unable to create data file");
            }
        }

        return tasks;
    }

    /**
     * Parses a single task from its textual representation and adds it to the task list.
     *
     * @param split The split representation of the task details.
     * @param tasks The task list where the parsed task will be added.
     * @throws FileNotFoundException If the file to read tasks from does not exist.
     */
    public void parseTask(String[] split, NewTaskList tasks) throws FileNotFoundException {
        switch (split[0]) {
            case "T":
                Todo todo = new Todo(split[2], split[3]);
                if (split[1].equals("1")) {
                    todo.markDone();
                }
                tasks.add(todo);
                break;

            case "D":
                Deadline deadline = new Deadline(split[2], split[3], split[4]);
                if (split[1].equals("1")) {
                    deadline.markDone();
                }
                tasks.add(deadline);
                break;

            case "E":
                Event event = new Event(split[2], split[3], split[4], split[5]);
                if (split[1].equals("1")) {
                    event.markDone();
                }
                tasks.add(event);
                break;
        }
    }

    /**
     * Reads tasks from a file and populates a task list.
     *
     * @param tasks The task list to populate.
     * @param file The file to read tasks from.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public void addTasks(NewTaskList tasks, File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] split = line.split(" \\| ");
            parseTask(split, tasks);
        }
        fileScanner.close();
    }

    /**
     * Saves all tasks from a task list to the specified file.
     *
     * @param tasks The task list containing tasks to be saved.
     */
    public void saveTasks(NewTaskList tasks) {
        File file = new File(filePath);

        if (!file.exists()) {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Unable to create data file: " + e.getMessage());
                return;
            }
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }
}
