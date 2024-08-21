import java.util.Scanner;

public class Asta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Asta\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }

        scanner.close();
    }
}
