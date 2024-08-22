import java.util.ArrayList;
import java.util.Scanner;

public class Revir {
    public static void main(String[] args) {
        String name = "Revir";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> userInputList =  new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // Print the user input list
                System.out.println("List:");
                for (Task userInput : userInputList) {
                    System.out.println(userInput.toString());
                }
            } else if (input.startsWith("mark ")) {
                // Mark a task as completed
                String taskIndexStr = input.substring(5);
                try {
                    int taskIndex = Integer.parseInt(taskIndexStr);
                    if (taskIndex >= 1 && taskIndex <= userInputList.size()) {
                        Task task = userInputList.get(taskIndex - 1);
                        task.setCompleted(true);
                        System.out.println("Task marked as completed: " + task.toString());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("unmark ")) {
                // Unmark a completed task
                String taskIndexStr = input.substring(7);
                try {
                    int taskIndex = Integer.parseInt(taskIndexStr);
                    if (taskIndex >= 1 && taskIndex <= userInputList.size()) {
                        Task task = userInputList.get(taskIndex - 1);
                        task.setCompleted(false);
                        System.out.println("Task unmarked as completed: \n" + task.toString());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("todo ")) {
                // Add a todo task
                String taskDescription = input.substring(5);
                userInputList.add(new Todo(taskDescription));
                System.out.println("Todo task added: " + taskDescription);
            } else if (input.startsWith("deadline ")) {
                // Add a deadline task
                String taskDetails = input.substring(9);
                String[] taskInfo = taskDetails.split(" /by ");
                if (taskInfo.length == 2) {
                    String taskDescription = taskInfo[0];
                    String deadline = taskInfo[1];
                    userInputList.add(new Deadline(taskDescription, deadline));
                    System.out.println("Deadline task added: " + taskDescription + " (by: " + deadline + ")");
                } else {
                    System.out.println("Invalid deadline task format.");
                }
            } else if (input.startsWith("event ")) {
                // Add an event task
                String taskDetails = input.substring(6);
                String[] taskInfo = taskDetails.split(" /from ");
                if (taskInfo.length == 2) {
                    String taskDescription = taskInfo[0];
                    String startDate = taskInfo[1].split(" /to ")[0];
                    String endDate = taskInfo[1].split(" /to ")[1];
                    userInputList.add(new Event(taskDescription, startDate, endDate));
                    System.out.println("Event task added: " + taskDescription + " (from: " + startDate + " to: " + endDate + ")");
                } else {
                    System.out.println("Invalid event task format.");
                }
            } else {
                System.out.println("Invalid command.");
            }
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
