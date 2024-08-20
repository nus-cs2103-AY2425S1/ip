import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alice {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";
    private static TaskList list = new TaskList();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
         while (true) {
             String input = scanner.nextLine().trim();
             String[] parts = input.split(" ", 2);
             String verb = parts[0];

             if (input.equals("bye")) {
                 break;
             } else if (input.equals("list")) {
                 listTasks();
             } else if (verb.equals("mark")) {
                 int num = Integer.parseInt(input.split(" ")[1]);
                 list.markTask(num);
             } else if (verb.equals("unmark")) {
                 int num = Integer.parseInt(input.split(" ")[1]);
                 list.unmarkTask(num);
             } else if (verb.equals("todo")) {
                 try {
                     Todo toDo = new Todo(parts[1]);
                     list.addToList(toDo);
                 } catch (Exception e) {
                     System.out.println("Command Format: todo [description]");
                 }
             } else if (verb.equals("event")) {
                 try {
                     String[] detail = parts[1].split("/from");
                     String description = detail[0].trim();
                     String[] time = detail[1].trim().split("/to");
                     String start = time[0].trim();
                     String end = time[1].trim();
                     Event event = new Event(description, start, end);
                     list.addToList(event);
                 } catch (Exception e) {
                     System.out.println("Command Format: event [description] /from [time] /to [time]");
                 }
             } else if (verb.equals("deadline")) {
                 try {
                     String[] detail = parts[1].split("/by");
                     String description = detail[0].trim();
                     String time = detail[1].trim();
                     Deadline deadline = new Deadline(description, time);
                     list.addToList(deadline);
                 } catch (Exception e) {
                     System.out.println("Command Format: deadline [description] /by [time]");
                 }
             } else {
                 System.out.println(line);
                 System.out.println("Invalid command, use command words: list, todo, deadline & event");
                 System.out.println(line);
             }
         }
        exit(scanner);
    }

    public static void listTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    private static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private static void exit(Scanner scanner) {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        scanner.close();
    }
}
