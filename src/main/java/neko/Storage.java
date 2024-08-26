package neko;
import neko.task.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    private static final String DIRECTORY_PATH = "./data";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                Task task = Parser.parseTask(str);
                if (task != null) tasks.add(task);
            }
        }
        return tasks;
    }

    public void writeFile(String text) throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) directory.mkdirs();

        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();

        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text);
        fw.close();
    }

    public void rewriteFile(TaskList tasks) throws IOException, NekoException {
        FileWriter fw = new FileWriter(filePath, false);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            String taskType = task.getClass().getSimpleName().charAt(0) + "";
            String status = task.isDone() ? "1" : "0";
            String taskDetails = "";

            if (task instanceof Todo) {
                taskDetails = task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails = task.getDescription() + " | " + deadlineTask.getTime();
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails = task.getDescription() + " | " + eventTask.getTime();
            }

            fw.write(taskType + " | " + status + " | " + taskDetails + "\n");
        }
        fw.close();
    }


}
