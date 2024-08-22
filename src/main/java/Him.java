import java.util.Scanner;

public class Him {

    private static HimList list = new HimList();

    private static void greet() {
        System.out.println("Him: Hello! I'm Him\n     What can I do for you?\n");
    }

    private static void exit() {
        System.out.println("\nHim: Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        System.out.print("User: ");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("\nHim: Sure! Here's your list!\n\n" + list);
            } else {
                list.add(input);
                System.out.println("\nHim: added \"" + input + "\" to list\n");
            }
            System.out.print("User: ");
            input = scanner.nextLine();
        }
        exit();
    }
}
