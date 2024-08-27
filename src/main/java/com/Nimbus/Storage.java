package com.nimbus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String path) {
        this.filePath = path;
        checkSavedFile();
    }

    // create all the necessary directory and file if the file doesn't exists
    private void checkSavedFile() {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occur when creating file.");
            }
        }
    }

    public static Task getTaskFromSavedCommand(String command) {
        String[] arr = command.split("\\|");
        boolean isDone = arr[1].equals("1");
        return switch (arr[0]) {
            case "T" -> new Todo(arr[2], isDone);
            case "D" -> new Deadline(arr[2], isDone, arr[3]);
            case "E" -> new Event(arr[2], isDone, arr[3], arr[4]);
            default -> null;
        };
    }

    public TaskList load() {
        TaskList ans = new TaskList();
        checkSavedFile();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = getTaskFromSavedCommand(sc.nextLine());
                if (task != null)
                    ans.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find saved data file");
        }
        return ans;
    }

    public boolean writeTaskToFile(Task task) {
        try (FileWriter file = new FileWriter(filePath, true)){
            file.write(task.toFileFormat());
            file.write(System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
        return false;
    }

    public boolean removeTaskFromFileByIndex(int index, int size) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<String> arr = new ArrayList<String>();
            for (int i = 0; i < size && sc.hasNext(); ++i) {
                String nxt = sc.nextLine();
                if (i == index)
                    continue;
                arr.add(nxt);
            }
            sc.close();
            FileWriter fw = new FileWriter(filePath);
            for (String s : arr) {
                fw.write(s);
                fw.write(System.lineSeparator());
            }
            fw.close();

            return true;
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
        return false;
    }

    public void writeTasksToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); ++i) {
                fw.write(tasks.get(i).toFileFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
    }
}
