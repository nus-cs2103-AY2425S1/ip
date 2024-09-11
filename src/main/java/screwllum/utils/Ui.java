package screwllum.utils;

import screwllum.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    
    public String getInput() {
        return sc.nextLine();
    }
    
    public void showWelcome() {
        print("Pleased to meet you");
    }
    
    public void showError(String message) {
        print(message);
    }
    
    public void showMessage(List<String> tokens, List<Task> taskList) {
        switch (tokens.get(0)) {
        case "bye":
            print("It was my pleasure, good bye");
            System.exit(0);
        case "list":
            printTaskList(taskList);
            break;
        case "toggle":
            print("I have toggled the status of this task:");
            print(taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString());
            break;
        case "delete":
            print("I have deleted this task: ");
            print(taskList.get(Integer.parseInt(tokens.get(1)) - 1).toString());
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            print("I have added the following task:");
            print(taskList.get(taskList.size() - 1).toString());
            print("Now you have " + taskList.size() + " tasks" );
            break;
        case "find":
            List<Task> tempList = new ArrayList<>();
            String keywords = tokens.get(1);
            print("find " + keywords);
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                if (currentTask.getDesc().contains(keywords)) {
                    tempList.add(currentTask);
                }
            }
            printTaskList(tempList);
        }
    }
    
    private void print(String message) {
        System.out.println(message);
    }
    
    private void printTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) {
            print("There are no tasks for you!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                print(String.format("%s. %s", i + 1, taskList.get(i).toString()));
            }
        }
    }
}
