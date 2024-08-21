import java.util.Scanner;
public class Stan {
    public static void main(String[] args) {
        String logo = "Stan";
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + logo);
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }

        scanner.close();

    }
}
