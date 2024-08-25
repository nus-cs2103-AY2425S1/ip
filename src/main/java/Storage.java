import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
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

    public ArrayList<Task> loadTasks() throws DukeException {

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
                        String startDate = frags[3] + " " + frags[4];
                        String endDate = frags[5] + " " + frags[6];
                        Task task = new Event(frags[2], startDate, endDate);
                        if (frags[1].equals("1")) {
                            task.markTask();
                        }
                        t.add(task);
                    } else if (frags[0].equals("D")){
                        String endDate = frags[5] + " " + frags[6];
                        Task task = new Deadline(frags[2], endDate);
                        if (frags[1].equals("1")) {
                            task.markTask();
                        }
                        t.add(task);
                    }
                }

                return t;
            } catch(IOException e) {
                throw new DukeException("Error in loading tasks!");
            }
        }

        return new ArrayList<>();
    }

    public void saveTask(TaskList tasklist) throws DukeException {

        try {
            FileWriter fw = new FileWriter(this.filePath.toString());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            for (int i = 0; i < tasklist.size(); i++) {
                Task task = tasklist.getTask(i);
                char eventType = task.eventType;
                char isDone = task.isDone ? '1' : '0';
                String desc = task.description;
                String startDate = task.startDate.format(dateFormatter);
                String startTime = task.startTime;
                String endDate = task.endDate.format(dateFormatter);
                String endTime = task.endTime;
                String formattedMsg = eventType + " | " + isDone + " | " + desc +
                        " | " + startDate + " | " + startTime +
                        " | " + endDate + " | " + endTime + "\n";
                fw.write(formattedMsg);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error in saving tasks!");
        }
    }
}
