import java.util.Scanner;
public class Nex {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Nex nex = new Nex();
        nex.run();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            }
            echo(input);
        }

        scanner.close();
    }
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Nex, your friendly personal chat buddy.");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void echo(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Did I hear you say \"" + input + "\"?");
        System.out.println("Is there anything else on your mind?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Bye then! Hope to catch you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
