import java.util.Scanner;
public class Pebble {
    public static void main(String[] args) {
        // scans keyboard
        Scanner scanner = new Scanner(System.in);

        //introduction text
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        //forever loop
        while (true) {
            String input = scanner.nextLine();
            // break loop if says bye
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + input);
            System.out.println("    ____________________________________________________________");
        }

        // terminates
        scanner.close();
    }
}