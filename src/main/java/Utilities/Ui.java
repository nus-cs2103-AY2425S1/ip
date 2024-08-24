package Utilities;

import Task.Task;

import java.util.ArrayList;

public class Ui {
    public void printTask(ArrayList<Task> tasks) {
        System.out.println("_____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("_____________________________________");
    }

    public void noTask(){
        System.out.println("_____________________________________");
        System.out.println("No Task.Task Found");
        System.out.println("_____________________________________");
    }

    public void markTask(ArrayList<Task> tasks, int index, Boolean isToMark){
        System.out.println("_____________________________________");
        if (isToMark){
            System.out.println("Nice! I've marked this task as done:");
        }else{
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + tasks.get(index - 1));
        System.out.println("_____________________________________");
    }

    public void alterTask(ArrayList<Task> tasks, Task task, Boolean isAdd){
        System.out.println("_____________________________________");
        if (isAdd){
            System.out.println("Got it. I've added this task:");
        }else{
            System.out.println("Noted. I've removed this task:");
        }
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________________");
    }

    public void defaultMsg(){
        System.out.println("_____________________________________");
        System.out.println("I'm sorry, but I don't know what that means");
        System.out.println("_____________________________________");
    }

    public void endMsg(){
        System.out.println("_____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________________");
    }

    public void greetMsg(String logo){
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________");
    }

    public void blankMsg(String type){
        System.out.println("_____________________________________");
        switch (type){
        case "todo":
            System.out.println("The description of a " + type + " cannot be empty.");
            break;
        case "number":
            System.out.println("The task number cannot be empty.");
            break;
        case "keyword":
            System.out.println("The keyword to look for cannot be empty.");
            break;
        default:
            System.out.println("The description and date of a " + type + " cannot be empty.");
            break;
        }
        System.out.println("_____________________________________");
    }

    public void invalidDateMsg(){
        System.out.println("_____________________________________");
        System.out.println("Invalid date and time format. Please use the format 'd/M/yyyy HHmm'.");
        System.out.println("_____________________________________");
    }

    /**
     * Displays the tasks that match the search keyword.
     *
     * @param foundTasks The list of tasks that match the search keyword.
     */
    public void foundTask(ArrayList<String> foundTasks){
        System.out.println("_____________________________________");

        if (foundTasks.isEmpty()){
            System.out.println("No matching tasks found.");
        }else{
            System.out.println("Here are the matching tasks in your list:");
            for (String foundTask : foundTasks) {
                System.out.println(foundTask);
            }
        }
        System.out.println("_____________________________________");
    }
}
