import java.util.Scanner;

public class Alex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alex, your friendly assistant!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            //tells user a joke
            if (userInput.equalsIgnoreCase("tell me a joke")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Why did the scarecrow win an award? Because he was outstanding in his field!");
                System.out.println("____________________________________________________________");
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}

