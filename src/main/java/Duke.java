import java.util.Scanner;

public class Duke {

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.next();
            if (command.equals("list")) {
                System.out.println("list");
            } else if (command.equals("bye")) {
                break;
            } else {
                System.out.println(command);
            }
        }
        sc.close();
        bye();
    }
}
