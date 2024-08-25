package echo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Storage {
    private static final String DATA_PATH = "data/data.txt";
    public static void init() {
        Path filePath = Paths.get(Storage.DATA_PATH);

        // If there is already a data.txt file, simply exit
        if (Files.exists(filePath)) {
            setEmptyFile();
            return;
        }

        // Creat a new File object and put a empty task list in it
        File f = new File(DATA_PATH);
        try {
            File parentDir = f.getParentFile();
            parentDir.mkdir();
            f.createNewFile();
            // Put a empty task list in the file created
            TaskList allTasks = new TaskList();
            Storage.setData(allTasks);

        } catch (IOException e) {
            System.out.println("Init Error: " + e.getMessage());
        }
    }

    public static void setEmptyFile() {
        File f = new File(Storage.DATA_PATH);
        if (f.length() == 0) {
            TaskList allTasks = new TaskList();
            Storage.setData(allTasks);
        }
    }

    public static void setData(TaskList taskList) {
        try {
            FileOutputStream fileStream = new FileOutputStream(Storage.DATA_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(taskList);
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskList getData() {
        TaskList allTasks = null;
        try {
            FileInputStream fileStream =new FileInputStream(Storage.DATA_PATH);
            ObjectInputStream outputStream = new ObjectInputStream(fileStream);
            allTasks = (TaskList) outputStream.readObject();
            outputStream.close();
            return allTasks;
        } catch (IOException e) {
            System.out.println("Get Data Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid Class Casting: " + e.getMessage());
        }
        if (allTasks == null) {
            allTasks = new TaskList();
        }
        return allTasks;
    }
}
