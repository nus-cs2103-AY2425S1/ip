import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File filePath;

    public Storage(File filePath) {
        this.filePath = filePath;
        try {
            File dir = filePath;
            dir.mkdirs();
            File f = filePath;
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file:" + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(this.filePath);
            while (scanner.hasNextLine()) {
                String taskDesc = scanner.nextLine();
                if (taskDesc.startsWith("[T]")) {
                    Todo todo = new Todo(taskDesc);
                    taskList.add(todo);
                } else if (taskDesc.startsWith("[D]")) {
                    Deadline deadline = new Deadline(taskDesc);
                    taskList.add(deadline);
                } else if (taskDesc.startsWith("[E]")) {
                    Event event = new Event(taskDesc);
                    taskList.add(event);
                }
                Task.count = taskList.size() + 1;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return taskList;
    }
}
