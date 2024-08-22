import java.util.Scanner;

public class PacMan {
    public static void greet() {
        System.out.println("Hello! I'm PacMan");
        System.out.println("How can I help you?");
    }

    public static void exit() {
        System.out.println("Good bye. Hope to see you soon!");
    }

    public static void echo(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                echo(input);
            }
        }
        exit();
    }
}
