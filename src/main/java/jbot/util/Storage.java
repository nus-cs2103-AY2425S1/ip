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

public class Storage {
    private static File storageFile;
    private Storage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Initializes the storage by setting up the storage file.
     * This method should be called before any operations on the storage.
     */
    public static void init() {
        storageFile = Storage.getStorageFile();
        assert storageFile != null : "Storage file should be initialized";
        assert storageFile.exists() : "Storage file must exist after initialization";
    }

    /**
     * Retrieves the storage file. Creates the file if it does not exist.
     *
     * @return The storage file.
     */
    public static File getStorageFile() {
        Path dataFilePath = Paths.get("data", "JBotStorage.json");
        File file = dataFilePath.toFile();

        // Create the file if it doesn't exist
        if (!file.exists()) {
            try {
                // Ensure the parent directories exist
                assert file.getParentFile() != null : "Parent directory of the file must not be null";
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
        } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
        }

        assert file.exists() : "Storage file must exist after being retrieved or created";
        return file;
    }

    /**
     * Updates the storage file with the current list of tasks.
     * Converts tasks to JSON format and writes them to the file.
     */
    public static void updateData() {
        assert storageFile != null : "Storage file must be initialized before updating data";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Storage.storageFile))) {
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < TaskList.size(); i++) {
                Task task = TaskList.get(i);
                assert task != null : "Task in TaskList should not be null";
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
        assert task != null : "Task to convert to JSON must not be null";

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
        assert storageFile != null : "Storage file must be initialized before parsing data";

        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Storage.storageFile))) {
            StringBuilder json = new StringBuilder();
            String line;
            //noinspection NestedAssignment
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            if (json.length() == 0) {
                TaskList.setList(tasks);
                return; // Exit if file is empty
            }

            JSONArray jsonArray = new JSONArray(json.toString());
            assert jsonArray.length() >= 0 : "Parsed JSON array should not have negative length";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String type = jsonObject.getString("type");
                String name = jsonObject.getString("name");
                boolean done = jsonObject.getString("done").equals("X");

                Task task = null;
                switch (type) {
                case "T":
                    task = new ToDoTask(String.format("todo %s", name));
                    break;
                case "E":
                    String from = jsonObject.getString("from");
                    String to = jsonObject.getString("to");
                    task = new EventTask(String.format("event /from %s /to %s", from, to));
                    break;
                case "D":
                    String deadline = jsonObject.getString("deadline");
                    task = new DeadlineTask(name, deadline);
                    break;
                default:
                    assert false : "Unknown task type: " + type;
                }

                assert task != null : "Parsed task should not be null";
                if (done) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
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
     * Extracts the value from a field in a string format.
     *
     * @param field The field string containing the value.
     * @return The extracted value.
     */
    private static String getValue(String field) {
        assert field != null && field.contains(":") : "Field must contain a valid key-value format";
        return field.split(":")[1].replace("\"", "").trim();
    }
}
