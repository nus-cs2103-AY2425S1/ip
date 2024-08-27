import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void writeOneToFile(Task task) throws BlitzException {
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(task.getType().equals("Empty") ? "" : task.taskToString());
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }
    }

    public void writeAllToFile(TaskList list) throws BlitzException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }

        if (!list.isEmpty()) {
            ArrayList<Task> temp = list.getAllTask();
            for (Task task : temp) {
                writeOneToFile(task);
            }
        }
    }

    public TaskList readFromFile(Ui ui) {
        File f = new File(path);
        TaskList list = new TaskList(new ArrayList<>());

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                list.addTask(Task.stringToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            try {
                writeAllToFile(new TaskList(new ArrayList<>()));
            } catch (BlitzException e2) {
                ui.printError(e2);
            }
        } catch (BlitzException e) {
            ui.printError(e);
        }

        return list;
    }
}
