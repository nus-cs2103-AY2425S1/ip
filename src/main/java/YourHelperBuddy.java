import java.util.Scanner;
import java.util.*;
public class YourHelperBuddy {
    public static void main(String[] args) {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        // User enters the tasks until he types bye
        System.out.println("Enter your task: ");
        String taskDescription = myObj.nextLine();
        while (!taskDescription.equals("bye")) {
            if (taskDescription.equals("list")) {
                System.out.println("________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    String taskLabel = String.valueOf(i + 1);
                    Task currentTask = taskList.get(i);
                    System.out.println(" " + taskLabel + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                }
                System.out.println("________________________________________________");
            }
            else if (taskDescription.startsWith("mark")) {
                int lastIndex = taskDescription.length() - 1;
                int taskLabel = Character.getNumericValue(taskDescription.charAt(lastIndex));
                Task currentTask = taskList.get(taskLabel - 1);
                System.out.println("________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                currentTask.markDone();
                System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                System.out.println("________________________________________________");
            }
            else if (taskDescription.startsWith("unmark")) {
                int lastIndex = taskDescription.length() - 1;
                int taskLabel = Character.getNumericValue(taskDescription.charAt(lastIndex));
                Task currentTask = taskList.get(taskLabel - 1);
                System.out.println("________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                currentTask.markUndone();
                System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                System.out.println("________________________________________________");
            }
            else {
                System.out.println("________________________________________________");
                Task newTask = new Task(taskDescription);
                taskList.add(newTask);
                System.out.println("  added: " + taskDescription);
                System.out.println("________________________________________________");
            }
            System.out.println("Enter your task: ");
            taskDescription = myObj.nextLine();
        }
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }
}
