import java.util.Scanner;
import java.util.ArrayList;

public class MiluTrock {
    public static void main(String[] args) {
        String name = "MiluTrock";
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] words = input.split("\\s+");
            
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                // Exit if "bye" is entered
                break;
            } else if (input.equals("list")) {
                // List all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (words.length == 2 && words[0].equals("mark")) {
                // Mark task as done
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).markDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(i));
            } else if (words.length == 2 && words[0].equals("unmark")) {
                // Unmark task as done
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).unmarkDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(i));
            } else if (words[0].equals("todo")){
                // Add todo task
                String taskName = input.substring(5);
                ToDo task = new ToDo(taskName);
                tasks.add(task);
                
                System.out.println("Got it. I've added this task:\n  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (words[0].equals("deadline")){
                // Add deadline task
                String taskInput = input.substring(9);
                String[] parts = taskInput.split(" /by ");
                Deadline task = new Deadline(parts[0], parts[1]);
                tasks.add(task);
                
                System.out.println("Got it. I've added this task:\n  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (words[0].equals("event")){
                // Add event task
                String taskInput = input.substring(6);
                String[] parts = taskInput.split(" /from ");
                String[] dates = parts[1].split(" /to ");
                Event task = new Event(parts[0], dates[0], dates[1]);
                tasks.add(task);
                
                System.out.println("Got it. I've added this task:\n  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            System.out.println("____________________________________________________________");
        }
        
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
