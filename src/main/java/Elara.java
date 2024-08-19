import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine();
            if (text.toLowerCase().strip().equals("bye")) {
                break;
            }
            System.out.println(text);
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
