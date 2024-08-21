import java.util.Scanner;

public class Socchat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            System.out.print("> ");
            if (input.equals("bye")) {
                exit();
                break;
            }
            System.out.println(input);
        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!    ");
    }
}
