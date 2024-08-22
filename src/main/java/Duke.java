import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";

        System.out.println("____________________________________________________________");
        System.out.println("Greetings! I am Kobe Bryant \n" + logo);
        System.out.println("How can I help you? ");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                System.out.println("Goodbye! My man.");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}