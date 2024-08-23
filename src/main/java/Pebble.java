import java.util.Scanner;
public class Pebble {
    public static void main(String[] args) {
        String[] TaskList = new String[100];
        int totalTasks = 0;

        // scans keyboard
        Scanner scanner = new Scanner(System.in);

        //introduction text
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What task(s) do you want me to keep track of?");
        System.out.println("    ____________________________________________________________");

        //forever loop
        while (true) {
            String input = scanner.nextLine();

            // break loop if says bye
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            // list everything if list
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < totalTasks; i++) {
                    System.out.println("    " + (i + 1) + ". " + TaskList[i]);
                }
                System.out.println("    ____________________________________________________________");
                continue;
            }

            TaskList[totalTasks] = input;
            totalTasks++;

            System.out.println("    ____________________________________________________________");
            System.out.println("    " + "Added: " + input);
            System.out.println("    ____________________________________________________________");
        }

        // terminates
        scanner.close();
    }
}
