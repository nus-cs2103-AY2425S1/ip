import java.util.Scanner;

public class Testament {
    final static String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        boolean powerOn = true;
        Scanner scanner = new Scanner(System.in);
        printDialogue(" Hello! I'm Testament\n What can I do for you?");
        while (powerOn) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                printDialogue(" Bye. Hope to see you again soon!");
                powerOn = false;
            } else {
                printDialogue(userInput);
            }
        }
    }

    private static void printDialogue(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }
}