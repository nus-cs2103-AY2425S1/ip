import java.util.Scanner;

public class SilverWolf {
    public static void main(String[] args) {
        // initialize the scanner to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Silver Wolf\n" +
                " What can I help you with?\n" +
                "____________________________________________________________\n");
        while(true) {
            // reading the user input
            String input = scanner.nextLine();

            // echo the input back to the user
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");


            // exit if the user types "bye"
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Till we meet again!");
                System.out.println("____________________________________________________________");
                break;
            }
        }

        // close the scanner
        scanner.close();

    }
}
