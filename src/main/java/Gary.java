import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Gary {
    public static void main(String[] args) {
        Scanner detector = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        String greeting = "Hello! I'm Gary\nWhat can I do for you?";
        System.out.println(greeting);

        String userInput;
        while (true) {
            try {
                userInput = detector.nextLine();

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.println("\t" + (i + 1) + ". " + task.toString());
                    }
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(4).trim());
                    if (index > taskList.size() || index <= 0) {
                        throw new Exception("Index needs to be within your task list size!!\nPlease try again!");
                    }
                    Task task = taskList.get(index - 1);
                    task.editStatus();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + task.toString());
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(6).trim());
                    if (index > taskList.size() || index <= 0) {
                        throw new Exception("Index needs to be within your task list size!!\nPlease try again!");
                    }
                    Task task = taskList.get(index - 1);
                    task.editStatus();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + task.toString());
                } else if (userInput.startsWith("todo")) {
                    Task toDo = new Todo(userInput.substring(4).trim());
                    if (toDo.description.length() == 0) {
                        throw new Exception("There is no name for the task.\nPlease " +
                                "input a new task with a name!!");
                    }
                    taskList.add(toDo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + toDo.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    String[] division = userInput.substring(8).trim().split("/by");
                    if (division[0].length() == 0) {
                        throw new Exception("There is no description for this deadline task.\nPlease " +
                                "input a new deadline task with a description!!");
                    }
                    if (division.length != 2) {
                        throw new Exception("There is no deadline provided for this task.\nPlease " +
                                "include your deadline!!");
                    }
                    Task deadline = new Deadline(division[0].trim(), division[1].trim());
                    taskList.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + deadline.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    String[] division = userInput.substring(5).trim().split("/from");
                    if (division[0].length() == 0) {
                        throw new Exception("There is no description for this event task.\nPlease " +
                                "input a new event task with a description!!");
                    }
                    if (division.length == 1 || division[1].trim().split("/to").length != 2) {
                        throw new Exception("There is no start and end date/time for your event task.\nPlease " +
                                "include them for your event too!!");
                    }
                    String[] timeDivision = division[1].trim().split("/to");
                    Task event = new Event(division[0].trim(), timeDivision[0].trim(), timeDivision[1].trim());
                    taskList.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else {
                    throw new Exception("Sorry! This is not a valid task!!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        detector.close();
    }
}

