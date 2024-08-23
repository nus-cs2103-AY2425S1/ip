import java.util.Scanner;

public class MySutong {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

        scanner.close();
    }
}
