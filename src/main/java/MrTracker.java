package main.java;
import main.java.Task;
import main.java.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class MrTracker {

    public static void printLine() {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            String prefix = currTask.isDone ? "[X] " : "[ ] ";
            System.out.println(i+1 + "." + prefix + taskList.get(i).name);
        }
    }

    public static void addTask (ArrayList<Task> taskList, String taskName) {
        ToDo newTask = new ToDo(taskName);
        taskList.add(newTask);
        System.out.println("added: " + taskName);
    }

    public static int checkMarkIndex(String command) {
        // start at 5 because thats where the number should be
        String trimmed = command.substring(5);
        try {
            int res = Integer.parseInt(trimmed);
            return res;
        } catch (Exception ex) {
            // if the string after "mark " are not numbers, return an invalid index
            return Integer.MAX_VALUE;
        }
    }

    public static void markTask()

    public static void main(String[] args) {
        String name = "Mr Tracker";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        MrTracker.printLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        MrTracker.printLine();
        while (true) {
            String input = sc.nextLine();
            MrTracker.printLine();
            String lowerInput = input.toLowerCase();
            if (lowerInput.equals("bye")) {
                sc.close();
                break;
            } else if (lowerInput.equals("list")) {
                MrTracker.printTaskList(taskList);
            } else {
                if (input.startsWith("mark ")) {
                    int index = MrTracker.checkMarkIndex(input);
                    // if index is outside of acceptable range,
                    if (index == Integer.MAX_VALUE) {
                        MrTracker.addTask(taskList, input);
                    } else if (index < 1 || index > taskList.size()) {
                        System.out.println("task " + index + " does not exist");
                    } else {
                        index--;
                        Task curr = taskList.get(index);
                        curr.mark();
                        System.out.println("task " + ++index + " is marked!");
                    }
                } else {
                    MrTracker.addTask(taskList, input);
                }

            }
            MrTracker.printLine();
        }
        System.out.println("Bye. Hope to see you again soon! \n");
        MrTracker.printLine();
    }
}
