package ratchet.storage;

import ratchet.task.DeadlineTask;
import ratchet.task.EventTask;
import ratchet.task.TaskList;
import ratchet.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private static final String PATH_TO_DIRECTORY = "data";
    private static final String PATH_TO_TASK_FILE = "data/ratchet.txt";

    public void loadTasks(TaskList tasks) {
        File file = new File(PATH_TO_TASK_FILE);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split("\\|");
                String type = info[0];
                switch (type) {
                case "T":
                    tasks.addTask(new TodoTask(info[1], Boolean.parseBoolean(info[2])));
                    break;
                case "D":
                    tasks.addTask(new DeadlineTask(info[1], Boolean.parseBoolean(info[2]),
                            LocalDate.parse(info[3])));
                    break;
                case "E":
                    tasks.addTask(new EventTask(info[1], Boolean.parseBoolean(info[2]), LocalDate.parse(info[3]),
                            LocalDate.parse(info[4])));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            initFile();
        }
    }

    public void saveTasks(TaskList tasks) throws IOException {

    }

    private void initFile() {
        try {
            new File(PATH_TO_DIRECTORY).mkdir();
            new File(PATH_TO_TASK_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
