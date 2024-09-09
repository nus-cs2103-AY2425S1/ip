package joe.storage;

import static joe.Constants.TASK_DEADLINE;
import static joe.Constants.TASK_EVENT;
import static joe.Constants.TASK_TODO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import joe.task.Task;
import joe.task.TaskTodo;
import joe.task.TaskEvent;
import joe.task.TaskDeadline;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private static final String FILE_NAME = "./data/store.txt";

    /**
     * Saves the tasks in store to FILE_NAME.
     */
    public void saveTasks(ArrayList<Task> store) {
        try {
            System.out.println("Saving tasks...");
            File file = getFile();
            writeTasksToFile(file, store);
            System.out.println("Tasks saved!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not saved.");
        }
    }

    /**
     * Loads tasks from FILE_NAME and returns them as an ArrayList.
     * 
     * @return The tasks loaded from FILE_NAME.
     */
    public ArrayList<Task> loadTasks() {
        try {
            ArrayList<Task> store = new ArrayList<>();
            System.out.println("Loading tasks...");
            File file = getFile();
            readTasksFromFile(file, store);
            System.out.println("Tasks loaded!");
            return store;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not loaded.");
            return new ArrayList<>();
        }
    }

    private File getFile() throws IOException {
        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

    private void writeTasksToFile(File file, ArrayList<Task> store) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Task task : store) {
            writer.write(task.toSaveString() + "\n");
        }
        writer.close();
    }

    private void readTasksFromFile(File file, ArrayList<Task> store) throws IOException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String taskString = reader.nextLine();
            Task task = parseTask(taskString);
            store.add(task);
        }
        reader.close();
    }

    private Task parseTask(String taskString) {
        String[] parts = taskString.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDescription = parts[2];
        Task task;
        if (type.equals(TASK_TODO)) {
            task = new TaskTodo(taskDescription);
        } else if (type.equals(TASK_DEADLINE)) {
            String by = parts[3];
            task = new TaskDeadline(taskDescription, by);
        } else if (type.equals(TASK_EVENT)) {
            String from = parts[3];
            String to = parts[4];
            task = new TaskEvent(taskDescription, from, to);
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }
        if (isDone) {
            task.toggleDone();
        }
        return task;
    }
}
