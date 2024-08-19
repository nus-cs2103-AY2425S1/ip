import java.util.Scanner;

public class Taskon {
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String toEcho = scanner.nextLine();
            if (toEcho.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(toEcho + "\n");
            }
        }
    }

    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }
}
