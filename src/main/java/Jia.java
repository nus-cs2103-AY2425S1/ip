import java.util.ArrayList;
import java.util.Scanner;

public class Jia {
    public static void main(String[] args) {
        //Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //Create an array list to store user inputs
        ArrayList<String> inputs = new ArrayList<>();
        String input;

        //Greet the user
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Jia");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________");

        //Read user input until they say bye
        do {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                System.out.println("______________________________________________________");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + inputs.get(i));
                }
                System.out.println("______________________________________________________");
            } else if (!input.equalsIgnoreCase("bye")) {
                inputs.add(input);
                System.out.println("______________________________________________________");
                System.out.println(" " + "added: " + input);
                System.out.println("______________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye"));

        //Exit
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");

        scanner.close();
    }
}
