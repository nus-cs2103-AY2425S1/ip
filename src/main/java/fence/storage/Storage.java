package fence.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fence.parser.Parser;
import fence.task.Task;
import fence.tasklist.TaskList;

public class Storage {

    private File taskFile = new File("./data/fence.txt");
    private File dataDir = new File("./data");
    private FileWriter fw;

    public void saveAppend(Task task) {
        try {
            fw = new FileWriter(taskFile, true);
            fw.write(task.toTxt() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void saveRewrite(TaskList tasks) {
        try {
            taskFile.delete();
            taskFile.createNewFile();
            fw = new FileWriter(taskFile, true);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toTxt() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> read(Parser parser) {
        ArrayList<Task> tasks = new ArrayList<>();
        dataDir.mkdirs();
        try {
            taskFile.createNewFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                tasks.add(parser.parseTxt(command));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return tasks;
    }
}
