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
            
            System.out.println("____________________________________________________________");

            // Exit if "bye" is entered
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                handleInput(input, tasks);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
            
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }

    private static void handleInput(String input, ArrayList<Task> tasks) throws UnknownCommandException {
        String[] words = input.split("\\s+");
        
        if (input.equals("list")) {
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
        } else if (
            words[0].equals("todo") || 
            words[0].equals("deadline") || 
            words[0].equals("event")
        ){
            // Add ToDo, Deadline or Event Task
            try {
                Task task;
                if (words[0].equals("todo")) {
                    task = ToDo.getToDoFromInput(input);
                } else if (words[0].equals("deadline")) {
                    task = Deadline.getDeadlineFromInput(input);
                } else {
                    // words[0] is guaranteed to be "event" here
                    task = Event.getEventFromInput(input);
                }
                tasks.add(task);
                
                System.out.println("Got it. I've added this task:\n  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (InvalidTaskFormatException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new UnknownCommandException(input);
        }
    }
}
