import java.util.Scanner;

public class Nugget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("________________________________________");
        System.out.println("Hello! I'm Nugget");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
        while (true) {
            String text = scanner.next();
            if (text.equals("bye")) {
                System.out.println("________________________________________");
                break;
            }
            System.out.println("________________________________________");
            System.out.println(text);
            System.out.println("________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
