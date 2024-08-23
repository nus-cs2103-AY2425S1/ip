import java.util.Scanner;

public class Sammy {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);

        System.out.println(line);
        System.out.println(" Hello! I'm Sammy.");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                break;
            }
            System.out.println(line);
            System.out.println(" " + input);
            System.out.println(line);
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
