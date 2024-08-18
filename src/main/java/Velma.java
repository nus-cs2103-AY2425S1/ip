import java.util.Scanner;
import java.util.ArrayList;

public class Velma {

    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        int count = 1;
        ArrayList<Task> list = new ArrayList<>(100);
        boolean end = false;
        printLine();
        System.out.println("Hello! I am Velma!");
        System.out.println("What can I do for you?");
        Scanner req = new Scanner(System.in);
        printLine();

        while (!end) {
            String request = req.nextLine();
            try {
                if (request.equals("bye")) {
                    printLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    break;
                } else if (request.startsWith("todo")) {
                    String todoDescription = request.replaceFirst("todo\\s*", "").trim();
                    if (todoDescription.isEmpty()) {
                        throw new VelmaException("Sorry boss! Where is your todo description?");
                    }
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newTodo = new Todo(todoDescription);
                    list.add(newTodo);
                    System.out.println("  " + newTodo.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    printLine();
                } else if (request.startsWith("deadline")) {
                    String[] parts = request.replaceFirst("deadline\\s*", "").split(" /by ");
                    if (parts.length < 2) {
                        throw new VelmaException("Sorry boss! Your deadline task needs a deadline!");
                    }
                    String description = parts[0];
                    String deadline = parts[1];
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newDeadline = new Deadline(description, deadline);
                    list.add(newDeadline);
                    System.out.println("  " + newDeadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    printLine();
                } else if (request.contains("event")) {
                    String[] parts = request.replaceFirst("event\\s+", "").split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new VelmaException("Sorry boss! An event needs a valid start time and end time!");
                    }
                    String description = parts[0];
                    String startTime = parts[1];
                    String endTime = parts[2];
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newEvent = new Event(description, startTime, endTime);
                    list.add(newEvent);
                    System.out.println("  " + newEvent.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printLine();
                } else if (request.equals("list")) {
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : list) {
                        System.out.println(count + "." + task.toString());
                        count++;
                    }
                    printLine();
                    count = 1;
                } else if (request.contains("mark") || request.contains("unmark")) {
                    String[] parts = request.split(" ");
                    if (parts.length < 1) {
                        throw new VelmaException("Sorry boss! Please specify which task.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    list.get(taskNumber).changeIsDone();
                    printLine();
                    if (request.contains("mark")) {
                        System.out.println("Nice! I have marked this task as done:");
                        System.out.println("  " + "[" + list.get(taskNumber).getStatusIcon() + "] " + list.get(taskNumber).description);
                        printLine();
                    } else {
                        System.out.println("OK! I have marked this task as not done yet:");
                        System.out.println("  " + "[" + list.get(taskNumber).getStatusIcon() + "] " + list.get(taskNumber).description);
                        printLine();
                    }
                } else if (request.contains("delete")) {
                    String[] parts = request.split(" ");
                    if (parts.length < 1) {
                        throw new VelmaException("Sorry boss! Please specify which task to delete.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    printLine();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + list.get(taskNumber).toString());
                    list.remove(taskNumber);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printLine();
                } else {
                    throw new VelmaException("Sorry boss! What are you talking about?");
                }
            } catch (VelmaException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }
}
