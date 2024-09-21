package ontos.storage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

import ontos.task.Task;
import ontos.task.TaskList;

/**
 * Reads and writes the task list from and to storage.
 */
public class SaveManager {
    protected static HashMap<Character, Boolean> isDoneHashMap = new HashMap<>() {{
            put('0', false);
            put('1', true);
        }};

    /** Path to the folder that the file will be saved */
    protected Path savePath;
    /** Path to the file that will be saved */
    protected Path saveFilePath;
    /** String representation of the name of the file that will be saved */
    protected String saveFileName;

    /**
     * Constructs a Save Manager with the specified root path and save file name.
     *
     * @param rootPath     The root directory where the data folder will be created.
     * @param saveFileName The name of the save file (without extension).
     */
    public SaveManager(Path rootPath, String saveFileName) {
        assert rootPath != null : "Root path cannot be null";
        assert saveFileName != null : "Save file name cannot be null";

        this.savePath = rootPath.resolve("data");
        this.saveFileName = saveFileName;
        this.saveFilePath = savePath.resolve(saveFileName + ".txt");
    }

    /**
     * Creates the necessary directories and the save file if they do not already exist.
     */
    public void createSave() {
        try {
            Files.createDirectory(savePath);
            Files.createFile(saveFilePath);
        } catch (IOException e) {
            // File already exists, don't need to do anything
        }
    }

    /**
     * Loads tasks from the save file into a TaskList.
     * If the file is not found, an empty TaskList is returned.
     *
     * @return A TaskList containing the tasks loaded from the save file.
     */
    public TaskList loadSave() {
        try (Scanner sc = new Scanner(saveFilePath.toFile())) {
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                addTask(sc.nextLine(), tasks);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Adds a task to the TaskList from the input string.
     *
     * @param input The string representation of the task.
     * @param tasks The TaskList to which the task will be added.
     */
    public void addTask(String input, TaskList tasks) {
        try {
            Task task = stringToTask(input);
            tasks.addTask(task);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Data is corrupted: " + e.toString());
        }
    }

    /**
     * Converts a string from the save file into a Task object.
     *
     * @param input The stored string representation of the task.
     * @return The Task object created from the input string.
     * @throws StringIndexOutOfBoundsException If the input string is malformed.
     * @throws NumberFormatException If a number in the input string cannot be parsed.
     * @throws IllegalArgumentException If the task type or completion status is invalid.
     * @throws DateTimeParseException If a date in the input string cannot be parsed.
     */
    private Task stringToTask(String input) throws StringIndexOutOfBoundsException,
            NumberFormatException, IllegalArgumentException, DateTimeParseException {

        assert input != null : "Input cannot be null";

        Boolean isDone = isDoneHashMap.get(input.charAt(2));
        if (isDone == null) {
            throw new IllegalArgumentException();
        }
        if (input.startsWith("T")) {
            String description = input.substring(input.indexOf(" /d") + 3).trim();
            return Task.toDo(description, isDone);
        } else if (input.startsWith("D")) {
            int startOfDesc = input.indexOf(" /d");
            int startOfTime = input.indexOf(" /t");

            String description = input.substring(startOfDesc + 3, startOfTime);
            LocalDate time = LocalDate.parse(input.substring(startOfTime + 3).trim());

            return Task.deadline(description, isDone, time);
        } else if (input.startsWith("E")) {
            int startOfDesc = input.indexOf(" /d");
            int startOfStart = input.indexOf(" /b");
            int startOfEnd = input.indexOf(" /e");

            String description = input.substring(startOfDesc + 3, startOfStart);
            LocalDate start = LocalDate.parse(input.substring(startOfStart + 3, startOfEnd));
            LocalDate end = LocalDate.parse(input.substring(startOfEnd + 3).trim());

            return Task.event(description, isDone, start, end);
        } else {
            throw new IllegalArgumentException("Unsupported task type.");
        }
    }

    /**
     * Writes the tasks from the given TaskList to the save file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the save file.
     */
    public void writeToSave(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList cannot be null";

        FileWriter fw = new FileWriter(saveFilePath.toString());
        String[] tasksToAdd = tasks.toSave();
        for (String task : tasksToAdd) {
            fw.write(task + System.lineSeparator());
        }
        fw.close();
    }
}
