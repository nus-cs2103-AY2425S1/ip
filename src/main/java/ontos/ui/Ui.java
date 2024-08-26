package ontos.ui;

import java.util.Scanner;

import ontos.task.Task;
import ontos.task.TaskList;

public class Ui {
    private static String hello = " Hello! I'm Ontos \n What can I do for you?";
    private static String line = "____________________________________________________________";
    private static String bye = " Bye. Hope to see you again soon!";
    private static String listPrompt = " Here are the tasks in your list:\n";
    private static String completeTaskPrompt = " Nice! I've marked this task as done:";
    private static String uncompleteTaskPrompt = " OK, I've marked this task as not done yet:";
    private static String taskAdded = " Got it. I've added this task:";
    
    private Scanner inputs;

    public Ui() {
        this.inputs = new Scanner(System.in);
    }

    public String readInput() {
        return inputs.nextLine().trim();
    }

    public String showLine() {
        System.out.println(line);
        return line;
    }

    public String greet() {
        System.out.println(hello);
        return hello;
    }

    public String goodbye() {
        inputs.close();
        System.out.println(bye);
        return bye;
    } 

    public String list(TaskList taskList) {
        String list = listPrompt
                + taskList.toString();
        System.out.println(list);
        return list;
    }

    public String taskDoesNotExist() {
        String output = " I'm sorry, but this task doesn't exist.";
        System.out.println(output);
        return output;
    }

    public String incorrectUse(String command) {
        String output = "The correct usage of '" + command +  "' is: " 
                + command +  " n, where n is a natural number (ℕ).";
        System.out.println(output);
        return output;
    }

    public String mark(TaskList taskList, int index) {
        String output = completeTaskPrompt 
                + taskList.getTaskString(index);
        System.out.println(output);
        return output;
    }

    public String unmark(TaskList taskList, int index) {
        String output = uncompleteTaskPrompt 
                + taskList.getTaskString(index);
        System.out.println(output);
        return output;
    }

    public String delete(Task task) {
        String output = " Noted. I've removed this task:\n" 
                + " " + task.toString();
        System.out.println(output);
        return output;
    }

    public String taskAdded(Task task, TaskList tasks) {
        String output = taskAdded 
                + " " + task.toString() + "\n" 
                + " Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(output);
        return output;
    }

    public String emptyDescription(String type) {
        String output = " OOPS!!! The description of a " + type + " cannot be empty.";
        System.out.println(output);
        return output;
    }

    public String missingDeadline() {
        String output = " OOPS!!! A deadline task requires a deadline.";
        System.out.println(output);
        return output;
    }

    public String missingStartAndEnd() {
        String output = " OOPS!!! An event task requires a start and end time.";
        System.out.println(output);
        return output;
    }

    public String badInput() {
        String output = " OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(output);
        return output;
    }

    public String findOutput(String filteredList) {
        String output = "Here are the matching tasks in your list:\n"
                + filteredList;
        System.out.println(output);
        return output;
    }
}
