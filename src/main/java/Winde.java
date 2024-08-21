import java.util.Scanner;

public class Winde {
    public static void main(String[] args) {
        /* String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        // System.out.println("Hello from\n" + "Winde");
        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("bye"))) {
            System.out.println("    " + input);
            input = scanner.nextLine();
        }

        exit();
    }
    public static void greet() {
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
