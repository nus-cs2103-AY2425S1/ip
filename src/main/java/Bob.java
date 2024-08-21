import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________\n";
    private static String greeting() {
        return Bob.line +
                " Hello! I'm [YOUR CHATBOT NAME]\n" +
                " What can I do for you?\n" +
                Bob.line;
    }
    private static String farewell() {
        return  Bob.line +
                " Bye. Hope to see you again soon!\n" +
                Bob.line;
    }
    public static void main(String[] args) {
        System.out.println(Bob.greeting());
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your command: ");
            input = scanner.next();
            if (input.compareTo("bye") == 0) {
                System.out.println(Bob.farewell());
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
    }
}
