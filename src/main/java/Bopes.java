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
                inputs.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}
