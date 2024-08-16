import java.util.Scanner;

public class Rex {
    public static void main(String[] args) {
        // Horizontal line separation
        String separation = "____________________________________________________________";

        // "rawr" string added to end of each print statement for customization
        String rawr = " rawr";

        // Rex's greeting message
        System.out.println(separation);
        System.out.println("Hello! I'm Rex!" + rawr);
        System.out.println("What can I do for you?" + rawr);
        System.out.println(separation);

        // Loop echos commands entered by user
        do {
            // Takes in user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Tell Rex: ");
            String input = scanner.nextLine();

            // Exit loop if input is "bye"
            if (input.equals("bye")) {
                break;
            }

            // Echo user input through print statement, but with a rawr
            System.out.println(separation);
            System.out.println(input + rawr);
            System.out.println(separation);
        }  while (true);

        // Rex's exit message
        System.out.println(separation);
        System.out.println("Bye. Hope to see you again soon!" + rawr);
        System.out.println(separation);
    }
}
