import java.util.ArrayList;
import java.util.Scanner;

public class Eevee {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.println(divider + greeting + divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(divider);

            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(input);
            }

            System.out.println(divider);
        }
    }
}
