package topaz.main;

import topaz.task.Deadline;
import topaz.task.Event;
import topaz.task.Task;
import topaz.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public TaskList load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] parts = nextLine.split(" \\| ");
            Task task;
            Topaz.TaskType type = Topaz.TaskType.valueOf(parts[0]);
            switch (type) {
                case T:
                    task = new Todo(parts[2]);
                    break;
                case D:
                    task = new Deadline(parts[2], LocalDateTime.parse(parts[3]));
                    break;
                case E:
                    task = new Event(parts[2], LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
                    break;
                default:
                    return new TaskList();
            }
            tasks.add(task);
        }
        return new TaskList(tasks);
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        taskList.write(fw);
        fw.close();
    }

}
