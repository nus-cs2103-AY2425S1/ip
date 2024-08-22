import java.util.ArrayList;
import java.util.Scanner;

public class Bopes {
    public static void main(String[] args) {
        String intro = "Bopes is a personal assistant that helps you manage your tasks.";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        ArrayList<Task> inputs = new ArrayList<Task>();

        while (true) {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            // Exit 
            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            }

            // Input special commands
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(inputs.get(i).toString());
                }
            } else if (input.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                inputs.get(index).markAsDone();
            } else if (input.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                inputs.get(index).markAsUndone();
            } else {
                // Adding of tasks
                Task newTask = null;
                if (input.startsWith("todo")) {
                    newTask = new ToDo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    String[] temp = input.substring(9).split(" /by ");
                    newTask = new Deadline(temp[0], temp[1]);
                } else if (input.startsWith("event")) {
                    String[] temp = input.substring(6).split(" /from | /to ");
                    newTask = new Event(temp[0], temp[1], temp[2]);
                }
                inputs.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            }
        }

        scanner.close();
    }
}
