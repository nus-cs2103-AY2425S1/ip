import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("\t------------------------------------------");
        System.out.println("\tHello! I'm Bob!\n\tHow can I help you today?");
        System.out.println("\t------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (!input.equals("bye")) {
                System.out.println("\t------------------------------------------");
                System.out.println("\t" + input);
                System.out.println("\t------------------------------------------");
            }
        }

        System.out.println("\t------------------------------------------");
        System.out.println("\tBye, see you again!");
        System.out.println("\t------------------------------------------");
    }
}


