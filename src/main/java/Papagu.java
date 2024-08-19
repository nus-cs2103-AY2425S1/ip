import java.util.Scanner;

public class Papagu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello from Papagu!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
            userInput = scanner.nextLine();
        } 
        System.out.println("Bye! Hope to see you again soon!");
    }
}
