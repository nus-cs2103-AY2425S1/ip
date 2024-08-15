import java.util.Scanner;

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
        String input = "";
        Boolean isChatting = true;
        while (isChatting) {
            switch (input) {
                case "bye":
                    isChatting = false;
                    break;
                default:
                    System.out.println(">>>");
                    input = SCANNER.nextLine();
                    System.out.println("<<<");
                    System.out.println(input);
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
