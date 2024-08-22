import java.util.ArrayList;
import java.util.Scanner;

public class Bopes {
    public static void main(String[] args) {
        String intro = "Bopes is a personal assistant that helps you manage your tasks.";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        ArrayList<String> inputs = new ArrayList<String>();

        while (true) {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            // Exit 
            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            }

            // Display inputs
            if (input.equals("list")) {
                System.out.println("Here are the inputs in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + ". " + inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println("added: " + input);
            }

        }

        scanner.close();
    }
}
