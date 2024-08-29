package processes;

import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String dirPath;
    private String filePath;

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    public void loadData(ArrayList<Task> taskList) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                boolean doesDirExists = dir.mkdir();
                if (!doesDirExists) {
                    System.out.println("Error: Directory " + dirPath + " could not be created" +
                            " to store your tasks!");
                    return;
                }
            }

            File file = new File(filePath);
            if (!file.exists()) {
                boolean doesFileExists = file.createNewFile();
                if (doesFileExists) {
                    System.out.println("File created to store your tasks!");
                } else {
                    System.out.println("Error: The file " + filePath + " could not be " +
                            "created to store your tasks!");
                }

                return;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String type = input.substring(0, 1);
                String[] args = input.substring(2).trim().split("\\|");

                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new ToDo(args);
                    break;
                case "D":
                    newTask = new DeadLine(args);
                    break;
                case "E":
                    newTask = new Event(args);
                    break;
                }
                taskList.add(newTask);
            }
            sc.close();
        } catch (IOException ex) {
            System.out.println("The file " + filePath + " could not be read from");
        }
    }

    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter file = new FileWriter(filePath);
            for (Task item : taskList) {
                file.write(item.toSave());
                file.write("\n");
            }
            file.close();
        } catch (IOException ex) {
            System.out.println("Sorry, your tasks could not be saved");
        }
    }
}
