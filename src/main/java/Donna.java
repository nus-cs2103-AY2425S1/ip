import java.util.Scanner;

public class Donna {
    static private void printDonnaLogo() {
        System.out.println(" ____      ");
        System.out.println("|  _ \\  ___  _ __  _ __   __ _ ");
        System.out.println("| | | |/ _ \\| '_ \\| '_ \\ / _` |");
        System.out.println("| |_| | (_) | | | | | | | (_| |");
        System.out.println("|____/ \\___/|_| |_|_| |_|\\__,_|");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printDonnaLogo();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Donna");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();


            //exit
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            //echo
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

        }

        sc.close();
    }
}
