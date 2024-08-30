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
    private String filePath;

    public Storage(String f) {
        filePath = f;
    }

    /**
     * Writes to the storage file specified by filePath
     */
    public void write(List<Task> ls) {
        String str = "";
        for (Task t: ls) {
            if (t instanceof TodoTask) {
                str += "todo " + t.getDescription();
                if (t.isDone) {
                    str += "|true" + "\n";
                } else {
                    str += "|false" + "\n";
                }
            } else if (t instanceof DeadlineTask) {
                str += "deadline " + t.getDescription();
                if (t.isDone) {
                    str += "|true" + "\n";
                } else {
                    str += "|false" + "\n";
                }
            } else {
                str += "event " + t.getDescription();
                if (t.isDone) {
                    str += "|true" + "\n";
                } else {
                    str += "|false" + "\n";
                }
            }
        }
        try {
            Files.write(Paths.get(filePath), str.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please create new file with file path: " +
                    "/Users/jerryyou/ip/taskslist.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from the storage file to a list
     */
    public List<Task> read() {
        List<Task> ls = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task t = TaskCreator.create(line);
                ls.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please create new file with file path: " +
                    "/Users/jerryyou/ip/taskslist.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

}
