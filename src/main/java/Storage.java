import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Boolean.parseBoolean;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DrBrownException {
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
                        taskList.add(new Deadline(parseBoolean(sentenceSplit[1]), sentenceSplit[2], LocalDate.parse(sentenceSplit[3])));
                        break;
                    case "E":
                        taskList.add(new Event(parseBoolean(sentenceSplit[1]), sentenceSplit[2], LocalDate.parse(sentenceSplit[3]), LocalDate.parse(sentenceSplit[4])));
                        break;
                    default:
                        throw new DrBrownException("The file provided might be corrupted since it does not follow the specified format.");
                }
            }
        } catch (IOException e) {
            throw new DrBrownException("Oops! It seems like this is your first time. No worries, I've created a brand new file to get you started.");
        }
        return taskList;
    }

    public void update (Task task) throws DrBrownException {
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
            throw new DrBrownException("Seems like you messed up the file path somehow!");
        }
    }

}
