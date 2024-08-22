import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Kobe :D \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
        
            userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}