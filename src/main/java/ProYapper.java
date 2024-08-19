import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ProYapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greeting = "Hello! I am Pro Yapper!\nWhat can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";
        String errorMessage = "Please type in a command!\n" +
                "list: shows a list of your tasks\n" +
                "mark <task number>: mark the task in your list as done\n" +
                "unmark <task number>: mark the task in your list as not done\n" +
                "todo <task>: add task without any date/time attached to it\n" +
                "deadline <task> /by <when>: add task that needs to be done before a specific date/time\n" +
                "event <task> /from <when> /to <when>: add a task that starts at a specific date/time and ends at a specific date/time";

        List<Task> taskList = new ArrayList<>();

        System.out.println(greeting);
        while (true) {
            String userInput = scanner.nextLine();
            // Bye command
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(goodbye);
                break;

                // List command
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int lstNum = i + 1;
                    Task next = taskList.get(i);
                    System.out.println(lstNum + ". " + next.toString());
                }

                // Mark command
            } else if (userInput.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                String[] parts = userInput.split(" ");
                int lstNum = Integer.parseInt(parts[1]);
                Task marked = taskList.get(lstNum - 1);
                marked.markAsDone();
                System.out.println(marked.toString());

                // Unmark command
            } else if (userInput.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                String[] parts = userInput.split(" ");
                int lstNum = Integer.parseInt(parts[1]);
                Task unmarked = taskList.get(lstNum - 1);
                unmarked.markAsUndone();
                System.out.println(unmarked.toString());

                // To Do command
            } else if (userInput.startsWith("todo")) {
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split(" ", 2);
                String taskName = parts.length > 1 ? parts[1] : "";
                Task newTask = new ToDo(taskName);
                taskList.add(newTask);
                int numTasks = taskList.size();
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list");

                // Deadline
            } else if (userInput.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split("/by", 2);
                String taskName = parts[0].replaceFirst("deadline", "").trim();
                String dueWhen = parts.length > 1 ? parts[1].trim() : "";
                Task newTask = new Deadline(taskName, dueWhen);
                taskList.add(newTask);
                int numTasks = taskList.size();
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list");

                // Event
            } else if (userInput.startsWith("event")) {
                System.out.println("Got it. I've added this task:");
                String[] partsFrom = userInput.split("/from", 2);
                String taskName = partsFrom[0].replaceFirst("event", "").trim();
                String[] partsTo = partsFrom[1].split("/to", 2);
                String startWhen = partsTo[0].trim();
                String endWhen = partsTo.length > 1 ? partsTo[1].trim() : "";
                Task newTask = new Event(taskName, startWhen, endWhen);
                taskList.add(newTask);
                int numTasks = taskList.size();
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + numTasks + " tasks in the list");
            } else {
                System.out.println(errorMessage);
            }
        }
        scanner.close();
    }
}


