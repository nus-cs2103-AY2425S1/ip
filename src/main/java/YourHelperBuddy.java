import java.util.*;

public class YourHelperBuddy {
    public static void main(String[] args) {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            System.out.println("Enter your task: ");
            if (!myObj.hasNextLine()) {
                break;
            }
            String taskDescription = myObj.nextLine();
            if (taskDescription.equals("bye")) {
                break;
            }
            else if (taskDescription.equals("list")) {
                System.out.println("________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task currentTask = taskList.get(i);
                    System.out.println(" " + (i + 1) + ". " + currentTask);
                }
                System.out.println("________________________________________________");
            }
            else if (taskDescription.startsWith("mark")) {
                try {
                    int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                    Task currentTask = taskList.get(taskLabel);
                    System.out.println("________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.markDone();
                    System.out.println("  " + currentTask);
                    System.out.println("________________________________________________");
                }
                catch(Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! There seems to be an issue with the task number you provided.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("unmark")) {
                try {
                    int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                    Task currentTask = taskList.get(taskLabel);
                    System.out.println("________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    currentTask.markUndone();
                    System.out.println("  [" + currentTask);
                    System.out.println("________________________________________________");
                }
                catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! There seems to be an issue with the task number you provided.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("todo")) {
                String todoDescription = taskDescription.substring(5).trim();
                if (todoDescription.isEmpty()) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description cannot be empty.");
                    System.out.println("________________________________________________");
                }
                else {
                    Task newTask = new ToDo(todoDescription);
                    taskList.add(newTask);
                    System.out.println("________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("deadline")) {
                try {
                    String[] parts = taskDescription.split(" /by ");
                    String deadlineDescription = parts[0].substring(9).trim();
                    String deadlineBy = parts[1].trim();
                    if (deadlineDescription.isEmpty() || deadlineBy.isEmpty()) {
                        System.out.println("________________________________________________");
                        System.out.println("Sorry! The description or the deadline cannot be empty.");
                        System.out.println("________________________________________________");
                    } else {
                        Task newTask = new Deadline(deadlineDescription, deadlineBy);
                        taskList.add(newTask);
                        System.out.println("________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! The deadline command is not formatted correctly.");
                    System.out.println("Please use 'deadline <description> /by <date/time>'.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("event")) {
                try {
                    String[] parts = taskDescription.split(" /from ");
                    String[] subParts = parts[1].split(" /to ");
                    String eventDescription = parts[0].substring(6).trim();
                    String eventFrom = subParts[0].trim();
                    String eventTo = subParts[1].trim();
                    if (eventDescription.isEmpty() || eventFrom.isEmpty() || eventTo.isEmpty()) {
                        System.out.println("________________________________________________");
                        System.out.println("Sorry! The description or the timing cannot be empty.");
                        System.out.println("________________________________________________");
                    }
                    else {
                        Task newTask = new Event(eventDescription, eventFrom, eventTo);
                        taskList.add(newTask);
                        System.out.println("________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("________________________________________________");
                    }
                }
                catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The event command is not formatted correctly.");
                    System.out.println("Please use 'event <description> /from <start> /to <end>'.");
                    System.out.println("________________________________________________");
                }
            }
            else {
                System.out.println("________________________________________________");
                System.out.println("Invalid command. Please use 'todo', 'deadline', or 'event'.");
                System.out.println("________________________________________________");
            }
        }
        myObj.close();
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }
}
