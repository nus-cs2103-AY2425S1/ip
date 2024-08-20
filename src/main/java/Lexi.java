import java.util.Scanner;

public class Lexi {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        greet();
        String response = userInput.nextLine();
        while (!response.equals("bye")) {
            echo(response);
            response = userInput.nextLine();
        }
        bye();
    }
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Lexi\n What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }
    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    public static void echo(String message) {
        System.out.println("____________________________________________________________");
        System.out.printf(" %s%n", message);
        System.out.println("____________________________________________________________\n");
    }

}
