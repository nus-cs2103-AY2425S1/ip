import java.util.Scanner;

public class Genji {
    private static String NAME = "Genji";
    private static String LINE = "________________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void echo(String echo) {
        System.out.println(echo);
        System.out.println(LINE);
    }

    public static void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?");
        System.out.println(LINE);
        while (flag) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                flag = false;
            }
            else {
                echo(input);
            }
        }
        bye();
    }

    public static void main(String[] args) {
        run();
    }
}
