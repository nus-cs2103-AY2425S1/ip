import java.util.ArrayList;

public class MiluTrock {
    public static void main(String[] args) {
        String name = "MiluTrock";
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (true) {
            String input = System.console().readLine();
            String[] words = input.split("\\s+");
            
            if (input.equals("bye")) {
                // Exit if "bye" is entered
                break;
            } else if (input.equals("list")) {
                // List all previous inputs if "list" is entered
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (words.length == 2 && words[0].equals("mark")) {
                // Mark task as done if "mark" is entered as the first word
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).markDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(i));
            } else if (words.length == 2 && words[0].equals("unmark")) {
                // Unmark task as done if "unmark" is entered as the first word
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).unmarkDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(i));
            } else {
                // Otherwise, store input
                System.out.println("Added: " + input);
                tasks.add(new Task(input));
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
