package bigdog;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> taskList) throws BigdogException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task task: taskList) {
                String line = getString(task);
                writer.write(line + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new BigdogException("Storage Error: You do not have a .txt file to store my memory! " + e);
        } catch (IOException e) {
            throw new BigdogException("Storage Error: IO Error " + e);
        }
    }

    private static String getString(Task task) throws BigdogException {
        StringBuilder line = new StringBuilder();
        line.append(task.isMarked() ? "X | " : "O | ");

        if (task instanceof Todo) {
            line.append("T | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            line.append("D | ").append(task.getDescription());
        } else if (task instanceof Event) {
            line.append("E | ").append(task.getDescription());
        } else {
            throw new BigdogException("Storage Error: Unrecognized task type.");
        }
        return line.toString();
    }

    public ArrayList<Task> load() throws BigdogException {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new BigdogException("Storage Error: Unable to load tasks. File Path: " + e);
        }
        return list;
    }

    /**
     * Parses a line of text into a task.
     *
     * @param line the line of text
     * @return the parsed task
     * @throws BigdogException if the line is corrupted or invalid
     */
    private static Task parseLine(String line) throws BigdogException {
        if (line.charAt(0) == 'X') {
            return Task.of(line.substring(4), true);
        } else if (line.charAt(0) == 'O') {
            return Task.of(line.substring(4), false);
        } else {
            throw new BigdogException("Storage Error: Corrupted data in file.");
        }
    }
    
}
