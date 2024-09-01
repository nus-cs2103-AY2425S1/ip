import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Boolean.parseBoolean;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] sentenceSplit = s.nextLine().split(" \\| ");
                switch (sentenceSplit[0]) {
                    case "T":
                        taskList.add(new Todo(parseBoolean(sentenceSplit[1]), sentenceSplit[2]));
                        break;
                    case "D":
                        taskList.add(new Deadline(parseBoolean(sentenceSplit[1]), sentenceSplit[2], sentenceSplit[3]));
                        break;
                    case "E":
                        taskList.add(new Event(parseBoolean(sentenceSplit[1]), sentenceSplit[2], sentenceSplit[3], sentenceSplit[4]));
                        break;
                    default:
                        throw new DrBrownException("The File is corrupted.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Seems like this is your first time");
        } catch (DrBrownException | IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void update(Task task) {
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileWriter fw = new FileWriter (this.filePath, true);
            fw.write(task.toFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Seems like you messed up the file path somehow!");
        }
    }

}
