import java.util.Scanner;
import java.util.*;
public class YourHelperBuddy {
    public static void main(String[] args) {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        // User enters the tasks until he types bye
        System.out.println("Enter your task: ");
        String userTask = myObj.nextLine();
        while (!userTask.equals("bye")) {
            if (userTask.equals("list")) {
                System.out.println("________________________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    String taskLabel = String.valueOf(i + 1);
                    System.out.println(" " + taskLabel + ". " + taskList.get(i));
                }
                System.out.println("________________________________________________");
                System.out.println("Enter your task: ");
                userTask = myObj.nextLine();
            }
            else {
                System.out.println("________________________________________________");
                taskList.add(userTask);
                System.out.println("  added: " + userTask);
                System.out.println("________________________________________________");
                System.out.println("Enter your task: ");
                userTask = myObj.nextLine();
            }
        }
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }
}
