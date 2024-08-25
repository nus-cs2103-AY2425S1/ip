import java.util.Scanner;

public class Spike {

    public static void helloMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Spike\nWhat can I do for you?");
        System.out.println("_________________________________________________________");
        return;
    }

    public static void byeMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                byeMessage();
                break;
            } else {
                System.out.println("     _________________________________________________________");
                System.out.println("     " + input);
                System.out.println("     _________________________________________________________");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        helloMessage();
        echo();
    }
}
