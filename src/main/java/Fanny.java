import java.util.Scanner;

public class Fanny {
    public static void main(String[] args) {
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Fanny");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");

        //while ensure continuous reading of user inputs
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("User: ");
            String cmd = scanner.nextLine();

            //condition to exit when cmd is bye
            if(cmd.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            }

            System.out.println("_____________________________________________");
            System.out.println("Fanny: ");
            System.out.println(cmd);
            System.out.println("_____________________________________________");

        }

    }
}
