import java.util.*;

public class Handsome {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Handsome\nWhat can I do for you?\n");

        //for the echo
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye, it was an amazing conversation!");
                break;
            }

            System.out.println(input);

        }
    }
}
