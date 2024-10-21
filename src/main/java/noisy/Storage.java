package noisy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Storage {

    File file = new File("./data/duke.txt");
    public ArrayList<Task> loadTasks() {
        Parser parser = new Parser();
        ArrayList<Task> tasks = new ArrayList<>();
        try {

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;

            }Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitString = line.split("\\|");
                String taskType = splitString[0].trim();
                switch (taskType) {
                    case "T":
                        System.out.println("Adding task" + new Todo(splitString[1], Boolean.parseBoolean(splitString[2])));
                        tasks.add(new Todo(splitString[1], Boolean.parseBoolean(splitString[2])));
                        break;
                    case "D":
                        System.out.println("Adding task" + new Deadline(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3].trim())));
                        tasks.add(new Deadline(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3].trim())));
                        break;
                    case "E":
                        tasks.add(new Event(splitString[1], Boolean.parseBoolean(splitString[2]), parser.parseDate(splitString[3].trim()), parser.parseDate(splitString[4].trim())));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tasks);
        return tasks;
    }

    public void saveTasks(ArrayList<Task> taskList) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : taskList) {
                writer.write(task.formatTask()); // Write each task in the desired format
                writer.newLine();
                System.out.println(task.formatTask());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}