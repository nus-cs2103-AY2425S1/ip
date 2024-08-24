import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public Path dirPath;
    public Path filePath;

    public Storage() {
        String home = System.getProperty("user.home");
        Path dirPath = Paths.get(home, "Documents", "Github", "IP", "data");
        boolean directoryExists = Files.exists(dirPath);
        this.dirPath = dirPath;
        this.filePath = Paths.get(home, "Documents", "Github", "IP", "data", "tasks.txt");

        //Create directory if needed
        if (!directoryExists) {
            try {
                Files.createDirectory(this.dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File f = new File(this.filePath.toString());
        //Create file if needed
        if (!f.isFile()) {
            try {
                Files.createFile(this.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Task> loadTasks() {

        if (Files.exists(this.dirPath) && new File(this.filePath.toString()).isFile()) {
            try {
                List<String> lines = Files.readAllLines(this.filePath);
                ArrayList<Task> t = new ArrayList<>();
                for (String line : lines) {
                    String[] frags = line.split("\\s*\\|\\s*");
                    if (frags[0].equals("T")) {
                        Task task = new Todo(frags[2]);
                        if (frags[1].equals("1")) {
                            task.markTask();
                        }
                        t.add(task);
                    } else if (frags[0].equals("E")) {
                        Task task = new Event(frags[2]);
                        if (frags[1].equals('1')) {
                            task.markTask();
                        }
                        t.add(task);
                    } else {
                        Task task = new Deadline(frags[2]);
                        if (frags[1].equals("1")) {
                            task.markTask();
                        }
                        System.out.println(task.description);
                        t.add(task);
                    }
                }

                return t;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

    public void saveTask(ArrayList<Task> t) {

        try {
            FileWriter fw = new FileWriter(this.filePath.toString());
            for (int i = 0; i < t.size(); i++) {
                Task task = t.get(i);
                char eventType = task.eventType;
                char isDone = task.isDone ? '1' : '0';
                String desc = task.description;
                String[] frag = desc.split("\\(", 2);
                String formattedMsg = eventType + " | " + isDone + " | " + desc + "\n";
                fw.write(formattedMsg);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
