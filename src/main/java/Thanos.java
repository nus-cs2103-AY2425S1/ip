import java.util.Scanner;

public class Thanos {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Thanos!\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(command);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
