import java.util.Objects;
import java.util.Scanner;
public class Neko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        String welcomeMessage = "Hello! What can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";

        System.out.println(line + welcomeMessage + line);

        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println(exitMessage + line);
                break;
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
    }
}
