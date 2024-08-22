import java.util.Scanner;

public class Buddy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("    ____________________________________");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ____________________________________\n");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\n    ____________________________________");
                System.out.println("    Bye! See ya soon!");
                System.out.println("    ____________________________________");
                break;
            } else {
                System.out.println("\n    ____________________________________");
                System.out.println("    " + userInput);
                System.out.println("    ____________________________________\n");
            }
        }
        scanner.close();

    }
}
