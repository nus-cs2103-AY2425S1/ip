import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {

    private static String filePath= null;
    private static ArrayList<String> listForDisk;
    public Storage(String filePath) {
        this.filePath = filePath;
        this.listForDisk = new ArrayList<>(100);
    }

    /**
     * Writes the given task description to the file.
     */
    public static void writeToFile(TaskList local) throws Exception {
        FileWriter fw = new FileWriter(filePath, true);
        for (int x = 0; x < local.getSize(); x++) {
            Task curr_task = local.get(x);
            listForDisk.add(curr_task.toStore());
        }

        for (int x = 0; x < listForDisk.size(); x++) {
            fw.write(listForDisk.get(x) + System.lineSeparator());
        }
        fw.close();
    }

    public static ArrayList<Task> loadFile() throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Bobby.constructTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }
        FileWriter fw = new FileWriter(filePath, false);
        return taskList;
    }
}
