import java.util.Scanner;
public class Axel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        //Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Axel");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        //Repeatedly prompts for user's input
        while (true) {
            //Reads user's input
            userInput = scanner.nextLine();
            //Echoes user's input
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
            //Exit message
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
        scanner.close();
    }
}
