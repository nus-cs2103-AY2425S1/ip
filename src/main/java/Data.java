import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Data {
    private static final String DATA_PATH = "data/data.txt";
    public static void init() {
        Path filePath = Paths.get(Data.DATA_PATH);
        if (Files.exists(filePath)) {
            return;
        }
        File f = new File(DATA_PATH);
        try {
            File parentDir = f.getParentFile();
            if (parentDir.isDirectory() && !parentDir.exists()) {
                parentDir.mkdir();
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Init Error: " + e.getMessage());
        }
    }

    public static void setData(ArrayList<Task> allTask) {
        try {
            // FileWriter fw = new FileWriter(Data.DATA_PATH);
            FileOutputStream fileStream = new FileOutputStream(Data.DATA_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            for (Task t : allTask) {
                os.writeObject(t);
            }
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> getData() {
        ArrayList<Task> allTask = new ArrayList<>();
        File f = new File(Data.DATA_PATH);
        if (f.length() == 0) {
            return allTask;
        }
        try {
            FileInputStream fileStream =new FileInputStream(Data.DATA_PATH);
            ObjectInputStream outputStream = new ObjectInputStream(fileStream);
            while (true) {
                try {
                    Task t = (Task) outputStream.readObject();
                    allTask.add(t);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found: " + e.getMessage());
                    break;
                }
            }
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Get Data Error: " + e.getMessage());
        }
        return allTask;
    }
}
