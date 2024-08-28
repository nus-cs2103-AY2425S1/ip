package com.example.YourHelperBuddy;

import java.io.*;

public class Main {
    private static String home = System.getProperty("user.home");

    private static String directoryPath = home + "/Documents/";

    private static String filePath = directoryPath + "TaskInfo.txt";

    public static void main(String[] args) {
        Ui screen = new Ui();
        screen.showWelcomeMessage();
        TaskList taskList = new TaskList();

        File directory = new File(directoryPath);
        if (!directory.exists()){
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            }
            else {
                System.out.println("Failed to create directory: " + directoryPath);
                return;
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                }
                else {
                    System.out.println("Failed to create file: " + filePath);
                    return;
                }
            }
            catch (IOException e) {
                System.out.println("________________________________________________");
                System.out.println("An error occurred while creating the file.");
                System.out.println("________________________________________________");
                return;
            }
        }
        Storage storage = new Storage(filePath);
        storage.loadTasks(taskList.getTasks());
        while (true) {
            String taskDescription = screen.readUserInput();
            if (taskDescription.equals("")) {
                break;
            }
            else if (taskDescription.equals("bye")) {
                break;
            }
            else if (taskDescription.equals("list")) {
                screen.showTaskList(taskList);
            }
            else if (taskDescription.startsWith("delete")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task removedTask = taskList.deleteTask(taskLabel);
                screen.showTaskRemoved(removedTask, taskList);
            }
            else if (taskDescription.startsWith("mark")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task currentTask = taskList.getTask(taskLabel);
                screen.showTaskMarked(currentTask);
            }
            else if (taskDescription.startsWith("unmark")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task currentTask = taskList.getTask(taskLabel);
                screen.showTaskUnmarked(currentTask);
            }
            else {
                Parser parser = new Parser();
                Task objective = parser.parseCommand(taskDescription);
                if (objective != null) {
                    taskList.addTask(objective);
                    screen.showTaskAdded(objective, taskList.size());
                }
            }
        }
        storage.saveTasks(taskList.getTasks());
        screen.close();
        screen.showGoodbyeMessage();
    }
}
