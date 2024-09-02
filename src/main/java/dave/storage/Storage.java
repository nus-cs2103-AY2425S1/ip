package dave.storage;

import dave.task.Task;
import dave.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Storage {
    private static String filePath;

    String horizontal = "__________________________________________________________";
    private ArrayList<Task> taskList;

    public Storage(String file) {
        try {
            this.filePath = file;
            clearFile();
            taskList = new ArrayList<>();
        } catch (IOException e) {
            System.out.println(horizontal);
            System.out.println("An error occurred while saving the task to the file.");
        } catch (Exception e) {
            System.out.println(horizontal);
            System.out.println("An unexpected error occurred while marking the task.");
        }
    }

    public static void saveFile(TaskList dataList) throws IOException
    {
        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
        }
        FileWriter fw = new FileWriter(fileObj);
        for (int i = 0; i < dataList.getSize(); i++)
        {
            fw.write(dataList.getTask(i).write());
        }
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();
    }

    public static void amendFile(Task task) throws IOException
    {
        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
        }
        FileWriter fw = new FileWriter(fileObj, true);
        fw.write(task.write());
        fw.close();
    }

}
