import java.util.Scanner;

public class Diomon {
    public static void greeting() {
        String greetingMessage = """
                           ________________________________________________________________
                           Hello! I'm Diomon
                           What can I do for you?
                           ________________________________________________________________
                           """;
        System.out.println(greetingMessage);
    }
    public static void bye() {
        String byeMessage = """
                            ________________________________________________________________
                            Bye. Hope to see you again soon!
                            ________________________________________________________________
                            """;
        System.out.println(byeMessage);
    }
    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.printf("""
                               ________________________________________________________________
                               ~~%s xD~~
                               ________________________________________________________________
                               """, input);
        }
    }

    public static void main(String[] args) {
        greeting();
        echo();
        bye();
    }
}
