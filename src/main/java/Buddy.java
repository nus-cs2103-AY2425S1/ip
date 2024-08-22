import java.util.Scanner;

public class Buddy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int tracker = 0;
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
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\n    ____________________________________");
                if (tracker == 0) {
                    System.out.println("    List is empty!!");
                } else {
                    for (int i = 0; i < tracker; i++) {
                        System.out.printf("    %d. %s%n", i+1, list[i]);
                    }
                }
                System.out.println("    ____________________________________\n");

            } else {
                list[tracker] = userInput;
                tracker++;
                System.out.println("\n    ____________________________________");
                System.out.println("    added: " + userInput);
                System.out.println("    ____________________________________\n");
            }
        }
        scanner.close();

    }
}
