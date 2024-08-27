import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataLoading {
    public static String getCurrentPath() {
        String s = Paths.get("").toAbsolutePath().toString();
        return s;
    }

    public static String getDataPath() {
        return String.valueOf(Paths.get("data/db.txt"));
    }

    public static File getDbFile() throws IOException {
        File dbFile = new File(String.valueOf(Paths.get("data/db.txt")));
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            dbFile.createNewFile();
            System.out.println("Created database file to store your data at: " + dbFile.getAbsolutePath());
        } else {
            System.out.println("Successfully Loaded your previous todo list from the database");
        }
        return dbFile;
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(getDbFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Task task : taskList) {
                oos.writeObject(task);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            FileInputStream fin = new FileInputStream(getDbFile());
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object o;
            while(true) {
                try {
                    o = ois.readObject();
                    if (o instanceof Task) {
                        taskList.add((Task) o);
                    } else {
                        System.err.println("Unexpected objects found in database");
                    }
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (EOFException eof) {
            //empty database from the start
            return taskList;
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return taskList;
    }



}
