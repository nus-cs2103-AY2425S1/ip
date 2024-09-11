package dude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dude.exception.DudeCorruptedDataException;
import dude.exception.DudeDateTimeFormatException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.ToDo;
import javafx.util.Pair;

/**
 * Represents a storage that handles the loading and saving of tasks to a file.
 */
public class Storage {
    private static final String DATA_FILE_NAME = "/dude.txt";
    private static final String SHORTCUT_FILE_NAME = "/shortcut.txt";
    private String filePath;
    private String dataFilePath;
    private String shortcutFilePath;

    /**
     * Constructs a Storage with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty();

        this.filePath = filePath;
        dataFilePath = filePath + DATA_FILE_NAME;
        shortcutFilePath = filePath + SHORTCUT_FILE_NAME;
    }

    /**
     * Loads tasks from the txt file specified by filePath.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty ArrayList if file does not exist.
     */
    public ArrayList<Task> loadData() {
        File dataFile = new File(dataFilePath);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(dataFile);

            while (fileScanner.hasNextLine()) {
                String dataLine = fileScanner.nextLine();
                try {
                    tasks.add(stringDataToTask(dataLine));
                } catch (DudeCorruptedDataException e) {
                    System.out.println(e.getMessage());
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            createNewFile(dataFilePath);
        }

        return tasks;
    }

    /**
     * Creates a new data file at the specified filePath.
     * Parent directories are created if they do not exist.
     */
    public void createNewFile(String fullFilePath) {
        File dataFile = new File(fullFilePath);
        File parent = new File(filePath);
        parent.mkdirs();

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("There is something wrong while creating data file:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task into a Task object.
     *
     * @param stringData The string representation of the task.
     * @return The Task object corresponding to the string data.
     * @throws DudeCorruptedDataException If the string data is corrupted or invalid.
     */
    public Task stringDataToTask(String stringData) throws DudeCorruptedDataException {
        String[] taskComponent = stringData.split("\\|");
        Task task;

        try {
            switch (taskComponent[0]) {
            case "T":
                task = new ToDo(taskComponent[2]);
                break;
            case "D":
                LocalDateTime by = Parser.stringToDateTime(taskComponent[3]);
                task = new Deadline(taskComponent[2], by);
                break;
            case "E":
                LocalDateTime from = Parser.stringToDateTime(taskComponent[3]);
                LocalDateTime to = Parser.stringToDateTime(taskComponent[4]);
                task = new Event(taskComponent[2], from, to);
                break;
            default:
                throw new DudeCorruptedDataException();
            }
        } catch (ArrayIndexOutOfBoundsException | DudeDateTimeFormatException e) {
            throw new DudeCorruptedDataException();
        }

        if (taskComponent[1].equals("X")) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the list of tasks to the txt file specified by filePath.
     *
     * @param taskList The TaskList with list of tasks to be saved.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(dataFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            ArrayList<Task> tasks = taskList.getTasks();

            for (Task task : tasks) {
                bufferedWriter.write(task.taskToStringData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("There is something wrong while saving to data file:");
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, CommandType> loadShortcut() {
        File shortcutFile = new File(shortcutFilePath);
        HashMap<String, CommandType> shortcutMap = new HashMap<>();

        try {
            Scanner fileScanner = new Scanner(shortcutFile);

            while (fileScanner.hasNextLine()) {
                String shortcutLine = fileScanner.nextLine();
                try {
                    Pair<String, CommandType> shortcutPair = stringToShortcutPair(shortcutLine);
                    shortcutMap.put(shortcutPair.getKey(), shortcutPair.getValue());
                } catch (DudeCorruptedDataException e) {
                    System.out.println(e.getMessage());
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            createNewFile(dataFilePath);
        }

        return shortcutMap;
    }

    public Pair<String, CommandType> stringToShortcutPair(String string) throws DudeCorruptedDataException {
        String[] splitString = string.split("\\|");

        if (splitString.length != 2) {
            throw new DudeCorruptedDataException();
        }

        try {
            CommandType command = CommandType.valueOf(splitString[1]);

            return new Pair<>(splitString[0], command);
        } catch (IllegalArgumentException e) {
            throw new DudeCorruptedDataException();
        }
    }

    public void saveShortcut(Parser parser) {
        HashMap<String, CommandType> shortcutMap = parser.getShortcutMap();

        try {
            FileWriter fileWriter = new FileWriter(shortcutFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder s = new StringBuilder();

            shortcutMap.forEach((key, value) -> {
                s.append(key + "|" + value.name() + '\n');
            });

            bufferedWriter.write(s.toString());

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("There is something wrong while saving to shortcut file:");
            System.out.println(e.getMessage());
        }
    }
}
