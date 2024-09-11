package screwllum.utils;

import screwllum.exception.IllegalFileFormatException;
import screwllum.tasks.Deadline;
import screwllum.tasks.Event;
import screwllum.tasks.Task;
import screwllum.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(List<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public List<Task> load() throws IOException, IllegalFileFormatException {
        File file = new File(filePath);
        List<Task> taskList = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(file);
            Task task = null;
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                task = parseTask(nextLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(filePath, true);
            fw.close();
        }
        return taskList;
    }

    public Task parseTask(String nextLine) throws IllegalFileFormatException {
        String[] taskData = nextLine.split("_");
        Task task = null;
        switch (taskData[0]) {
        case "T":
            task = new ToDo(taskData[2]);
            break;
        case "D":
            task = new Deadline(taskData[2], taskData[3]);
            break;
        case "E":
            task = new Event(taskData[2], taskData[3], taskData[4]);
            break;
        default:
            throw new IllegalFileFormatException(nextLine);
        }
        if (taskData[1].equals("1")) {
            task.toggleStatus();
        }
        return task;
    }
}
