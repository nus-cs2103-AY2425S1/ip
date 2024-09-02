import java.util.ArrayList;
import java.util.Scanner;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";
    public static void printwithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        ArrayList<Task> taskList = taskManager.loadTasks();
        String welcomeMsg =
                " Hello! I'm Bottle\n" +
                " What can I do for you?";

        String byeMsg =" Bye. Hope to see you again soon!";
        printwithBreak(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    printwithBreak(byeMsg);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(lineBreak);
                    for (int i = 0; i < taskList.size(); i++) {
                        int idx = i + 1;
                        System.out.println(idx + ". " + taskList.get(i));
                    }
                    System.out.println(lineBreak);
                } else if (input.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task task = taskList.get(taskIndex);
                        task.mark();
                        String msg = "Nice! I've marked this task as done:\n";
                        printwithBreak(msg + task);
                    } else {
                        throw new IndexOutOfBoundsException("Wrong Task Number");
                    }
                } else if (input.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task task = taskList.get(taskIndex);
                        task.unMark();
                        String msg = "OK, I've marked this task as not done yet:\n";
                        printwithBreak(msg + task);
                    } else {
                        throw new IndexOutOfBoundsException("Wrong Task Number");
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("Description cannot be empty");
                    }
                    taskList.add(new Todo(description));
                    System.out.print(lineBreak);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + taskList.get(taskList.size() - 1).toString());
                    System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(lineBreak);
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Incorrect deadline arguments count");
                    }
                    String description = parts[0];
                    String by = parts[1];
                    taskList.add(new Deadline(description, by));
                    System.out.println(lineBreak + " Got it. I've added this task: ");
                    System.out.println("   " + taskList.get(taskList.size() - 1).toString());
                    System.out.println(" Now you have " + taskList.size() + " tasks in the list." + lineBreak);
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Incorrect event arguments count");
                    }
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    taskList.add(new Event(description, from, to));
                    System.out.println(lineBreak + " Got it. I've added this task: " + taskList.get(taskList.size() - 1).toString());
                    System.out.println(" Now you have " + taskList.size() + " tasks in the list." + lineBreak);
                } else {
                    throw new RuntimeException("OOPS!!! Something went wrong.");
                }
            } catch (RuntimeException e) {
                printwithBreak(e.getMessage());
            } finally {
                taskManager.saveTasks(taskList);
            }
        }
    }
}
