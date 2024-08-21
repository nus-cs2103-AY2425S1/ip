import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________\n";

    private static String greeting() {
        return Bob.lineFormat(" Hello! I'm Bob\n" +
                " What can I do for you?");
    }
    private static String farewell() {
        return  Bob.lineFormat(" Bye. Hope to see you again soon!");
    }
    private static String lineFormat(String input) {
        return Bob.line + input + "\n" + Bob.line;
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
            System.out.println(Bob.lineFormat(input));
        }
    }
}
