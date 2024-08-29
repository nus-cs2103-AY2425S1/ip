package tudee.storage;

import tudee.task.Task;
import tudee.task.ToDo;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.TudeeException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public List<Task> load() throws TudeeException {
        List<Task> ls = new ArrayList<>();
        File currentFile = new File(path);
        if (!currentFile.exists()) {
            return ls;
        }

        try (Scanner sc = new Scanner(currentFile)) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] data = currentLine.split(" \\| ");
                Task currentTask;
                try {
                    switch (data[0]) {
                        case "T":
                            currentTask = new ToDo(data[2]);
                            break;
                        case "D":
                            currentTask = new Deadline(data[2], data[3]);
                            break;
                        case "E":
                            currentTask = new Events(data[2], data[3], data[4]);
                            break;
                        default:
                            throw new TudeeException("Invalid letter");
                    }
                    if (data[1].equals("1")) {
                        currentTask.markAsDone();
                    }
                    ls.add(currentTask);
                }
                catch (TudeeException e) {
                    System.out.println("Error procesesing task list: " + e.getMessage());
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error in loading tasks: " + e.getMessage());
        }
        return ls;
    }

    public void save(List<Task> ls) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Task currentTask : ls) {
                pw.println(currentTask.toFileString());
            }
        }
        catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
