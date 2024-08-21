import java.util.Objects;
import java.util.Scanner;
public class Rob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greet = "Hello! I'm Rob\n" +
                "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.println(greet);

        while (true) {
            String input = scanner.nextLine();
            // echo
            String echo = "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________\n";
            System.out.println(echo);

            // exit
            if (Objects.equals(input, "bye")) {
                System.out.println(exit);
                break;
            }
        }
        scanner.close();
    }
}
