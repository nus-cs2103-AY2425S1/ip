package choaticbot.storage;

import choaticbot.tasks.Deadlines;
import choaticbot.tasks.Events;
import choaticbot.tasks.TaskList;
import choaticbot.tasks.ToDos;
import choaticbot.tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static choaticbot.ui.Ui.printText;

/**
 * The {@code Storage} class handles saving and loading tasks from a file
 * to persist the user's task list across different program executions.
 */
public class Storage {

    /**
     * The path to the file where tasks are stored.
     */
    private static final String DATA_FILE_PATH = "./data/choaticbot.txt";

    /**
     * Saves the tasks in the provided {@link TaskList} to a file.
     * The tasks are converted to a string format and stored in the specified file path.
     *
     * @param taskList The task list containing the tasks to be saved.
     */
    public static void saveTasks(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("./data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH));

            for (Task task : taskList.getTasks()) {
                String line = convertTaskToLine(task);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            printText("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a {@link Task} object into a line of text that can be written to the file.
     *
     * @param task The task to be converted into a string representation.
     * @return A string representation of the task.
     */
    private static String convertTaskToLine(Task task) {
        String line = "";
        if (task instanceof ToDos) {
            line = "T|" + task.getName() + "|" + task.isComplete();
        } else if (task instanceof Deadlines deadline) {
            line = "D|" + task.getName() + "|" + task.isComplete() + "|" + deadline.getDeadline();
        } else if (task instanceof Events event) {
            line = "E|" + task.getName() + "|" + task.isComplete() + "|" + event.getFrom() + "|" + event.getTo();
        }

        return line;
    }

    /**
     * Loads tasks from the save file into the provided {@link TaskList}.
     * If the save file does not exist, it creates the necessary directories.
     *
     * @param taskList The task list where the loaded tasks will be stored.
     */
    public static void loadTasksFromFile(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("./data"));
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                loadTasksFromText(reader, taskList);
                reader.close();
            }
        } catch (IOException e) {
            printText("An error occurred while opening save file: " + e.getMessage());
        }
    }

    /**
     * Reads and loads tasks from the file and adds them to the task list.
     *
     * @param reader The {@link BufferedReader} used to read from the file.
     * @param taskList The task list where the loaded tasks will be stored.
     * @throws IOException If an I/O error occurs during reading.
     */
    private static void loadTasksFromText(BufferedReader reader, TaskList taskList) throws IOException {
        try {
            String line = reader.readLine();

            while (line != null) {
                String[] parts = line.split("\\|");
                Task task = parseTask(parts);
                taskList.addTask(task);
                line = reader.readLine();
            }
        } catch (IOException e) {
            printText("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the save file and converts it into a {@link Task} object.
     *
     * @param parts The parts of the line representing a task.
     * @return The {@link Task} object created from the parsed line.
     */
    private static Task parseTask(String[] parts) {
        Task task = null;
        if (parts.length >= 2) {
            String type = parts[0];
            String description = parts[1];
            boolean isDone = parts.length > 2 && parts[2].equals("true");
            switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                String deadline = parts.length > 3 ? parts[3] : "";
                task = new Deadlines(description, deadline);
                break;
            case "E":
                String from = parts.length > 3 ? parts[3] : "";
                String to = parts.length > 4 ? parts[4] : "";
                task = new Events(description, from, to);
                break;
            }

            assert task != null;
            task.setDone(isDone);
        }

        return task;
    }
}
