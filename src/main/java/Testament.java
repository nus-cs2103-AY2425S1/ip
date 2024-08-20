import java.util.Scanner;

public class Testament {
    final static String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        boolean powerOn = true;
        Scanner scanner = new Scanner(System.in);
        printDialogue(" Morning!\n Nice day for a stroll, don't you think?");
        while (powerOn) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                printDialogue(" I'd say it's time for a tea break. Milk and sugar for you?");
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