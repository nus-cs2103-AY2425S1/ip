import java.util.Scanner;

public class Lama {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String bar = "____________________________________________________________";
        System.out.println(bar);
        System.out.println("Hello! I'm Lama");
        System.out.println("What can I do for you?");
        System.out.println(bar + "\n");

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (!input.equals("bye")) {
                System.out.println(bar);
                System.out.println(input);
                System.out.println(bar + "\n");
            }

            if (input.equals("bye")) {
                System.out.println(bar);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(bar);
                break;
            }
        }

        scanner.close();

    }
}
