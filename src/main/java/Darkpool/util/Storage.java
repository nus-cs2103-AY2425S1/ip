package Darkpool.util;

import Darkpool.Task.Deadline;
import Darkpool.Task.Event;
import Darkpool.Task.Task;
import Darkpool.Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private File fileAndDirCheck() throws DarkpoolException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DarkpoolException(e.getMessage());
            }
        }
        return dataFile;
    }

    public ArrayList<Task> loadData() throws DarkpoolException {
        ArrayList<Task> taskList = new ArrayList<>();
        String curTask;
        File dataFile = fileAndDirCheck();
        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DarkpoolException(e.getMessage());
        }
        while (scanner.hasNext()) {
            curTask = scanner.nextLine();
            taskList.add(parseTask(curTask));
        }
        scanner.close();
        return taskList;
    }

    public void saveData(TaskList taskList) throws DarkpoolException {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write(taskList.toFileString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DarkpoolException(e.getMessage());
        }

    }

    private Task parseTask(String task) throws DarkpoolException {
        String[] taskParts = task.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");
        String description = taskParts[2];
        String from, to, by;

        switch (type) {
            case "E" -> {
                from = taskParts[3];
                to = taskParts[4];
                return new Event(description, from, to, isDone);
            }
            case "D" -> {
                by = taskParts[3];
                return new Deadline(description, by, isDone);
            }
            case "T" -> {
                return new Todo(description, isDone);
            }

            default -> {
                System.out.println("Unknown task type: " + type);
                return null;
            }
        }
    }

}
