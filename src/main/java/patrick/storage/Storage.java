package patrick.storage;

import patrick.tasklist.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static ArrayList<Task> list = new ArrayList<>();
    static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }
    public ArrayList<Task> load() {
        try {
            readTasks(filePath);
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            try {
                newFile.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("File created!");
        }
        return list;
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static void addList(Task task) {
        list.add(task);
    }

    public static void deleteItem(int index) {
        list.remove(index - 1);
    }

    private static void readTasks(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        Task currTask;
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (taskString.startsWith("T")) {
                String taskDescription = taskString.substring(7);
                currTask = new ToDo(taskDescription);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("D")) {
                String taskDescription = taskString.substring(7);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String deadline = taskString.substring(7).replace(taskDescription, "").replace(" | ", "");
                currTask = new Deadline(taskDescription, deadline);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("E")) {
                String taskDescription = taskString.substring(7);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String tempFrom = taskString.substring(7).replace(taskDescription + " ", "").substring(2);
                String to = tempFrom.substring(tempFrom.indexOf("-") + 1);
                String from = tempFrom.replace("-" + to, "");
                currTask = new Event(taskDescription, from, to);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            }
        }
    }

    public static void appendToFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(text);
        fileWriter.close();
    }

    public static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                String temp = list.get(i).toString();
                fileWriter.write(temp);
            } else {
                String temp = list.get(i).toString();
                fileWriter.write(temp + "\n");
            }
        }
        fileWriter.close();
    }
}
