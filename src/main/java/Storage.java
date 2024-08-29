import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Storage {
    private static final String path = "./data/duke.txt";

    public Storage() {
    }

    public static Task[] load() throws DuckException {
        File file = new File(path);
        Task[] tasks;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return new Task[100];
            } else {
                List<String> tasklist = Files.readAllLines(Paths.get(path));
                int numTasks = 0;
                if (!tasklist.isEmpty()) {
                    numTasks = Integer.parseInt(String.valueOf(tasklist.get(0)));
                }
                tasks = new Task[100];
                for (int i =1; i<numTasks+1; i++) {
                    tasks[i-1] = parser(tasklist.get(i));
                }
                return tasks;
            }
        } catch (IOException e) {
            throw new DuckException("Cannot load tasks.");
        }
    }
    public static int loadNum() throws DuckException {
        File file = new File(path);
        try {
            if (!file.exists()) {
                return 0;
            } else {
                List<String> tasklist = Files.readAllLines(Paths.get(path));
                int numTasks = 0;
                if (!tasklist.isEmpty()) {
                    numTasks = Integer.parseInt(String.valueOf(tasklist.get(0)));
                }
                return numTasks;
            }
        } catch (IOException e) {
            throw new DuckException("Cannot load number of tasks.");
        }
    }

    public static void save(Task[] tasks, int n) throws DuckException {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(n + "\n");
            for (int i =0; i<n; i++) {
                //System.out.println("Saved "+tasks[i].description);
                writer.write(saveTask(tasks[i]) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Cannot save tasks.");
        }
    }

    private static Task parser(String line) throws DuckException {
        String[] parts = line.split(" \\| ");
        if (Objects.equals(parts[0], "T")) {
            Task t = new Todo(parts[2]);
            if (parts[1].equals("1")) {
                t.mark();
            }
            return t;
        } else if (Objects.equals(parts[0], "D")) {
            System.out.println(Arrays.toString(parts));
            Task t = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) {
                t.mark();
            }
            return t;
        } else if (Objects.equals(parts[0], "E")) {
            Task t = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].equals("1")) {
                t.mark();
            }
            return t;
        } else {
            throw new DuckException("Unrecognised file type.");
        }
    }

    private static String saveTask(Task task) {
        String done = "0";
        if (task.isDone) {
            done = "1";
        }
        String type = "";
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        }
        return type+" | "+done+" | "+task.description + task.getDates();
    }
}