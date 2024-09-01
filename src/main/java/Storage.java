import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File filePath;
    Scanner scanner;

    public Storage(String filePathString) {
        try {
            File dir = new File(filePathString.split("/gallium.txt")[0]);
            dir.mkdirs();
            File f = new File(dir, "gallium.txt");
            f.createNewFile();
            this.filePath = new File(filePathString);
            this.scanner = new Scanner(this.filePath);
        } catch (IOException e) {
            System.out.println("Error creating file:" + e.getMessage());
        }
    }

    public TaskList load() {
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        TaskList taskList = new TaskList(taskArrayList);
        while (scanner.hasNextLine()) {
            String taskDesc = scanner.nextLine();
            if (taskDesc.startsWith("[T]")) {
                Todo todo = new Todo(taskDesc);
                taskArrayList.add(todo);
            } else if (taskDesc.startsWith("[D]")) {
                Deadline deadline = new Deadline(taskDesc);
                taskArrayList.add(deadline);
            } else if (taskDesc.startsWith("[E]")) {
                Event event = new Event(taskDesc);
                taskArrayList.add(event);
            }
        }
        Task.count = taskArrayList.size() + 1;
        scanner.close();
        return taskList;
    }
}
