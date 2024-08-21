import java.util.Scanner;

public class Tako {
    public static void main(String[] args) {
        String name = "Tako";

        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + "\n" +
                           "What can I do for you?\n");

        String command = input.next();

        while(!command.equals("bye")) {
            System.out.println(command);
            command = input.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
