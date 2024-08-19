import java.util.Scanner;

public class Derrick {

    public static void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            }
            else System.out.println(input);
        }
    }


    public static void exit() {
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) {
        Derrick.greetings();
        Derrick.echo();
    }
}
