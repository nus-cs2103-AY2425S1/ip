import java.util.Scanner;
public class Gutti {

    private static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    private static void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        // User's input
        String input;
        // Loops until user says bye
        while (true) {
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("bye")) {
                break;
            }
            // Echo the command entered by the user
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Display the greeting message
        greetings();
        // Echos
        echo();
        // Exit message
        goodBye();
    }
}
