import java.util.Scanner;

public class Prince {

    private static String LINE = "--------------------------------------";
    private static boolean TOGGLER = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printline();

        System.out.println("Hello! I'm Prince");
        System.out.println("What can I do for you?");
        printline();

        while (TOGGLER) {
            String input = sc.nextLine();
            printline();
            if (!input.equals("bye")) {
                System.out.println(input);
                printline();
            } else {
                TOGGLER = false;
            }
        }
        
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    private static void printline() {
        System.out.println(LINE);
    }
}
