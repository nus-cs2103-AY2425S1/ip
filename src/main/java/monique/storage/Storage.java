package monique.storage;

import monique.task.Task;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    private final File dbFile;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.dbFile = this.getDbFile();
    }

    public static String getCurrentPath() {
        String s = Paths.get("").toAbsolutePath().toString();
        return s;
    }

    public static String getDataPath() {
        return String.valueOf(Paths.get("data/tasks.txt"));
    }

    public File getDbFile() throws IOException {
        File dbFile = new File(String.valueOf(Paths.get(this.filePath)));
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            dbFile.createNewFile();
        }
        return dbFile;
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(this.getDbFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Task task : taskList) {
                oos.writeObject(task);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            FileInputStream fin = new FileInputStream(this.getDbFile());
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
