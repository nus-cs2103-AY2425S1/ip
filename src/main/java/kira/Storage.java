package kira;

import tasks.List;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {

    File file;
    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    /**
     * Reads the previously saved file and
     * Returns the list of all tasks saved in the file previously
     * If no saved file detected, returns a new empty list
     *
     * @return List of tasks
     */
    public List retrieve() {
        try {
            Scanner s = new Scanner(this.file);
            List list = new List();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] strings = line.split("\\[");
                String priority = strings[1].split("]")[0];
                String type = strings[2].split("]")[0];
                String description = strings[3].split("] ")[1];
                String check = strings[3].split("] ")[0];
                Task task = Task.intepreteTask(description, type);
                if (Objects.equals(priority, "!")) {
                    task.setPriority(true);
                }
                if (Objects.equals(check, "X")) {
                    task.markAsDone();
                }
                list.addTaskToList(task);
            }
            return list;
        } catch (FileNotFoundException e) {     // user is new
            return new List();
        }
    }

    /**
     * Writes out the current list of tasks in a file
     *
     * @param list List of tasks
     * @throws IOException If file cannot be written
     */
    public void save(List list) throws IOException {
        FileWriter filewriter = new FileWriter(this.file, false);

        for (int i = 0; i < list.getTasks().size(); i++) {
            String line = list.getTasks().get(i).displayTask();
            filewriter.write(line);
        }
        filewriter.close();
    }
}
