import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        /*** Initialising ***/
        String logo = "____________________________________________________________\n"
                + " Hello! I'm Lemon\n"
                + " What can I do for you?\n";

        String end = " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        String bar = "____________________________________________________________";

        Scanner scan = new Scanner(System.in);
        String input = null;
        String[] tasks = new String[100];
        int numTasks = 0;

        /*** Program starts here ***/
        System.out.print(logo);

        while (true) {
            System.out.println(bar);

            input = scan.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                System.out.println(bar);
                break;
            } else if (input.equals("list") || input.equals("List")) {
                System.out.println(bar);
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else {
                System.out.println(bar);
                System.out.println("added: " + input);
                tasks[numTasks] = input;
                numTasks++;
            }
        }

        System.out.println(end);
    }
}
