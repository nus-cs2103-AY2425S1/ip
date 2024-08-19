import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alice {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
         while (true) {
             String input = scanner.nextLine();
             if (input.equals("bye")) {
                 break;
             } else if (input.equals("list")) {
                 listTasks();
             } else if (input.startsWith("mark")) {
                 String[] phrases = input.split(" ");
                 if (phrases.length > 1) {
                     try {
                         int num = Integer.parseInt(phrases[1]);
                         Task currTask = tasks.get(num - 1);
                         currTask.markDone();
                         System.out.println(line);
                         System.out.println("Nice! I've marked this task as done:");
                         System.out.println(currTask.toString());
                         System.out.println(line);
                     } catch (IndexOutOfBoundsException e) {
                         System.err.println("Invalid number");
                     }
                 } else {
                     System.err.println("Need to state the number");
                 }
             } else if (input.startsWith("unmark")) {
                 String[] phrases = input.split(" ");
                 if (phrases.length > 1) {
                     try {
                         int num = Integer.parseInt(phrases[1]);
                         Task currTask = tasks.get(num - 1);
                         currTask.markUndone();
                         System.out.println(line);
                         System.out.println("OK, I've marked this task as not done yet:");
                         System.out.println(currTask.toString());
                         System.out.println(line);
                     } catch (IndexOutOfBoundsException e) {
                         System.err.println("Invalid number");
                     }
                 } else {
                     System.err.println("Need to state the number");
                 }
             } else {
                 Task newTask = new Task(input);
                 tasks.add(newTask);
                 String s = "added: " + input;
                 System.out.println(line);
                 System.out.println(s);
                 System.out.println(line);
             }
         }
        exit(scanner);
    }

    public static void listTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            int currNum = i + 1;
            String s = currNum + "." + currTask.toString();
            System.out.println(s);
        }
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
