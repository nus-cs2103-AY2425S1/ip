import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MiluTrock {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static String stdin = "";

    public static void main(String[] args) {
        String name = "MiluTrock";
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        
        // Replay previous input without output. This is a dirty way to re-create the task list
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        File f = new File("./data.txt");
        try {
            Scanner scanner = new Scanner(f);
            inputLoop(scanner);
        } catch (FileNotFoundException e) {}

        System.setOut(stdout);
        
        // Handle stdin
        Scanner scanner = new Scanner(System.in);
        inputLoop(scanner);

        // Store input
        try {
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            fw.write(stdin);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputLoop(Scanner scanner) {
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            stdin += input + "\n";
            
            System.out.println("____________________________________________________________");

            // Exit if "bye" is entered
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                handleInput(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
            
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }

    private static void handleInput(String input) throws UnknownCommandException {
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
            System.out.println("  " + tasks.get(i));
        } else if (words.length == 2 && words[0].equals("unmark")) {
            // Unmark task as done
            int i = Integer.parseInt(words[1]) - 1;
            tasks.get(i).unmarkDone();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(i));
        } else if (words.length == 2 && words[0].equals("delete")) {
            // Delete task
            int i = Integer.parseInt(words[1]) - 1;
            Task task = tasks.get(i);
            tasks.remove(i);

            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (InvalidTaskFormatException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new UnknownCommandException(input);
        }
    }
}
