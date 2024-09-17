package jbot.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import jbot.task.DeadlineTask;
import jbot.task.EmptyToDoDescriptionException;
import jbot.task.EventTask;
import jbot.task.Task;
import jbot.task.ToDoTask;


/**
 * A utility class for managing the storage of tasks in a JSON file.
 * This class cannot be instantiated.
 */

@SuppressWarnings({"FeatureEnvy", "StaticVariableMayNotBeInitialized", "StaticVariableUsedBeforeInitialization"})

public class Storage {
    private Storage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static File storageFile;

    /**
     * Initializes the storage by setting up the storage file.
     * This method should be called before any operations on the storage.
     */
    public static void init() {
        storageFile = Storage.getStorageFile();
    }

    /**
     * Retrieves the storage file. Creates the file if it does not exist.
     *
     * @return The storage file.
     */
    public static File getStorageFile() {
        Path dataFilePath = Paths.get("data", "JBotStorage.json");
        File file = dataFilePath.toFile();

        if (file.exists()) {
            System.out.println("File already exists: " + file.getAbsolutePath());
            return file;
        }

        createFile(file);
        return file;
    }

    /**
     * Creates the file and ensures its parent directories exist.
     *
     * @param file The file to be created.
     */
    private static void createFile(File file) {
        try {
            // Ensure the parent directories exist
            file.getParentFile().mkdirs();

            // Create the file
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("Failed to create file: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the storage file with the current list of tasks.
     * Converts tasks to JSON format and writes them to the file.
     */
    public static void updateData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Storage.storageFile))) {
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < TaskList.size(); i++) {
                Task task = TaskList.get(i);
                if (i > 0) {
                    json.append(",");
                }
                json.append(Storage.taskToJson(task));
            }

            json.append("]");
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Converts a task to its JSON representation.
     *
     * @param task The task to convert.
     * @return The JSON string representing the task.
     */
    private static String taskToJson(Task task) {
        String type = task.getTaskTypeSymbol();
        String name = task.getName();
        String status = task.isDone() ? "X" : " ";

        if (task instanceof ToDoTask) {
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\"}", type, name, status);
        } else if (task instanceof EventTask eventTask) {
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\",\"from\":\"%s\",\"to\":\"%s\"}",
                    type, name, status, eventTask.getFrom(), eventTask.getTo());
        } else if (task instanceof DeadlineTask deadlineTask) {
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\",\"deadline\":\"%s\"}",
                    type, name, status, deadlineTask.getDeadlineAsString());
        }
        return "{}";
    }

    /**
     * Parses the storage file and populates the task list with the data from the file.
     * Handles various task types and updates the task list accordingly.
     */
    public static void parseData() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Storage.storageFile))) {
            String jsonContent = readFileContent(reader);

            if (jsonContent.isEmpty()) {
                TaskList.setList(tasks);
                return;
            }

            JSONArray jsonArray = new JSONArray(jsonContent);
            tasks.addAll(parseTasksFromJson(jsonArray));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("An error occurred while parsing the JSON.");
            e.printStackTrace();
        }

        TaskList.setList(tasks);
    }

    /**
     * Reads the content of the file into a single string.
     *
     * @param reader The BufferedReader to read from.
     * @return The content of the file as a string.
     * @throws IOException If an I/O error occurs.
     */
    private static String readFileContent(BufferedReader reader) throws IOException {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        return json.toString();
    }

    /**
     * Parses the JSON array into a list of tasks.
     *
     * @param jsonArray The JSONArray containing the task data.
     * @return A list of tasks parsed from the JSON array.
     */
    private static ArrayList<Task> parseTasksFromJson(JSONArray jsonArray) throws EmptyToDoDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Task task = createTaskFromJson(jsonObject);

            if (task != null) {
                if (jsonObject.getString("done").equals("X")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Creates a task from the JSON object.
     *
     * @param jsonObject The JSON object containing the task data.
     * @return The created task.
     */
    private static Task createTaskFromJson(JSONObject jsonObject) throws EmptyToDoDescriptionException {
        String type = jsonObject.getString("type");
        String name = jsonObject.getString("name");

        switch (type) {
        case "T":
            return new ToDoTask(String.format("todo %s", name));
        case "E":
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");
            return new EventTask(String.format("event /from %s /to %s", from, to));
        case "D":
            String deadline = jsonObject.getString("deadline");
            return new DeadlineTask(name, deadline);
        default:
            return null;
        }
    }

    /**
     * Extracts the value from a field in a string format.
     *
     * @param field The field string containing the value.
     * @return The extracted value.
     */
    private static String getValue(String field) {
        return field.split(":")[1].replace("\"", "").trim();
    }
}