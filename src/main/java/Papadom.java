import java.util.Objects;
import java.util.Scanner;
public class Papadom {
    public static void main(String[] args) {

        String logo = "____________________________________________________________\n"
                + "Hello! I'm Papadom\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(logo);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            if (Objects.equals(text, "bye")) break;
            String response = "____________________________________________________________\n"
                    + " " + text + "\n"
                    + "____________________________________________________________";

            System.out.println(response);
        }
        System.out.println(exitMessage);
    }
}
