package sunny;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes to storage file
 */
public class Storage {
    private Path filePath;

    // Updated constructor to use a dynamic file path
    public Storage(String f) {
        // Use a dynamic file path based on the user's home directory
        filePath = Paths.get(System.getProperty("user.home"), f);
    }

    /**
     * Concatenate status of task behind the task description
     * @param t The task that the status is being added to
     * @return the status of the task in String format
     */
    private String addStatus(Task t) {
        return t.isDone ? "|true\n" : "|false\n";
    }

    /**
     * Writes to the storage file specified by filePath
     */
    public void write(List<Task> ls) {
        StringBuilder str = new StringBuilder();
        for (Task t : ls) {
            if (t instanceof TodoTask) {
                str.append("todo ").append(t.getDescription());
            } else if (t instanceof DeadlineTask) {
                str.append("deadline ").append(t.getDescription());
            } else {
                str.append("event ").append(t.getDescription());
            }
            str.append(addStatus(t));
        }
        try {
            Files.write(filePath, str.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds all information stored in the hardware to a list in one go
     * @param lines String read from the hardware
     * @param ls target list
     */
    public void massAdd(List<String> lines, List<Task> ls) throws Exception {
        for (String line : lines) {
            Task t = TaskCreator.create(line);
            ls.add(t);
        }
    }

    /**
     * Reads from the storage file to a list
     */
    public List<Task> read() {
        List<Task> ls = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                // If file does not exist, create a new one
                Files.createFile(filePath);
                System.out.println("New file created at: " + filePath.toString());
            }
            List<String> lines = Files.readAllLines(filePath);
            massAdd(lines, ls);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new file at: " + filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
}
