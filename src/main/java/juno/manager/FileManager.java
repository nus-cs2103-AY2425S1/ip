package juno.manager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import juno.task.Task;

/**
 * A class that handles the reading and writing to the file, task.json, which stores tasks in JSON format.
 */
public class FileManager {

    private static final String FILE_PATH = "task.json";
    private Gson gsonInstance;

    /**
     * Constructs a FileManager instance which when initialised, will initialise a custom Gson instance as well.
     * The Gson instance will handle Task objects and exclude fields without @Expose annotation.
     */
    public FileManager() {
        this.gsonInstance = new GsonBuilder()
                 .registerTypeAdapter(Task.class, new TaskAdapter())
                 .setPrettyPrinting()
                 .excludeFieldsWithoutExposeAnnotation()
                 .create();
    }

    /**
     * Writes the list of tasks to the file task.json in JSON format.
     *
     * @param tasks The tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            this.gsonInstance.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Looks like we ran into some errors while saving the tasks!"
                    + e.getMessage());
        }
    }

    /**
     * Reads the list of tasks from the file task.json.
     *
     * @return The tasks read from the file, in ArrayList.
     */
    public ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
            tasks = this.gsonInstance.fromJson(reader, taskListType);
        } catch (JsonSyntaxException e) {
            System.out.println("Looks like the file content is not in the correct format!");
            System.out.println("Proceeding to delete the file and create a new one...");
            this.handleCorruptedFile();
        } catch (IOException e) {
            System.out.println("Looks like we ran into some errors while retrieving data regarding your tasks!"
                    + e.getMessage());
        }
        return tasks;
    }

    /**
     * Ensures that the file exists. If the file does not exist, create a new one.
     */
    public void ensureFileExist() {

        File f = new File(FILE_PATH);
        if (!f.isFile()) {
            try {
                boolean isCreatedFile = f.createNewFile();
            } catch (IOException e) {
                System.out.println("Looks like we ran into some errors while trying to store your data!"
                        + e.getMessage());
            }
        }
    }

    /**
     * Handles the issue when file is corrupted by deleting the corrupted file and creating a new one.
     */
    public void handleCorruptedFile() {
        File f = new File(FILE_PATH);
        if (f.delete()) {
            this.ensureFileExist();

            System.out.println("File created! Unfortunately, it looks like your previous tasks have vanished into the"
                    + " digital abyss. But hey, fresh start, right? 🌟");
        } else {
            System.out.println("Seems like we can't delete the file either, maybe try deleting it manually "
                    + "and run me again!");
        }
    }
}
