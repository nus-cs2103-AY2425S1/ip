package fence.storage;

import fence.parser.Parser;
import fence.task.Task;
import fence.tasklist.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File taskFile = new File("./data/fence.txt");;
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

    public void saveRewrite(TaskList taskList) {
        try {
            taskFile.delete();
            taskFile.createNewFile();
            fw = new FileWriter(taskFile, true);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toTxt() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> read(Parser parser) {
        ArrayList<Task> items = new ArrayList<>();
        dataDir.mkdirs();
        try {
            taskFile.createNewFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                items.add(parser.parseTxt(command));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return items;
    }
}
