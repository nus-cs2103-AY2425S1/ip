import java.util.Scanner;

public class Neko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage =  "  ∧,,,∧  \n( ̳̳• · ̳• ) \n づ Meow! I'm Neko\nWhat can I do for you? ";
        String exitMessage = "Bye! Hope to see you again soon meow ฅ ฅ";
        int numOfItem = 0;
        String[] ls = new String[100];

        System.out.println(greetingMessage);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < numOfItem; i++) {
                    System.out.println(i + 1 +": " + ls[i]);
                }
            } else {
                ls[numOfItem++] = input;
                System.out.println("added: " + input + " ฅ/ᐠᓀ ﻌ ᓂマ");
            }
            input = scanner.nextLine();
        }
        System.out.println(exitMessage);
    }

}
