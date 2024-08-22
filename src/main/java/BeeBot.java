import java.util.Objects;
import java.util.Scanner;

public class BeeBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean exit_status = false;
        String greet = "Hello! I'm BeeBot\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again!";
        speak(greet);

        while (!exit_status) {
            String cmd = scanner.nextLine();
            if (Objects.equals(cmd, "bye")) {
                speak(exit);
                exit_status = true;
            } else {
                speak(cmd);
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd + "\n"
                + "________________________\n");
    }
}