import java.util.Scanner;
public class Elon {

    private static void Echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (!input.equals("bye")) {
            System.out.println(input);
            System.out.println("-------------------------------------------------------");
            input = scanner.next();
        }
        scanner.close();
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------------------------");
    }
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------");
        System.out.println(" Hello! I'm Elon");
        System.out.println(" What can I do for you?");
        System.out.println("-------------------------------------------------------");
        Elon.Echo();
    }
}
