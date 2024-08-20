import java.util.Scanner;

public class Fanny {

    public static void main(String[] args) {

        List list = new List();

        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Fanny");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");

        Scanner scanner = new Scanner(System.in);

        //while ensure continuous reading of user inputs
        while (true) {
            System.out.println("User: ");
            String cmd = scanner.nextLine();

            //condition to exit when cmd is bye
            if (cmd.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            } else if (cmd.equals("list")) {
                list.printList();
            } else {
                System.out.println("_____________________________________________");
                System.out.println("Fanny: ");
                System.out.println("Added: " + cmd);
                System.out.println("_____________________________________________");
                list.add(cmd);
            }

        }

        scanner.close();
    }
}
