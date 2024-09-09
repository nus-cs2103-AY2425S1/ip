package dude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import dude.exception.DudeCorruptedDataException;
import dude.exception.DudeDateTimeFormatException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.TaskList;
import dude.task.ToDo;

/**
 * Represents a storage that handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty();

        this.filePath = filePath;
    }

    /**
     * Loads tasks from the txt file specified by filePath.
     *
     * @return An ArrayList of tasks loaded from the file, or an empty ArrayList if file does not exist.
     */
    public ArrayList<Task> loadData() {
        File dataFile = new File(filePath);
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
            createNewDataFile();
        }

        return tasks;
    }

    /**
     * Creates a new data file at the specified filePath.
     * Parent directories are created if they do not exist.
     */
    public void createNewDataFile() {
        File dataFile = new File(filePath);
        File parent = new File(dataFile.getParent());
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
            FileWriter fileWriter = new FileWriter(filePath);
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
}
