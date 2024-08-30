package yapmeister;

import yapmeister.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage that handles file loading and storing of tasks.
 * @author BlazeChron
 */
public class Storage {
    private String filepath;
    private File tasksFile;
    enum TaskType {
        ToDo,
        Deadline,
        Event
    }

    /**
     * Creates a new Storage at the given filepath.
     * @param filepath Filepath of file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads from the filepath the list of tasks and returns it.
     * @return ArrayList<Task> of tasks loaded from the file at filepath.
     * @throws StorageException Error when the file is inaccessible or does not exist.
     */
    public ArrayList<Task> load() throws StorageException {
        ArrayList<Task> tasks = null;
        this.tasksFile = null;
        try {
            tasksFile = loadTaskFile();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        try {
            tasks = loadSavedTasks(tasksFile);
        } catch (FileNotFoundException e) {
            throw new StorageException(e.getMessage());
        }
        return tasks;
    }

    private File loadTaskFile() throws IOException {
        //create filepath if it does not exist
        if (!Files.exists(Paths.get("./data/"))) {
            new File("./data/").mkdirs();
        }

        File file = new File("./data/tasks.txt");

        //create file if it does not exist
        file.createNewFile();
        return file;
    }

    private ArrayList<Task> loadSavedTasks(File taskFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(taskFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] taskDetails = line.split("\\|");

            boolean isCompleted = taskDetails[1].equals("1");

            switch (taskDetails[0]) {
            case "T":
                tasks.add(createTask(TaskType.ToDo, isCompleted, taskDetails[2]));
                break;
            case "D":
                tasks.add(createTask(TaskType.Deadline, isCompleted, taskDetails[2], taskDetails[3]));
                break;
            case "E":
                tasks.add(createTask(TaskType.Event, isCompleted,
                        taskDetails[2], taskDetails[3], taskDetails[4]));
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves tasks given into the file at filepath.
     * @param tasks Tasks to be saved.
     * @throws IOException Error when file is inaccessible.
     */
    public void saveLoadedTasks(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(tasksFile);
        ArrayList<Task> taskList = tasks.getTaskArrayList();
        for (Task task : taskList) {
            fw.write(String.format("%s\n", task.exportString()));
        }
        fw.close();
    }

    private Task createTask(TaskType type, boolean isCompleted, String ... args) {
        Task task = null;
        switch (type) {
        case ToDo:
            task = new ToDo(args[0]);
            task.setCompleted(isCompleted);
            break;
        case Deadline:
            task = new Deadline(args[0], args[1]);
            task.setCompleted(isCompleted);
            break;
        case Event:
            task = new Event(args[0], args[1], args[2]);
            task.setCompleted(isCompleted);
            break;
        }
        return task;
    }
}
