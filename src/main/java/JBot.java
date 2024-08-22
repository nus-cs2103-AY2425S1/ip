import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JBot {

    private static boolean isRunning = true;
    private static void greetUser() {
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
    }
    private static void endSession() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greetUser();

        while (JBot.isRunning) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                sc.close();
                isRunning = false;
                endSession();
            } else {
                System.out.println(command);
            }
        }
    }
}
