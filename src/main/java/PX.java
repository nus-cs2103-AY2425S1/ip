import java.util.Scanner;

public class PX {
    private static String name = "PX";

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void PXSays(String arg) {
        System.out.println("    " + arg);
    }

    public static void main(String[] args) {
        printLine();
        PXSays("Hello! I'm " + name);
        PXSays("What can I do for you?");
        printLine();
        Scanner sc = new Scanner(System.in);

        while (true) {
            PXSays("");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printLine();
                PXSays("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else {
                printLine();
                PXSays(input);
                printLine();

            }
        }
    }
}
