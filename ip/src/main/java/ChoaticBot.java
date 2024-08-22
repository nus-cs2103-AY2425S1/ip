import java.util.Objects;
import java.util.Scanner;

public class ChoaticBot {
    public static void main(String[] args) {
        String welcomeMsg = "____________________________________________________________ \n"
                + "Hello! I'm ChoaticBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        String byeMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";


        String lines = "____________________________________________________________\n";
        System.out.println(welcomeMsg);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            System.out.println(lines + userInput + "\n" + lines);
        }
    }
}
