package susan.ui;

import susan.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles saving tasks to file.
 */
public class Storage {
    final String FILE_PATH = "./data/SusanToDoList.txt";

    public Storage() {}

    /**
     * Creates data directory if it does not exist.
     * Writes updated TaskList into file.
     *
     * @param tasks updated TaskList.
     * @throws SusanException if saving to file fails.
     */
    public void load(TaskList tasks) throws SusanException {
        // Create data file
        File dataPath = new File("./data");
        if (!dataPath.exists()) {
            boolean x = dataPath.mkdir();
            assert x : "Failed to create data path";
        }
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(tasks.saveList());
            fw.close();
        } catch (Exception e) {
            throw new SusanException("Error saving tasks to file");
        }
    }

    /**
     * Returns TaskList from data file if it exists.
     *
     * @throws SusanException if file does not exist.
     */
    public TaskList readTasksFromFile() throws SusanException {
        try {
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);
            TaskList tasks = new TaskList();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                tasks = processTask(tasks, line);
            }
            return tasks;
        } catch (Exception e) {
            throw new SusanException("Tasklist has not been created yet or data file has been corrupted.");
        }
    }

    /**
     * Parses lines from data file into task.
     * Returns original TaskList if line format is invalid.
     */
    private TaskList processTask(TaskList tasks, String line) {
        String[] commandParts = line.split(" : | ~ ");
        String commandWord = commandParts[0];

        Task task;
        switch (commandWord) {
        case "T":
            task = new ToDo(commandParts[2]);
            break;
        case "D":
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate by = LocalDate.parse(commandParts[3], deadlineFormatter);
            task = new Deadline(commandParts[2], by);
            break;
        case "E":
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(commandParts[3], eventFormatter);
            LocalDateTime to = LocalDateTime.parse(commandParts[4], eventFormatter);
            task = new Event(commandParts[2], from, to);
            break;
        default:
            // Unable to upload task from file
            // Return original TaskList
            return tasks;
        }

        if (commandParts[1].equals("X")) {
            task.markAsDone();
        }
        tasks.add(task);
        return tasks;
    }
}
