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
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    System.out.println("mark WHAT???");
                } else {
                    try {
                        int lstNum = Integer.parseInt(parts[1]);
                        if (lstNum < 1 || lstNum > taskList.size()) {
                            System.out.println("OI WRONG NUMBER.");
                        } else {
                            Task marked = taskList.get(lstNum - 1);
                            marked.markAsDone();

                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(marked.toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("THIS ONE NOT INTEGER!!!");
                    }
                }

                // Unmark command
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                if (parts.length < 2) {
                    System.out.println("unmark WHAT???");
                } else {
                    try {
                        int lstNum = Integer.parseInt(parts[1]);
                        if (lstNum < 1 || lstNum > taskList.size()) {
                            System.out.println("OI WRONG NUMBER.");
                        } else {
                            Task unmarked = taskList.get(lstNum - 1);
                            unmarked.markAsUndone();

                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(unmarked.toString());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("THIS ONE NOT INTEGER!!!");
                    }
                }

                // To Do command
            } else if (userInput.startsWith("todo")) {
                String[] parts = userInput.split(" ", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("todo WHAT????");
                } else {
                    String taskName = parts[1].trim();
                    Task newTask = new ToDo(taskName);
                    taskList.add(newTask);
                    int numTasks = taskList.size();

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask.toString());
                    System.out.println("Now you have " + numTasks + " tasks in the list");
                }

                // Deadline
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by", 2);
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    System.out.println("WHEN IS THE DEADLINE???");
                } else {
                    String taskName = parts[0].replaceFirst("deadline", "").trim();
                    if (taskName.isEmpty()) {
                        System.out.println("deadline WHAT???");
                    } else {
                        String dueWhen = parts[1].trim();
                        Task newTask = new Deadline(taskName, dueWhen);
                        taskList.add(newTask);
                        int numTasks = taskList.size();

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask.toString());
                        System.out.println("Now you have " + numTasks + " tasks in the list");
                    }
                }

                // Event
            } else if (userInput.startsWith("event")) {
                String[] partsFrom = userInput.split("/from", 2);
                if (partsFrom.length < 2 || partsFrom[1].trim().isEmpty()) {
                    System.out.println("WHEN DOES IT START???");
                } else {
                    String[] partsTo = partsFrom[1].split("/to", 2);
                    if (partsTo.length < 2 || partsTo[1].trim().isEmpty()) {
                        System.out.println("WHEN DOES IT END???");
                    } else {
                        String taskName = partsFrom[0].replaceFirst("event", "").trim();
                        if (taskName.isEmpty()) {
                            System.out.println("event WHAT???");
                        } else {
                            String startWhen = partsTo[0].trim();
                            String endWhen = partsTo[1].trim();
                            Task newTask = new Event(taskName, startWhen, endWhen);
                            taskList.add(newTask);
                            int numTasks = taskList.size();

                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newTask.toString());
                            System.out.println("Now you have " + numTasks + " tasks in the list");
                        }
                    }
                }

            } else {
                System.out.println(errorMessage);
            }
        }
        scanner.close();
    }
}


