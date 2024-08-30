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
public class Storage {
    private static File storageFile;

    public Storage() {
        Storage.storageFile = getStorageFile();
    }

    public static File getStorageFile() {
        Path dataFilePath = Paths.get("data", "JBotStorage.json");
        File file = dataFilePath.toFile();

        // Create the file if it doesn't exist
        if (!file.exists()) {
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
        } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
        }

        return file;
    }

    public static void updateData(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile))) {
            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (i > 0) {
                    json.append(",");
                }
                json.append(taskToJson(task));
            }

            json.append("]");
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static String taskToJson(Task task) {
        String type = task.taskTypeSymbol;
        String name = task.name;
        String status = task.isDone() ? "X" : " ";

        if (task instanceof ToDoTask) {
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\"}", type, name, status);
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\",\"from\":\"%s\",\"to\":\"%s\"}",
                    type, name, status, eventTask.from, eventTask.to);
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return String.format("{\"type\":\"%s\",\"name\":\"%s\",\"done\":\"%s\",\"deadline\":\"%s\"}",
                    type, name, status, deadlineTask.deadline);
        }
        return "{}";
    }

    public static ArrayList<Task> parseData() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(storageFile))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            if (json.length() == 0) {
                return tasks; // Return empty list if file is empty
            }

            JSONArray jsonArray = new JSONArray(json.toString());

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
                    task = new DeadlineTask(String.format("deadline /by %s", deadline));
                    break;
                }

                if (task != null) {
                    if (done) task.markAsDone();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    private static String getValue(String field) {
        return field.split(":")[1].replace("\"", "").trim();
    }
}