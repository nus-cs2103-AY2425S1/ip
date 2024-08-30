package storage;

import parser.Parser;
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataSaved = new File(FILE_PATH);
        Scanner s = new Scanner(dataSaved);

        while (s.hasNext()) {
            String[] dataArr = s.nextLine().split(" \\| ");
            Task newTask = Parser.parseSavedData(dataArr);
            taskList.add(newTask);
        }
        return taskList;
    }

    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        String separation = " | ";
        for (Task task : taskList) {
            fw.write(task.toSavedFormat(separation) + "\n");
        }
    }
}
