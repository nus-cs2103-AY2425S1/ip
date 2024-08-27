import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileReading {
    private static final String SEPARATOR = " \\| ";
    private final static String FILE_PATH = "data/bob.txt";

    protected static void createDirectory(String dirName) {
        File directory = new File(dirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    protected static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile(); // Creates the file if it does not exist
        }
    }

    protected static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] parts = s.nextLine().split(SEPARATOR);
            if (parts[0].equals("T")) {
                Task todo = new Todo(parts[2]);
                todo.isDone = isDone(Integer.parseInt(parts[1]));
                list.add(todo);
            } else if (parts[0].equals("D")) {
                Task deadline = new Deadline(parts[2], parts[3]);
                deadline.isDone = isDone(Integer.parseInt(parts[1]));
                list.add(deadline);
            } else if (parts[0].equals("E")) {
                Task event = new Event(parts[2], parts[3], parts[4]);
                event.isDone = isDone(Integer.parseInt(parts[1]));
                list.add(event);
            }
        }
        return list;
    }

    protected static boolean isDone(int i) {
        return i == 1;
    }

    public static boolean exists(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }
}
