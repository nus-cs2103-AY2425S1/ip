package ui;

import task.Task;
import task.TaskList;
import prince.Prince;


import java.util.ArrayList;

public class Ui {
    public static String listDisplay(ArrayList<Task> list) {
        int length = list.size();
        // use String Builder to ensure that the string can be created on another line
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + list.get(i).printTask()).append("\n");
        }

        return "Here are the tasks in your list:\n" + sb.toString();
    }

    public static String taskAddDescription(Task task) {
        return "Got it. I've added this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + TaskList.getList().size() + " tasks in the list";
    }

    public static String taskDelDescription(int num, Task task){
        return "Noted. I've removed this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + TaskList.getList().size() + " tasks in the list";
    }

    public void terminationMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
