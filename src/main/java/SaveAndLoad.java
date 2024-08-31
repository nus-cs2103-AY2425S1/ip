package src.main.java;

import src.main.java.KillJoy;
import src.main.java.Task;
import src.main.java.ProcessTasks;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveAndLoad {
    private KillJoy kj;
    private ProcessTasks processTasks;

    public SaveAndLoad(KillJoy kj, ProcessTasks processTasks) {
        this.kj = kj;
        this.processTasks = processTasks;
    }

    public void loadTasks() {
        File file = new File("KillJoy.txt");
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String data = scan.nextLine();
                processTasks.createTasks(data);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter("KillJoy.txt");
                fw.write("");
                fw.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("KillJoy.txt");
            String currentTasks = "";
            for (Task task : tasks) {
                currentTasks += task.getTaskInfo();
            }
            fw.write(currentTasks);
            fw.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
