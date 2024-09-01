import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File filePath;
    Scanner scanner;
    TaskList taskList;

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
        this.taskList = new TaskList(taskArrayList);
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
        return this.taskList;
    }

    public void writeFile(Ui ui) {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i < Task.count; i++) {
            Task task = taskList.get(i - 1);
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        try {
            FileWriter fw = new FileWriter("./data/gallium.txt");
            fw.write(listString);
            fw.close();
        } catch (IOException e) {
            ui.showIOException(e);
        }
    }
}
