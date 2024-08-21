import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
