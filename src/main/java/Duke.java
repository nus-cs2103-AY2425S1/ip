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

        String[] things = new String[24];
        int count = 0;
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

            if (userInput.equals("list")) {
                int counter = 0;
                while (things[counter] != null) {
                    System.out.println(things[counter]);
                    counter++;
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("added: " + userInput);
                int num = count + 1;
                String numString = String.valueOf(num);
                things[count] = numString + ". " + userInput;
                count++;
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}