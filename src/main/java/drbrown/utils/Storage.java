package drbrown.utils;

import drbrown.task.Deadline;
import drbrown.task.Event;
import drbrown.task.Task;
import drbrown.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Boolean.parseBoolean;

public class Storage {
    private final String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
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
                        taskList.add(new Deadline(parseBoolean(sentenceSplit[1]), sentenceSplit[2], LocalDateTime.parse(sentenceSplit[3], DATE_TIME_FORMATTER)));
                        break;
                    case "E":
                        taskList.add(new Event(parseBoolean(sentenceSplit[1]), sentenceSplit[2], LocalDateTime.parse(sentenceSplit[3], DATE_TIME_FORMATTER), LocalDateTime.parse(sentenceSplit[4], DATE_TIME_FORMATTER)));
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

    public void update (TaskList tasks) {
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileWriter fw = new FileWriter (this.filePath);
            for (Task task : tasks.getList()) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Seems like you messed up the file path somehow!");
        }
    }

}
