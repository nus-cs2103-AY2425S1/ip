import java.util.ArrayList;
import java.util.Scanner;

public class Dawn {
    public static void main(String[] args) {
        String logo = "Dawn 🌙";
        String divider = "--".repeat(30);
        String[] lst = new String[100];
        Scanner scanner = new Scanner(System.in);

        System.out.println(divider);
        System.out.printf("%s speaking, what can I do for you? \n", logo);
        respond(scanner, lst, 0);
        System.out.println(divider);
    }

    private static void respond(Scanner scanner, String[] lst, int counter) {
        String input = scanner.nextLine();

        if (input.equals("bye")) {
            System.out.println("Byeeee~ nice chatting with you! See you next time, tschüss :)");
        } else if (input.equals("list")) {
            System.out.println("listing all tasks...");
            for (int i = 0; i < counter; i++) {
                System.out.printf("%d. %s  \n", i + 1, lst[i]);
            }
            respond(scanner, lst, counter);
        } else {
            lst[counter] = input;
            System.out.printf("added TASK <%s> to the list \n", input);
            respond(scanner, lst, ++counter);
        }
    }
}
