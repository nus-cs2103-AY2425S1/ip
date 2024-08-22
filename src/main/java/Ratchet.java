import java.util.Scanner;

public class Ratchet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("bye")) {
                echo(input);
            } else {
                exit();
            }
        }
    }

    public static void lineBreak() {
        System.out.println("   ________________________________________________________");
    }

    public static void greet() {
        lineBreak();
        System.out.println("    Hello! I'm Ratchet\n" + "    What can I do for you?");
        lineBreak();
    }

    public static void exit() {
        lineBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        lineBreak();
    }

    public static void echo(String s) {
        lineBreak();
        System.out.println("    " + s);
        lineBreak();
    }
}