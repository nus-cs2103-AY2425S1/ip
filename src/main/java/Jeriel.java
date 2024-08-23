import java.util.Scanner;


public class Jeriel {
    public static void main(String[] args) {  
        String logo = "     _  ____  ___   _  ____  _      \n"
                    + "    | || ___|| _ \\ | || ___|| |     \n"
                    + "    | |||___ ||_> || |||___ | |     \n"
                    + " _  | || ___||  _/ | || ___|| |     \n"
                    + "| |_| |||___ ||\\\\  | |||___ | |____ \n"
                    + " \\___/ |____||| \\\\ |_||____||______|\n";
        System.out.println("Hello from\n" + logo);

        // System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jeriel");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        String[] tasks = new String[100];
        int taskCount = 0;


        while (true) {
            input = scanner.nextLine(); 

            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {

                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

}
