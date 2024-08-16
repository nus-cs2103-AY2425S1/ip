import java.util.Scanner;
import java.util.ArrayList;

public class Barney {
    private static String LONG_LINE = "_________________________________________________";
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        welcomeText();
        chat();
        endingText();
    }

    public static void welcomeText() {
        String text = "Hello, I am Barney <RAWR>, what can I do for you?";
        System.out.println(text);
        System.out.println(LONG_LINE);
    }

    public static void chat() {
        String input;
        Boolean isChatting = true;
        ArrayList<String> list = new ArrayList<String>();

        while (isChatting) {
            System.out.println(">>>");
            input = SCANNER.nextLine();
            System.out.println("<<<");

            switch (input) {
                case "list":
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                    break;
                case "bye":
                    isChatting = false;
                    break;
                default:
                    list.add(input);
                    System.out.println("added: " + input);

                    System.out.println(LONG_LINE);
            }
        }
    }

    public static void endingText() {
        String text = "Goodbye, I am Barney <RAWR>, see you next time!";
        System.out.println(text);
        System.out.println(LONG_LINE);
    }

}
