import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " Hello! I'm Lemon\n"
                + " What can I do for you?\n";

        String end = " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        String bar = "____________________________________________________________";

        Scanner scan = new Scanner(System.in);
        String input = null;

        System.out.print(logo);

        while(true) {
            System.out.println(bar);
            input = scan.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                System.out.println(bar);
                break;
            }

            System.out.println(bar);
            System.out.println(input);
        }

        System.out.println(end);
    }
}
