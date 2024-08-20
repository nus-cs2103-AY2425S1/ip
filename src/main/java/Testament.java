import java.util.Scanner;

public class Testament {
    final static String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        boolean powerOn = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Testament\n What can I do for you?");
        System.out.println(LINE);
        while (powerOn) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(LINE);
                powerOn = false;
            } else {
                System.out.println(userInput);
                System.out.println(LINE);
            }
        }
    }
}